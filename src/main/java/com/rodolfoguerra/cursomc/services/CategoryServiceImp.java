package com.rodolfoguerra.cursomc.services;

import com.rodolfoguerra.cursomc.model.Category;
import com.rodolfoguerra.cursomc.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service(value = "CategoryService")
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryRepository repository;

    public Category findById(Long id) {
        Optional<Category> obj = repository.findById(id);
        return obj.orElse(null);
    }
}