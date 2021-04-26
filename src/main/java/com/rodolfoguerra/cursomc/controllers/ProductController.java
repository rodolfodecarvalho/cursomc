package com.rodolfoguerra.cursomc.controllers;

import com.rodolfoguerra.cursomc.controllers.utils.URL;
import com.rodolfoguerra.cursomc.dto.ProductDTO;
import com.rodolfoguerra.cursomc.model.Product;
import com.rodolfoguerra.cursomc.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Product> findById(@PathVariable(value = "id") final Long id) {
        Product product = service.findById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping(path = {"/page"})
    public ResponseEntity<Page<ProductDTO>> findPage(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "categories", defaultValue = "") String categories,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        List<Long> list = URL.decoderList(categories);
        String nameDecode = URL.decodeParam(name);
        Page<ProductDTO> listDTO = service.search(nameDecode, list, page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok(listDTO);
    }
}