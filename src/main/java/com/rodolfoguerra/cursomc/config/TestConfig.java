package com.rodolfoguerra.cursomc.config;

import com.rodolfoguerra.cursomc.services.DBService;
import com.rodolfoguerra.cursomc.services.EmailService;
import com.rodolfoguerra.cursomc.services.MockEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;

import java.text.ParseException;

@Configuration
@Profile(value = "test")
public class TestConfig {

    private final DBService service;

    private final TemplateEngine templateEngine;

    private final JavaMailSender javaMailSender;

    public TestConfig(DBService service, TemplateEngine templateEngine, JavaMailSender javaMailSender) {
        this.service = service;
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
    }

    @Bean
    public boolean instantiateDatabase() throws ParseException {
        service.instantiateTestDatabase();
        return true;
    }

    @Bean
    public EmailService emailService (){
        return new MockEmailService(templateEngine, javaMailSender);
    }
}
