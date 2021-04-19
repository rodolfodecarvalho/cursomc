package com.rodolfoguerra.cursomc.repositories;

import com.rodolfoguerra.cursomc.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
