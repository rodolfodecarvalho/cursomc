package com.rodolfoguerra.cursomc.controllers;

import com.rodolfoguerra.cursomc.dto.ClientDTO;
import com.rodolfoguerra.cursomc.dto.ClientNewDTO;
import com.rodolfoguerra.cursomc.model.Client;
import com.rodolfoguerra.cursomc.services.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(path = {"/page"})
    public ResponseEntity<Page<ClientDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<ClientDTO> list = service.findPage(page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok(list);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Client> findById(@PathVariable(value = "id") final Long id) {
        Client client = service.findById(id);
        return ResponseEntity.ok(client);
    }

    @GetMapping(path = "/email")
    public ResponseEntity<Client> find(@RequestParam(value = "value") String email) {
        Client obj = service.findByEmail(email);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> save(@Valid @RequestBody ClientNewDTO clientDTO) {
        Client client = service.fromDTO(clientDTO);
        client = service.save(client);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(client.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody ClientDTO clientDTO, @PathVariable(value = "id") final Long id) {
        Client client = service.fromDTO(clientDTO);
        client.setId(id);
        service.update(client);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Client> deleteById(@PathVariable(value = "id") final Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "picture")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name = "file") MultipartFile multipartFile) {
        URI uri = service.uploadProfilePicture(multipartFile);
        return ResponseEntity.created(uri).build();
    }
}