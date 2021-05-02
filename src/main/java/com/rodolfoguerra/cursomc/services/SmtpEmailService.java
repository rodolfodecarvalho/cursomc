package com.rodolfoguerra.cursomc.services;

import lombok.extern.log4j.Log4j2;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;

import javax.mail.internet.MimeMessage;

@Log4j2
public class SmtpEmailService extends AbstractEmailService {

    private final MailSender mailSender;
    private final JavaMailSender javaMailSender;

    public SmtpEmailService(TemplateEngine templateEngine, JavaMailSender javaMailSender, MailSender mailSender) {
        super(templateEngine, javaMailSender);
        this.mailSender = mailSender;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        log.info("Envio de email");
        mailSender.send(msg);
        log.info("Email sent");
    }

    @Override
    public void sendHtmlEmail(MimeMessage msg) {
        log.info("Envio de email");
        javaMailSender.send(msg);
        log.info("Email sent");
    }
}
