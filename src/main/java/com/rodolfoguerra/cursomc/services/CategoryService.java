package com.rodolfoguerra.cursomc.services;

import com.rodolfoguerra.cursomc.model.Category;
import com.rodolfoguerra.cursomc.services.exceptions.ObjectNotFoundException;

public interface CategoryService {

    Category findById(Long id) throws ObjectNotFoundException;
}
