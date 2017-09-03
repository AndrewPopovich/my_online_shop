package com.myshop.myonlineshop.handler;

import com.myshop.myonlineshop.model.RegisterModel;
import com.myshop.myonlineshop.service.EmailService;
import com.myshop.shopbackend.dao.UserDAO;
import com.myshop.shopbackend.dto.Address;
import com.myshop.shopbackend.dto.Cart;
import com.myshop.shopbackend.dto.User;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RegisterHandler {

    public static final String FROM = "from";

    public static final String TO = "to";

    public static final String SUBJECT = "subject";

    public static final String BCC_LIST = "bccList";

    public static final String CCC_LIST = "ccList";

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private VelocityEngine velocityEngine;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserDAO userDAO;

    public RegisterModel init() {
        return new RegisterModel();
    }

    public void addUser(RegisterModel registerModel, User user) {
        registerModel.setUser(user);
    }

    public void addBilling(RegisterModel registerModel, Address billing) {
        registerModel.setBilling(billing);
    }

    public String saveAll(RegisterModel model) {
        String transitionValue = "success";

        User user = model.getUser();

        if (user.getRole().equals("USER")) {
            Cart cart = new Cart();
            cart.setUser(user);

            user.setCart(cart);
        }

        // Send email for new USER
        Map<String, Object> emailModel = new HashMap<>();

        emailModel.put(EmailService.FROM, "myonlineshop@mvc.app");
        emailModel.put(EmailService.SUBJECT, "Hello from My Online Shop!");
        emailModel.put(EmailService.TO, user.getEmail());
        emailModel.put(EmailService.CCC_LIST, new ArrayList<>());
        emailModel.put(EmailService.BCC_LIST, new ArrayList<>());
        emailModel.put("userName", user.getFirstName() + " " + user.getLastName());
        emailModel.put("url", "${contextRoot}/home");
        emailModel.put("message", "your registered success!");
        boolean result = this.sendEmail("registered.vm", emailModel);

        // Encode user password
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userDAO.addUser(user);

        Address billing = model.getBilling();
        billing.setUser(user);
        billing.setBilling(true);

        userDAO.addAddress(billing);

        return transitionValue;
    }

    public String validateUser(User user, MessageContext messageContext) {
        String transitionValue = "success";

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            messageContext.addMessage(new MessageBuilder()
                    .error()
                    .source("confirmPassword")
                    .defaultText("Password dos'n match the confirm password!")
                    .build());
            transitionValue = "failure";
        }
        if (userDAO.getByEmail(user.getEmail()) != null) {
            messageContext.addMessage(new MessageBuilder()
                    .error()
                    .source("email")
                    .defaultText("Email is already used!")
                    .build());
            transitionValue = "failure";
        }
        return transitionValue;
    }

    private boolean sendEmail(final String templateName, final Map<String, Object> model) {
        boolean res = false;
        try {
            mailSender.send(mimeMessage -> {
                String from = (String) model.get(FROM);
                String to = (String) model.get(TO);
                String subject = (String) model.get(SUBJECT);

                List<String> bccList = (List<String>) model.get(BCC_LIST);

                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
                message.setFrom(from);
                message.setTo(to);
                message.setSubject(subject);
                message.setSentDate(new Date());

                if (bccList != null) {
                    for (String bcc : bccList) {
                        message.addBcc(bcc);
                    }
                }
                model.put("noArgs", new Object());
                String text = VelocityEngineUtils.mergeTemplateIntoString(
                        velocityEngine, templateName, "UTF-8", model);

                message.setText(text, true);
            });
            res = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }
}
