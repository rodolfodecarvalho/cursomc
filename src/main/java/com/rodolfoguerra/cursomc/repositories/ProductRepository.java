package com.rodolfoguerra.cursomc.repositories;

import com.rodolfoguerra.cursomc.model.Category;
import com.rodolfoguerra.cursomc.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}