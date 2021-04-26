package com.rodolfoguerra.cursomc.services;

import com.rodolfoguerra.cursomc.dto.ProductDTO;
import com.rodolfoguerra.cursomc.model.Category;
import com.rodolfoguerra.cursomc.model.Product;
import com.rodolfoguerra.cursomc.repositories.CategoryRepository;
import com.rodolfoguerra.cursomc.repositories.ProductRepository;
import com.rodolfoguerra.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    public Product findById(Long id) {
        Optional<Product> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found Id:" + id + ", Type: " + Product.class.getTypeName()));
    }

    public Page<ProductDTO> search(String name, List<Long> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        List<Category> categories = categoryRepository.findAllById(ids);

        Page<Product> search = repository.findDistinctByNameContainingAndCategoriesIn(name, categories, pageRequest);

        return search.map(ProductDTO::new);
    }
}