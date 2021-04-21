package com.rodolfoguerra.cursomc;

import com.rodolfoguerra.cursomc.model.*;
import com.rodolfoguerra.cursomc.model.enums.ClientType;
import com.rodolfoguerra.cursomc.model.enums.EstadoPagamento;
import com.rodolfoguerra.cursomc.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public static void main(String[] args) {
        SpringApplication.run(CursomcApplication.class, args);
    }

    @Override
    public void run(String... args) throws ParseException {
        Category cat1 = new Category(null, "Informática");
        Category cat2 = new Category(null, "Escritório");

        Product p1 = new Product(null, "Computador", 200.00);
        Product p2 = new Product(null, "Impressora", 500.00);
        Product p3 = new Product(null, "Mouse", 50.00);

        cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProducts().addAll(Collections.singletonList(p2));

        p1.getCategories().addAll(Collections.singletonList(cat1));
        p2.getCategories().addAll(Arrays.asList(cat1, cat2));
        p3.getCategories().addAll(Collections.singletonList(cat1));

        categoryRepository.saveAll(Arrays.asList(cat1, cat2));
        productRepository.saveAll(Arrays.asList(p1, p2, p3));

        Estado est1 = new Estado(null, "Minas Gerais");
        Estado est2 = new Estado(null, "São Paulo");

        City c1 = new City(null, "Urbelandia", est1);
        City c2 = new City(null, "São Paulo", est2);
        City c3 = new City(null, "Campinas", est2);

        est1.getCities().addAll(Collections.singletonList(c1));
        est2.getCities().addAll(Arrays.asList(c2, c3));

        estadoRepository.saveAll(Arrays.asList(est1, est2));
        cityRepository.saveAll(Arrays.asList(c1, c2, c3));

        Client cli1 = new Client(null, "Maria", "maria@email", "12345678996", ClientType.PESSOA_FISICA);

        Address address1 = new Address(null, "Rua Flores", "123", "casa", "Al", "12.456.879", cli1, c1);
        Address address2 = new Address(null, "Rua Margarida", "4564", "apto", "Ald", "12.456.879", cli1, c2);

        cli1.getAddresses().addAll(Arrays.asList(address1, address2));
        cli1.getPhones().addAll(Arrays.asList("123456", "54645"));

        clientRepository.save(cli1);
        addressRepository.saveAll(Arrays.asList(address1, address2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Pedido ped1 = new Pedido(null, sdf.parse("30/09/2021 10:32"), cli1, address1);
        Pedido ped2 = new Pedido(null, sdf.parse("01/10/2021 10:32"), cli1, address2);

        Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pagto1);

        Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("01/12/2021 10:32"), null);
        ped2.setPagamento(pagto2);

        cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

        pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
        pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

        ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
        ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
        ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

        ped1.getItens().addAll(Arrays.asList(ip1, ip2));
        ped2.getItens().addAll(Collections.singletonList(ip3));

        p1.getItens().addAll(Collections.singletonList(ip1));
        p2.getItens().addAll(Collections.singletonList(ip3));
        p3.getItens().addAll(Collections.singletonList(ip2));

        itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
    }
}