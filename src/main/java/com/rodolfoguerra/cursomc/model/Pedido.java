package com.rodolfoguerra.cursomc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Data
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date date;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
    private Pagamento pagamento;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Address enderecoDeEntrega;

    @OneToMany(mappedBy = "id.pedido")
    private Set<ItemPedido> itens = new HashSet<>();

    public Pedido(Long id, Date date, Client client, Address enderecoDeEntrega) {
        super();
        this.id = id;
        this.date = date;
        this.client = client;
        this.enderecoDeEntrega = enderecoDeEntrega;
    }

    public Optional<Double> getValorTotal() {
        return itens.stream().map(ItemPedido::getSubTotal).reduce(Double::sum);
    }

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        final StringBuffer sb = new StringBuffer("Pedido{");
        sb.append("Pedido número: ");
        sb.append(getId());
        sb.append(", Instante: ");
        sb.append(sdf.format(getDate()));
        sb.append(", Cliente: ");
        sb.append(getClient().getName());
        sb.append(", Situação do pagamento: ");
        sb.append(getPagamento().getEstado().getCode());
        sb.append("\nDetalhes:\n");

        getItens().forEach(ip -> sb.append(ip.toString()));

        sb.append("Valor total: ");
        sb.append(getValorTotal());

        return sb.toString();
    }
}