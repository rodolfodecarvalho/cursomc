package com.rodolfoguerra.cursomc;

import com.rodolfoguerra.cursomc.services.S3Service;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

    private final S3Service s3Service;

    public CursomcApplication(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    public static void main(String[] args) {
        SpringApplication.run(CursomcApplication.class, args);
    }

    @Override
    public void run(String... args) {
        s3Service.uploadFile("C:\\Users\\rodol\\Pictures\\rodolfo.jpg");
    }
}