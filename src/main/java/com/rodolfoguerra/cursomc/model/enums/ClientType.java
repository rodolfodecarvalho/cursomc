package com.rodolfoguerra.cursomc.model.enums;

import lombok.Getter;

@Getter
public enum ClientType {
    PESSOA_FISICA(0), PESSOA_JURIDICA(1);

    private final int code;

    ClientType(int code) {
        this.code = code;
    }

    public static ClientType toEnum(Integer code) {
        if (code == null) {
            return null;
        }

        for (ClientType clientType : ClientType.values()) {
            if (code.equals(clientType.getCode())) {
                return clientType;
            }
        }

        throw new IllegalArgumentException("Id invalid: " + code);
    }
}