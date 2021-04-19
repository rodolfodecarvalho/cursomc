package com.rodolfoguerra.cursomc.services.imp;

import com.rodolfoguerra.cursomc.model.Category;
import com.rodolfoguerra.cursomc.repositories.CategoryRepository;
import com.rodolfoguerra.cursomc.services.CategoryService;
import com.rodolfoguerra.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service(value = "CategoryService")
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryRepository repository;

    public Category findById(Long id) {
        Optional<Category> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found Id:" + id + ", Type: " + Category.class.getTypeName()));
    }
}