package com.rodolfoguerra.cursomc.model;

import lombok.*;

import javax.persistence.Id;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Data
public class Category implements Serializable {
    private static final long serialVersionUID = 8213987281589035882L;

    @Id
    Integer id;

    String name;
}
