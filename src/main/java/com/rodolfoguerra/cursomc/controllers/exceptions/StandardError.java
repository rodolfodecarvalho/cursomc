package com.rodolfoguerra.cursomc.controllers.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StandardError implements Serializable {

    private static final long serialVersionUID = -7815578593542444219L;

    private Integer status;
    private String msg;
    private Date timeStamp;
}