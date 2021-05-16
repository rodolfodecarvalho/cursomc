package com.rodolfoguerra.cursomc.services;

import com.rodolfoguerra.cursomc.dto.CityDTO;
import com.rodolfoguerra.cursomc.model.City;
import com.rodolfoguerra.cursomc.repositories.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

    private final CityRepository repository;

    public CityService(CityRepository repository) {
        this.repository = repository;
    }

    public List<CityDTO> findCidade(Long estadoID){
        List<City> list = repository.findCidades(estadoID);

        return list.stream().map(CityDTO::new).collect(Collectors.toList());
    }
}