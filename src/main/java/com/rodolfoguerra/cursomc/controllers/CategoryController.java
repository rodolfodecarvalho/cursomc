package com.rodolfoguerra.cursomc.controllers;

import com.rodolfoguerra.cursomc.model.Category;
import com.rodolfoguerra.cursomc.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> save(@Validated @RequestBody Category obj){
        obj = categoryService.save(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}