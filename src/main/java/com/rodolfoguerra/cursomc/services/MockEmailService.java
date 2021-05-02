package com.rodolfoguerra.cursomc.services;

import lombok.extern.log4j.Log4j2;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;

import javax.mail.internet.MimeMessage;

@Log4j2
public class MockEmailService extends AbstractEmailService{


    public MockEmailService(TemplateEngine templateEngine, JavaMailSender javaMailSender) {
        super(templateEngine, javaMailSender);
    }

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        log.info("Simulando envio de email");
        log.info(msg.toString());
        log.info("Email sent");
    }

    @Override
    public void sendHtmlEmail(MimeMessage msg) {
        log.info("Simulando envio de email html");
        log.info(msg.toString());
        log.info("Email sent");
    }
}