package com.rodolfoguerra.cursomc.model.enums;

import lombok.Getter;

@Getter
public enum EstadoPagamento {

    PENDENTE(1,"Pendente"), QUITADO(2, "Quitado"), CANCELADO(3, "Cancelado");

    private final int code;
    private String description;

    EstadoPagamento(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static EstadoPagamento toEnum(Integer code) {
        if (code == null) {
            return null;
        }

        for (EstadoPagamento estadoPagamento : EstadoPagamento.values()) {
            if (code.equals(estadoPagamento.getCode())) {
                return estadoPagamento;
            }
        }

        throw new IllegalArgumentException("Id invalid: " + code);
    }
}