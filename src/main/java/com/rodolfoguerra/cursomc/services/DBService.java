package com.rodolfoguerra.cursomc.services;

import com.rodolfoguerra.cursomc.model.*;
import com.rodolfoguerra.cursomc.model.enums.ClientType;
import com.rodolfoguerra.cursomc.model.enums.EstadoPagamento;
import com.rodolfoguerra.cursomc.repositories.*;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;

@Service
public class DBService {

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    private final CityRepository cityRepository;

    private final EstadoRepository estadoRepository;

    private final ClientRepository clientRepository;

    private final AddressRepository addressRepository;

    private final PedidoRepository pedidoRepository;

    private final PagamentoRepository pagamentoRepository;

    private final ItemPedidoRepository itemPedidoRepository;

    public DBService(CategoryRepository categoryRepository, ProductRepository productRepository, CityRepository cityRepository, EstadoRepository estadoRepository, ClientRepository clientRepository, AddressRepository addressRepository, PedidoRepository pedidoRepository, PagamentoRepository pagamentoRepository, ItemPedidoRepository itemPedidoRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.cityRepository = cityRepository;
        this.estadoRepository = estadoRepository;
        this.clientRepository = clientRepository;
        this.addressRepository = addressRepository;
        this.pedidoRepository = pedidoRepository;
        this.pagamentoRepository = pagamentoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
    }

    public void instantiateTestDatabase() throws ParseException {
        Product p1 = new Product(null, "Computador", 2000.00);
        Product p2 = new Product(null, "Impressora", 500.00);
        Product p3 = new Product(null, "Mouse", 80.00);
        Product p4 = new Product(null, "Mesa de escritório", 300.00);
        Product p5 = new Product(null, "Toalha", 50.00);
        Product p6 = new Product(null, "Colcha", 200.00);
        Product p7 = new Product(null, "TV true color", 1200.00);
        Product p8 = new Product(null, "Roçadeira", 800.00);
        Product p9 = new Product(null, "Abajour", 100.00);
        Product p10 = new Product(null, "Pendente", 180.00);
        Product p11 = new Product(null, "Shampoo", 90.00);

        Category cat1 = new Category(null, "Informática");
        Category cat2 = new Category(null, "Escritório");
        Category cat3 = new Category(null, "Quarto");
        Category cat4 = new Category(null, "Armazem");
        Category cat5 = new Category(null, "Beleza");
        Category cat6 = new Category(null, "Padaria");
        Category cat7 = new Category(null, "Eletronicos");

        cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProducts().addAll(Arrays.asList(p2, p4));

        p1.getCategories().addAll(Collections.singletonList(cat1));
        p2.getCategories().addAll(Arrays.asList(cat1, cat2));
        p3.getCategories().addAll(Collections.singletonList(cat1));

        cat2.getProducts().addAll(Arrays.asList(p2, p4));
        cat3.getProducts().addAll(Arrays.asList(p5, p6));
        cat4.getProducts().addAll(Arrays.asList(p1, p2, p3, p7));
        cat5.getProducts().addAll(Collections.singletonList(p8));
        cat6.getProducts().addAll(Arrays.asList(p9, p10));
        cat7.getProducts().addAll(Collections.singletonList(p11));

        p1.getCategories().addAll(Arrays.asList(cat1, cat4));
        p2.getCategories().addAll(Arrays.asList(cat1, cat2, cat4));
        p3.getCategories().addAll(Arrays.asList(cat1, cat4));
        p4.getCategories().addAll(Collections.singletonList(cat2));
        p5.getCategories().addAll(Collections.singletonList(cat3));
        p6.getCategories().addAll(Collections.singletonList(cat3));
        p7.getCategories().addAll(Collections.singletonList(cat4));
        p8.getCategories().addAll(Collections.singletonList(cat5));
        p9.getCategories().addAll(Collections.singletonList(cat6));
        p10.getCategories().addAll(Collections.singletonList(cat6));
        p11.getCategories().addAll(Collections.singletonList(cat7));

        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

        Estado est1 = new Estado(null, "Minas Gerais");
        Estado est2 = new Estado(null, "São Paulo");

        City c1 = new City(null, "Urbelandia", est1);
        City c2 = new City(null, "São Paulo", est2);
        City c3 = new City(null, "Campinas", est2);

        est1.getCities().addAll(Collections.singletonList(c1));
        est2.getCities().addAll(Arrays.asList(c2, c3));

        estadoRepository.saveAll(Arrays.asList(est1, est2));
        cityRepository.saveAll(Arrays.asList(c1, c2, c3));

        Client cli1 = new Client(null, "Maria", "rodolfoguerraster@gmail.com", "12345678996", ClientType.PESSOA_FISICA);

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
