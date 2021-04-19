package com.rodolfoguerra.cursomc;

import com.rodolfoguerra.cursomc.model.Category;
import com.rodolfoguerra.cursomc.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;

    public static void main(String[] args) {
        SpringApplication.run(CursomcApplication.class, args);
    }

    @Override
    public void run(String... args)  {
        Category cat1 = new Category(null, "Informática");
        Category cat2 = new Category(null, "Escritório");

        categoryRepository.saveAll(Arrays.asList(cat1, cat2));
    }
}
