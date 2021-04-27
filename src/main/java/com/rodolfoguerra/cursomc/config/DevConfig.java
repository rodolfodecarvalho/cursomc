package com.rodolfoguerra.cursomc.config;

import com.rodolfoguerra.cursomc.services.DBService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile(value = "dev")
public class DevConfig {

    private final DBService service;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    public DevConfig(DBService service) {
        this.service = service;
    }

    @Bean
    public boolean instantiateDatabase() throws ParseException {
        if (Boolean.FALSE.equals("create".equals(strategy))) {
            return false;
        }

        service.instantiateTestDatabase();

        return true;
    }
}
