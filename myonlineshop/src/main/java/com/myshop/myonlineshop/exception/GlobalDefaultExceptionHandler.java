package com.myshop.myonlineshop.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @Value("${excep.message.notConstr}")
    private String notConstr;

    @Value("${excep.message.notAvailb}")
    private String notAvailable;

    @Value("${excep.title.404}")
    private String error404;

    @Value("${page.jsp.errorJsp}")
    private String error;

    @Value("${excep.title.prodNotAvailb}")
    private String productNotAvailable;

    @Value("${excep.message.prodNotAvailbDes}")
    private String productNotAvailableDescription;

    @Value("${excep.title.prodUnavailb}")
    private String productUnavailable;

    @Value("${excep.title.contAdmin}")
    private String contactAdmin;

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handlerNoHandlerFoundException() {
        ModelAndView mv = new ModelAndView(error);

        mv.addObject("errorTitle", notConstr);
        mv.addObject("errorDescription", notAvailable);
        mv.addObject("title", error404);

        return mv;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ModelAndView handlerProductNotFoundException() {
        ModelAndView mv = new ModelAndView(error);

        mv.addObject("errorTitle", productNotAvailable);
        mv.addObject("errorDescription", productNotAvailableDescription);
        mv.addObject("title", productUnavailable);

        return mv;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handlerException(Exception ex) {
        ModelAndView mv = new ModelAndView(error);

        // TODO remove in production
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        ex.printStackTrace(pw);

        mv.addObject("errorTitle", contactAdmin);
        mv.addObject("errorDescription", sw.toString());
        mv.addObject("title", error.toUpperCase() + "!");

        return mv;
    }
}
