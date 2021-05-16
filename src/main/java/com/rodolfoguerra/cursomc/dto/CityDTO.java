package com.rodolfoguerra.cursomc.dto;

import com.rodolfoguerra.cursomc.model.City;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CityDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    public CityDTO(City city) {
        this.id = city.getId();
        this.name = city.getName();
    }
}