package com.rodolfoguerra.cursomc.controllers;

import com.rodolfoguerra.cursomc.model.Pedido;
import com.rodolfoguerra.cursomc.services.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<?> findById(@PathVariable(value = "id") final Long id) {
        Pedido pedido = pedidoService.findById(id);
        return ResponseEntity.ok(pedido);
    }
}