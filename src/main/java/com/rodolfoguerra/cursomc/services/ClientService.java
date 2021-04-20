package com.rodolfoguerra.cursomc.services;

import com.rodolfoguerra.cursomc.model.Client;
import com.rodolfoguerra.cursomc.services.exceptions.ObjectNotFoundException;

public interface ClientService {

    Client findById(Long id) throws ObjectNotFoundException;
}