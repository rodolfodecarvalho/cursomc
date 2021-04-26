package com.rodolfoguerra.cursomc.controllers;

import com.rodolfoguerra.cursomc.model.Pedido;
import com.rodolfoguerra.cursomc.services.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Pedido> findById(@PathVariable(value = "id") final Long id) {
        Pedido pedido = service.findById(id);
        return ResponseEntity.ok(pedido);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> save(@Valid @RequestBody Pedido pedido) {
        pedido = service.save(pedido);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pedido.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}