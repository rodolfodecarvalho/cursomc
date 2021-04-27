package com.rodolfoguerra.cursomc.config;

import com.rodolfoguerra.cursomc.services.DBService;
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
}
