package com.rodolfoguerra.cursomc.services;

import com.rodolfoguerra.cursomc.model.Pedido;
import com.rodolfoguerra.cursomc.repositories.PedidoRepository;
import com.rodolfoguerra.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository repository;

    public PedidoService(PedidoRepository repository) {
        this.repository = repository;
    }

    public Pedido findById(Long id) {
        Optional<Pedido> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found Id:" + id + ", Type: " + Pedido.class.getTypeName()));
    }
}