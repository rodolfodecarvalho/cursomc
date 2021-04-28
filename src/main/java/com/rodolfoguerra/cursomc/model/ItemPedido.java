package com.rodolfoguerra.cursomc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Data
public class ItemPedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    @JsonIgnore
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

    @JsonIgnore
    public Pedido getPedido() {
        return id.getPedido();
    }

    public void setPedido(Pedido pedido) {
        id.setPedido(pedido);
    }

    public Product getProduct() {
        return id.getProduct();
    }

    public void setProduct(Product product) {
        id.setProduct(product);
    }

    public Double getSubTotal() {
        return (price - desconto) * quantity;
    }

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return getProduct().getName() +
                ", Qte: " +
                getQuantity() +
                ", Preço unitário: " +
                nf.format(getPrice()) +
                ", Subtotal: " +
                nf.format(getSubTotal()) +
                "\n";
    }
}
