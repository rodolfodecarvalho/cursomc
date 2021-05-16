package com.rodolfoguerra.cursomc.dto;

import com.rodolfoguerra.cursomc.model.Estado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EstadoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    public EstadoDTO(Estado estado) {
        this.id = estado.getId();
        this.name = estado.getName();
    }
}