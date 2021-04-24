package com.rodolfoguerra.cursomc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientNewDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String email;
    private String cpfOrCnpj;
    private Integer type;

    private String logradouro;
    private String number;
    private String complemento;
    private String bairro;
    private String zipCode;

    private String phone1;
    private String phone2;
    private String phone3;

    private Long cityId;
}