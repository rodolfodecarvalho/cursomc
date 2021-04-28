package com.rodolfoguerra.cursomc.services;

import com.rodolfoguerra.cursomc.model.PagamentoComBoleto;
import com.rodolfoguerra.cursomc.model.Pedido;
import com.rodolfoguerra.cursomc.model.Product;
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
    private final ClientService clientService;

    public PedidoService(PedidoRepository repository, BoletoService boletoService, PagamentoRepository pagamentoRepository, ProductService productService, ItemPedidoRepository itemPedidoRepository, ClientService clientService) {
        this.repository = repository;
        this.boletoService = boletoService;
        this.pagamentoRepository = pagamentoRepository;
        this.productService = productService;
        this.itemPedidoRepository = itemPedidoRepository;
        this.clientService = clientService;
    }

    public Pedido findById(Long id) {
        Optional<Pedido> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found Id:" + id + ", Type: " + Pedido.class.getTypeName()));
    }

    public Pedido save(Pedido pedido) {
        pedido.setId(null);
        pedido.setDate(new Date());
        pedido.setClient(clientService.findById(pedido.getClient().getId()));
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
            Product product = productService.findById(ip.getProduct().getId());
            ip.setProduct(product);
            ip.setPrice(product.getPrice());
            ip.setPedido(pedido);
        });

        itemPedidoRepository.saveAll(pedido.getItens());

        System.out.println(pedido);

        return pedido;
    }
}