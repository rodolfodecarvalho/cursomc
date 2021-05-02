package com.rodolfoguerra.cursomc.config;

import com.rodolfoguerra.cursomc.services.DBService;
import com.rodolfoguerra.cursomc.services.EmailService;
import com.rodolfoguerra.cursomc.services.SmtpEmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;

import java.text.ParseException;

@Configuration
@Profile(value = "dev")
public class DevConfig {

    private final DBService service;

    private final MailSender mailSender;

    private final TemplateEngine templateEngine;

    private final JavaMailSender javaMailSender;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    public DevConfig(DBService service, MailSender mailSender, TemplateEngine templateEngine, JavaMailSender javaMailSender) {
        this.service = service;
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
    }

    @Bean
    public boolean instantiateDatabase() throws ParseException {
        if (Boolean.FALSE.equals("create".equals(strategy))) {
            return false;
        }

        service.instantiateTestDatabase();

        return true;
    }

    @Bean
    public EmailService emailService (){
        return new SmtpEmailService(templateEngine, javaMailSender, mailSender);
    }
}