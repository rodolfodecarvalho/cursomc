package com.rodolfoguerra.cursomc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
@Data
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "Product_Category", joinColumns = @JoinColumn(name = "productId"), inverseJoinColumns = @JoinColumn(name = "categoryId"))
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "id.product")
    @JsonIgnore
    private Set<ItemPedido> itens = new HashSet<>();

    public Product(Long id, String name, double price) {
        super();
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @JsonIgnore
    public List<Pedido> getPedidos() {
        List<Pedido> pedidos = new ArrayList<>();
        for (ItemPedido x : itens) {
            pedidos.add(x.getPedido());
        }

        return pedidos;
    }
}
