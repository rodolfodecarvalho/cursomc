package com.rodolfoguerra.cursomc.services;

import com.rodolfoguerra.cursomc.dto.ClientDTO;
import com.rodolfoguerra.cursomc.model.Client;
import com.rodolfoguerra.cursomc.repositories.ClientRepository;
import com.rodolfoguerra.cursomc.services.exceptions.DataIntegrityException;
import com.rodolfoguerra.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public Client findById(Long id) throws ObjectNotFoundException {
        Optional<Client> client = repository.findById(id);
        return client.orElseThrow(() -> new ObjectNotFoundException("Client not found Id:" + id + ", Type: " + Client.class.getTypeName()));
    }

    public Page<ClientDTO> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        Page<Client> all = repository.findAll(pageRequest);
        return all.map(ClientDTO::new);
    }

    public Client save(Client client) {
        return repository.save(client);
    }

    public void update(Client client) {
        Client newClient = findById(client.getId());
        updateData(newClient, client);
        repository.save(newClient);
    }

    private void updateData(Client newClient, Client client) {
        newClient.setName(client.getName());
        newClient.setEmail(client.getEmail());
    }

    public void deleteById(Long id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("You cannot delete a client");
        }
    }

    public Client fromDTO(ClientDTO clientDTO) {
        return new Client(clientDTO.getId(), clientDTO.getName(), clientDTO.getEmail(), null, null);
    }
}