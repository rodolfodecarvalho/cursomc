package com.rodolfoguerra.cursomc.controllers;

import com.rodolfoguerra.cursomc.dto.ClientDTO;
import com.rodolfoguerra.cursomc.dto.ClientNewDTO;
import com.rodolfoguerra.cursomc.model.Client;
import com.rodolfoguerra.cursomc.services.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(path = {"/page"})
    public ResponseEntity<Page<ClientDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<ClientDTO> list = clientService.findPage(page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok(list);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Client> findById(@PathVariable(value = "id") final Long id) {
        Client client = clientService.findById(id);
        return ResponseEntity.ok(client);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> save(@Valid @RequestBody ClientNewDTO clientDTO) {
        Client client = clientService.fromDTO(clientDTO);
        client = clientService.save(client);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(client.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody ClientDTO clientDTO, @PathVariable(value = "id") final Long id) {
        Client client = clientService.fromDTO(clientDTO);
        client.setId(id);
        clientService.update(client);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Client> deleteById(@PathVariable(value = "id") final Long id) {
        clientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}