package com.rodolfoguerra.cursomc.controllers;

import com.rodolfoguerra.cursomc.dto.CityDTO;
import com.rodolfoguerra.cursomc.dto.EstadoDTO;
import com.rodolfoguerra.cursomc.services.CityService;
import com.rodolfoguerra.cursomc.services.EstadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {
    private final EstadoService service;

    private final CityService cityService;

    public EstadoController(EstadoService service, CityService cityService) {
        this.service = service;
        this.cityService = cityService;
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<EstadoDTO>> findAll(){
        List<EstadoDTO> list = service.findAll();

        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{estadoId}/cidades")
    public ResponseEntity<List<CityDTO>> findCidades(@PathVariable Long estadoId){
        List<CityDTO> list = cityService.findCidade(estadoId);

        return ResponseEntity.ok(list);
    }
}