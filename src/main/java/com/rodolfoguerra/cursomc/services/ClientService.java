package com.rodolfoguerra.cursomc.services;

import com.rodolfoguerra.cursomc.model.Client;
import com.rodolfoguerra.cursomc.repositories.ClientRepository;
import com.rodolfoguerra.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client findById(Long id) throws ObjectNotFoundException {
        Optional<Client> client = clientRepository.findById(id);
        return client.orElseThrow(() -> new ObjectNotFoundException("Object not found Id:" + id + ", Type: " + Client.class.getTypeName()));
    }
}