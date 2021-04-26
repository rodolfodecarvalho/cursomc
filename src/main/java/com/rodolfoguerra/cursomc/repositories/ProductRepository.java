package com.rodolfoguerra.cursomc.repositories;

import com.rodolfoguerra.cursomc.model.Category;
import com.rodolfoguerra.cursomc.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Transactional(readOnly = true)
//    @Query("SELECT DISTINCT prod FROM Product prod INNER JOIN prod.categories cat WHERE prod.name LIKE %:name% AND cat IN :categories")
    Page<Product> findDistinctByNameContainingAndCategoriesIn(String name, List<Category> categories, Pageable pageRequest);
}
