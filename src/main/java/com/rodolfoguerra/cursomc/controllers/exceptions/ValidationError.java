package com.rodolfoguerra.cursomc.controllers.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class ValidationError extends StandardError implements Serializable {

    private static final long serialVersionUID = -1L;

    private List<FieldMessage> errors = new ArrayList<>();


    public ValidationError(Integer status, String msg, Date timeStamp) {
        super(status, msg, timeStamp);
    }

    public void addError(String name, String message) {
        errors.add(new FieldMessage(name, message));
    }
}