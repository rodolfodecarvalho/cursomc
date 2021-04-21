package com.rodolfoguerra.cursomc.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.rodolfoguerra.cursomc.model.enums.EstadoPagamento;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pagamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    private Integer estado;

    @OneToOne
    @JoinColumn(name = "pedido_id")
    @MapsId
    @JsonBackReference
    private Pedido pedido;

    public Pagamento(Long id, EstadoPagamento estado, Pedido pedido) {
        this.id = id;
        this.estado = estado.getCode();
        this.pedido = pedido;
    }

    public EstadoPagamento getEstado() {
        return EstadoPagamento.toEnum(estado);
    }

    public void setEstado(EstadoPagamento estado) {
        this.estado = estado.getCode();
    }
}