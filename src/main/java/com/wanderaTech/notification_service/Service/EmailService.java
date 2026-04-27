package com.wanderaTech.notification_service.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

import org.springframework.core.io.ClassPathResource;
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

        // 5. Add inline images (LOGOS)
        addInlineImages(helper);

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

        //Email send details
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);

        // 5. Add inline images (LOGOS)
        addInlineImages(helper);

        mailSender.send(message);
    }
    // Inline Images Method (logo)
    private void addInlineImages(MimeMessageHelper helper) throws MessagingException {

        ClassPathResource facebook =
                new ClassPathResource("static/images/facebook.png");

        ClassPathResource youtube =
                new ClassPathResource("static/images/youtube.png");

        ClassPathResource x =
                new ClassPathResource("static/images/x.png");

        ClassPathResource instagram =
                new ClassPathResource("static/images/instagram.png");

        helper.addInline("facebookLogo", facebook);
        helper.addInline("youtubeLogo", youtube);
        helper.addInline("xLogo", x);
        helper.addInline("instagramLogo", instagram);


    }

    public void sendEmailToRegisteredUser(String to, String subject, String templateName, Map<String, Object> variables) throws MessagingException {
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

        // 5. Add inline images (LOGOS)
        addInlineImages(helper);

        mailSender.send(message);
    }

    //resendOtp notification
    public void sendResendOtpEmail(String to, String subject, String templateName, Map<String, Object> variables) throws MessagingException {
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

        // 5. Add inline images (LOGOS)
        addInlineImages(helper);

        mailSender.send(message);
    }
}
