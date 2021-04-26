package com.rodolfoguerra.cursomc.controllers;

import com.rodolfoguerra.cursomc.dto.CategoryDTO;
import com.rodolfoguerra.cursomc.model.Category;
import com.rodolfoguerra.cursomc.services.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping(path = {"/"})
    public ResponseEntity<List<CategoryDTO>> findAll() {
        List<CategoryDTO> list = service.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping(path = {"/page"})
    public ResponseEntity<Page<CategoryDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<CategoryDTO> list = service.findPage(page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok(list);
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Category> findById(@PathVariable(value = "id") final Long id) {
        Category category = service.findById(id);
        return ResponseEntity.ok(category);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> save(@Valid @RequestBody CategoryDTO categoryDTO) {
        Category category = service.fromDTO(categoryDTO);
        category = service.save(category);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable(value = "id") final Long id) {
        Category category = service.fromDTO(categoryDTO);
        category.setId(id);
        service.update(category);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Category> deleteById(@PathVariable(value = "id") final Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}