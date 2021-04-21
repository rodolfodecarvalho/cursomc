package com.rodolfoguerra.cursomc.controllers;

import com.rodolfoguerra.cursomc.model.Category;
import com.rodolfoguerra.cursomc.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<?> findById(@PathVariable(value = "id") final Long id) {
        Category category = categoryService.findById(id);
        return ResponseEntity.ok(category);
    }
}