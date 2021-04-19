package com.rodolfoguerra.cursomc;

import com.rodolfoguerra.cursomc.model.Category;
import com.rodolfoguerra.cursomc.model.Product;
import com.rodolfoguerra.cursomc.repositories.CategoryRepository;
import com.rodolfoguerra.cursomc.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(CursomcApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Category cat1 = new Category(null, "Informática");
        Category cat2 = new Category(null, "Escritório");

        Product p1 = new Product(null, "Computador", 200.00);
        Product p2 = new Product(null, "Impressora", 500.00);
        Product p3 = new Product(null, "Mouse", 50.00);

        cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProducts().addAll(Collections.singletonList(p2));

        p1.getCategories().addAll(Collections.singletonList(cat1));
        p2.getCategories().addAll(Arrays.asList(cat1, cat2));
        p3.getCategories().addAll(Collections.singletonList(cat1));

        categoryRepository.saveAll(Arrays.asList(cat1, cat2));
        productRepository.saveAll(Arrays.asList(p1, p2, p3));
    }
}
