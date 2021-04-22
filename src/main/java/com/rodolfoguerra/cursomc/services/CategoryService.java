package com.rodolfoguerra.cursomc.services;

import com.rodolfoguerra.cursomc.dto.CategoryDTO;
import com.rodolfoguerra.cursomc.model.Category;
import com.rodolfoguerra.cursomc.repositories.CategoryRepository;
import com.rodolfoguerra.cursomc.services.exceptions.DataIntegrityException;
import com.rodolfoguerra.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<CategoryDTO> findAll() {
        List<Category> list = repository.findAll();
//        return list.stream().map(category -> new CategoryDTO(category)).collect(Collectors.toList());
        return list.stream().map(CategoryDTO::new).collect(Collectors.toList());
    }

    public Page<CategoryDTO> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        Page<Category> all = repository.findAll(pageRequest);
        return all.map(CategoryDTO::new);
    }

    public Category findById(Long id) {
        Optional<Category> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found Id:" + id + ", Type: " + Category.class.getTypeName()));
    }

    public Category save(Category obj) {
        return repository.save(obj);
    }

    public void update(Category obj) {
        findById(obj.getId());
        repository.save(obj);
    }

    public void deleteById(Long id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("You cannot delete a category that has a product");
        }
    }

    public Category fromDTO(CategoryDTO categoryDTO) {
        return new Category(categoryDTO.getId(), categoryDTO.getName());
    }
}