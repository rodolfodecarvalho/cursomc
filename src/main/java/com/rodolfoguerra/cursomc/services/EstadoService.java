package com.rodolfoguerra.cursomc.services;

import com.rodolfoguerra.cursomc.dto.EstadoDTO;
import com.rodolfoguerra.cursomc.model.Estado;
import com.rodolfoguerra.cursomc.repositories.EstadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstadoService {

    private final EstadoRepository repository;

    public EstadoService(EstadoRepository repository) {
        this.repository = repository;
    }

    public List<EstadoDTO> findAll(){
        List<Estado> list = repository.findAllByOrderByName();
        return list.stream().map(EstadoDTO::new).collect(Collectors.toList());
    }
}