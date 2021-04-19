package com.rodolfoguerra.cursomc.controllers;

import com.rodolfoguerra.cursomc.model.Category;
import com.rodolfoguerra.cursomc.services.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryServiceImp categoryServiceImp;

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<?> findById(@PathVariable(value = "id") final Long id) {
        Category category = categoryServiceImp.findById(id);
        return ResponseEntity.ok(category);
    }
}