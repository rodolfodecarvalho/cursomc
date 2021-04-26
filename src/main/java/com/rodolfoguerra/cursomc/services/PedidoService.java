package com.rodolfoguerra.cursomc.services;

import com.rodolfoguerra.cursomc.model.PagamentoComBoleto;
import com.rodolfoguerra.cursomc.model.Pedido;
import com.rodolfoguerra.cursomc.model.enums.EstadoPagamento;
import com.rodolfoguerra.cursomc.repositories.ItemPedidoRepository;
import com.rodolfoguerra.cursomc.repositories.PagamentoRepository;
import com.rodolfoguerra.cursomc.repositories.PedidoRepository;
import com.rodolfoguerra.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository repository;
    private final BoletoService boletoService;
    private final PagamentoRepository pagamentoRepository;
    private final ProductService productService;
    private final ItemPedidoRepository itemPedidoRepository;

    public PedidoService(PedidoRepository repository, BoletoService boletoService, PagamentoRepository pagamentoRepository, ProductService productService, ItemPedidoRepository itemPedidoRepository) {
        this.repository = repository;
        this.boletoService = boletoService;
        this.pagamentoRepository = pagamentoRepository;
        this.productService = productService;
        this.itemPedidoRepository = itemPedidoRepository;
    }

    public Pedido findById(Long id) {
        Optional<Pedido> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found Id:" + id + ", Type: " + Pedido.class.getTypeName()));
    }

    public Pedido save(Pedido pedido) {
        pedido.setId(null);
        pedido.setDate(new Date());
        pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        pedido.getPagamento().setPedido(pedido);

        if (pedido.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pagto = (PagamentoComBoleto) pedido.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto, pedido.getDate());
        }

        repository.save(pedido);
        pagamentoRepository.save(pedido.getPagamento());

        pedido.getItens().forEach(ip -> {
            ip.setDesconto(0.0);
            ip.setPrice(productService.findById(ip.getProduct().getId()).getPrice());
            ip.setPedido(pedido);
        });

        itemPedidoRepository.saveAll(pedido.getItens());

        return pedido;
    }
}