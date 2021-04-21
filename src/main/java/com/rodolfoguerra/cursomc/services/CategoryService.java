package com.rodolfoguerra.cursomc.services;

import com.rodolfoguerra.cursomc.model.Category;
import com.rodolfoguerra.cursomc.repositories.CategoryRepository;
import com.rodolfoguerra.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService  {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category findById(Long id) {
        Optional<Category> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found Id:" + id + ", Type: " + Category.class.getTypeName()));
    }
}