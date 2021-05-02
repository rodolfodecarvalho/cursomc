package com.rodolfoguerra.cursomc.model.enums;

import lombok.Getter;

@Getter
public enum Profile {

    ADMIN(1,"ROLE_ADMIN"), CLIENT(2, "ROLE_CLIENT");

    private final int code;
    private String description;

    Profile(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static Profile toEnum(Integer code) {
        if (code == null) {
            return null;
        }

        for (Profile profile : Profile.values()) {
            if (code.equals(profile.getCode())) {
                return profile;
            }
        }

        throw new IllegalArgumentException("Id invalid: " + code);
    }
}