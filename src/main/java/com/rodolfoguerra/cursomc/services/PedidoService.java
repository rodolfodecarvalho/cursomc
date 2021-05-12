package com.rodolfoguerra.cursomc.services;

import com.rodolfoguerra.cursomc.model.Client;
import com.rodolfoguerra.cursomc.model.PagamentoComBoleto;
import com.rodolfoguerra.cursomc.model.Pedido;
import com.rodolfoguerra.cursomc.model.Product;
import com.rodolfoguerra.cursomc.model.enums.EstadoPagamento;
import com.rodolfoguerra.cursomc.repositories.ItemPedidoRepository;
import com.rodolfoguerra.cursomc.repositories.PagamentoRepository;
import com.rodolfoguerra.cursomc.repositories.PedidoRepository;
import com.rodolfoguerra.cursomc.security.UserSS;
import com.rodolfoguerra.cursomc.services.exceptions.AuthorizationException;
import com.rodolfoguerra.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    private final EmailService emailService;

    public PedidoService(PedidoRepository repository, BoletoService boletoService, PagamentoRepository pagamentoRepository, ProductService productService, ItemPedidoRepository itemPedidoRepository, ClientService clientService, EmailService emailService) {
        this.repository = repository;
        this.boletoService = boletoService;
        this.pagamentoRepository = pagamentoRepository;
        this.productService = productService;
        this.itemPedidoRepository = itemPedidoRepository;
        this.clientService = clientService;
        this.emailService = emailService;
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

//        emailService.senderOrderConfirmationEmail(pedido);
        emailService.sendOrderConfirmationHtmlEmail(pedido);

        return pedido;
    }

    public Page<Pedido> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        UserSS user = UserService.authenticated();
        if (user == null) {
            throw new AuthorizationException("Acesso negado");
        }
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Client client =  clientService.findById(user.getId());
        return repository.findByClient(client, pageRequest);
    }
}