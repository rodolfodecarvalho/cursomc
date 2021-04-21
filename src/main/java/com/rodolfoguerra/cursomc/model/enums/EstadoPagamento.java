package com.rodolfoguerra.cursomc.model.enums;

import lombok.Getter;

@Getter
public enum EstadoPagamento {

    PENDENTE(1), QUITADO(2), CANCELADO(3);

    private final int code;

    EstadoPagamento(int code) {
        this.code = code;
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