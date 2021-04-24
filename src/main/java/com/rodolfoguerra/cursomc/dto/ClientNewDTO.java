package com.rodolfoguerra.cursomc.dto;

import com.rodolfoguerra.cursomc.services.validation.ClientInsert;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ClientInsert
public class ClientNewDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Required")
    @Size(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
    private String name;

    @NotEmpty(message = "Required")
    @Email(message = "Invalid email")
    private String email;

    @NotEmpty(message = "Required")
    private String cpfOrCnpj;

    private Integer type;

    @NotEmpty(message = "Required")
    private String logradouro;

    @NotEmpty(message = "Required")
    private String number;

    private String complemento;
    private String bairro;

    @NotEmpty(message = "Required")
    private String zipCode;

    @NotEmpty(message = "Required")
    private String phone1;

    private String phone2;
    private String phone3;

    private Long cityId;
}