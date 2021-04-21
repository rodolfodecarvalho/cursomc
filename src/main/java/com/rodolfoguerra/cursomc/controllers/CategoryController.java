package com.rodolfoguerra.cursomc.controllers;

import com.rodolfoguerra.cursomc.dto.CategoryDTO;
import com.rodolfoguerra.cursomc.model.Category;
import com.rodolfoguerra.cursomc.services.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(path = {"/"})
    public ResponseEntity<List<CategoryDTO>> findAll() {
        List<CategoryDTO> list = categoryService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping(path = {"/page"})
    public ResponseEntity<Page<CategoryDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<CategoryDTO> list = categoryService.findPage(page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok(list);
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Category> findById(@PathVariable(value = "id") final Long id) {
        Category category = categoryService.findById(id);
        return ResponseEntity.ok(category);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> save(@Validated @RequestBody Category obj) {
        obj = categoryService.save(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Void> update(@RequestBody Category obj, @PathVariable(value = "id") final Long id) {
        obj.setId(id);
        categoryService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Category> deleteById(@PathVariable(value = "id") final Long id) {
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}