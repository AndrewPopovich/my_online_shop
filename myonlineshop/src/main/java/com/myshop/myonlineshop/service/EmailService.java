package com.myshop.myonlineshop.service;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class EmailService {

    public static final String FROM = "from";

    public static final String TO = "to";

    public static final String SUBJECT = "subject";

    public static final String BCC_LIST = "bccList";

    public static final String CCC_LIST = "ccList";

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private VelocityEngine velocityEngine;

    public boolean sendEmail(final String templateName, final Map<String, Object> model) {
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

