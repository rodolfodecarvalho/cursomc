package com.rodolfoguerra.cursomc.config;

import com.rodolfoguerra.cursomc.services.DBService;
import com.rodolfoguerra.cursomc.services.EmailService;
import com.rodolfoguerra.cursomc.services.MockEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile(value = "test")
public class TestConfig {

    private final DBService service;

    public TestConfig(DBService service) {
        this.service = service;
    }

    @Bean
    public boolean instantiateDatabase() throws ParseException {
        service.instantiateTestDatabase();
        return true;
    }

    @Bean
    public EmailService emailService (){
        return new MockEmailService();
    }
}
