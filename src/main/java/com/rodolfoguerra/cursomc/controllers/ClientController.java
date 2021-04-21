package com.rodolfoguerra.cursomc.controllers;

import com.rodolfoguerra.cursomc.model.Client;
import com.rodolfoguerra.cursomc.services.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService serviceImp;

    public ClientController(ClientService serviceImp) {
        this.serviceImp = serviceImp;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Client> findById(@PathVariable(value = "id") final Long id) {
        Client client = serviceImp.findById(id);
        return ResponseEntity.ok(client);
    }
}