package com.rodolfoguerra.cursomc.services;

import lombok.extern.log4j.Log4j2;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

@Log4j2
public class SmtpEmailService extends AbstractEmailService {

    private final MailSender mailSender;

    public SmtpEmailService(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        log.info("Envio de email");
        mailSender.send(msg);
        log.info("Email sent");
    }
}
