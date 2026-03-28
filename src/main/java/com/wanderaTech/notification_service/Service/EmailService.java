package com.wanderaTech.notification_service.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final TemplateEngine templateEngine;
    private final JavaMailSender mailSender;

    //send  email to  customer of product  purchased
    @Async
    public void sendEmailToCustomer(String to, String subject, String templateName, Map<String, Object> variables ) throws MessagingException {

        //  Prepare thymeleaf context
        Context context = new Context();
        context.setVariables(variables);

        //  Process template
        String htmlContent = templateEngine.process(templateName, context);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper =
                new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);

        mailSender.send(message);
    }

    public void sendEmailToSeller(String to, String subject, String templateName, Map<String, Object> variables ) throws MessagingException {
        //  Prepare thymeleaf context
        Context context= new Context();
        context.setVariables(variables);

        //  Process template
        String htmlContent = templateEngine.process(templateName, context);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper =
                new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);

        mailSender.send(message);
    }
}
