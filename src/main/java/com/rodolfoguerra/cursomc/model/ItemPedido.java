package com.rodolfoguerra.cursomc.model;

import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Data
public class ItemPedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ItemPedidoPK id = new ItemPedidoPK();

    private Double desconto;

    private Integer quantity;

    private Double price;

    public ItemPedido(Pedido pedido, Product product, Double desconto, Integer quantity, Double price) {
        super();
        this.id.setPedido(pedido);
        this.id.setProduct(product);
        this.desconto = desconto;
        this.quantity = quantity;
        this.price = price;
    }

    public Pedido getPedido() {
        return id.getPedido();
    }

    public Product getProduct() {
        return id.getProduct();
    }
}
