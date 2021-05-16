package com.rodolfoguerra.cursomc.services;

import com.rodolfoguerra.cursomc.model.*;
import com.rodolfoguerra.cursomc.model.enums.ClientType;
import com.rodolfoguerra.cursomc.model.enums.EstadoPagamento;
import com.rodolfoguerra.cursomc.model.enums.Profile;
import com.rodolfoguerra.cursomc.repositories.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    private final BCryptPasswordEncoder pe;

    public DBService(CategoryRepository categoryRepository, ProductRepository productRepository, CityRepository cityRepository, EstadoRepository estadoRepository, ClientRepository clientRepository, AddressRepository addressRepository, PedidoRepository pedidoRepository, PagamentoRepository pagamentoRepository, ItemPedidoRepository itemPedidoRepository, BCryptPasswordEncoder pe) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.cityRepository = cityRepository;
        this.estadoRepository = estadoRepository;
        this.clientRepository = clientRepository;
        this.addressRepository = addressRepository;
        this.pedidoRepository = pedidoRepository;
        this.pagamentoRepository = pagamentoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
        this.pe = pe;
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
        Product p12 = new Product(null, "Product 12", 10.00);
        Product p13 = new Product(null, "Product 13", 10.00);
        Product p14 = new Product(null, "Product 14", 10.00);
        Product p15 = new Product(null, "Product 15", 10.00);
        Product p16 = new Product(null, "Product 16", 10.00);
        Product p17 = new Product(null, "Product 17", 10.00);
        Product p18 = new Product(null, "Product 18", 10.00);
        Product p19 = new Product(null, "Product 19", 10.00);
        Product p20 = new Product(null, "Product 20", 10.00);
        Product p21 = new Product(null, "Product 21", 10.00);
        Product p22 = new Product(null, "Product 22", 10.00);
        Product p23 = new Product(null, "Product 23", 10.00);
        Product p24 = new Product(null, "Product 24", 10.00);
        Product p25 = new Product(null, "Product 25", 10.00);
        Product p26 = new Product(null, "Product 26", 10.00);
        Product p27 = new Product(null, "Product 27", 10.00);
        Product p28 = new Product(null, "Product 28", 10.00);
        Product p29 = new Product(null, "Product 29", 10.00);
        Product p30 = new Product(null, "Product 30", 10.00);
        Product p31 = new Product(null, "Product 31", 10.00);
        Product p32 = new Product(null, "Product 32", 10.00);
        Product p33 = new Product(null, "Product 33", 10.00);
        Product p34 = new Product(null, "Product 34", 10.00);
        Product p35 = new Product(null, "Product 35", 10.00);
        Product p36 = new Product(null, "Product 36", 10.00);
        Product p37 = new Product(null, "Product 37", 10.00);
        Product p38 = new Product(null, "Product 38", 10.00);
        Product p39 = new Product(null, "Product 39", 10.00);
        Product p40 = new Product(null, "Product 40", 10.00);
        Product p41 = new Product(null, "Product 41", 10.00);
        Product p42 = new Product(null, "Product 42", 10.00);
        Product p43 = new Product(null, "Product 43", 10.00);
        Product p44 = new Product(null, "Product 44", 10.00);
        Product p45 = new Product(null, "Product 45", 10.00);
        Product p46 = new Product(null, "Product 46", 10.00);
        Product p47 = new Product(null, "Product 47", 10.00);
        Product p48 = new Product(null, "Product 48", 10.00);
        Product p49 = new Product(null, "Product 49", 10.00);
        Product p50 = new Product(null, "Product 50", 10.00);

        Category cat1 = new Category(null, "Informática");
        Category cat2 = new Category(null, "Escritório");
        Category cat3 = new Category(null, "Quarto");
        Category cat4 = new Category(null, "Armazem");
        Category cat5 = new Category(null, "Beleza");
        Category cat6 = new Category(null, "Padaria");
        Category cat7 = new Category(null, "Eletronicos");

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
        p12.getCategories().add(cat1);
        p13.getCategories().add(cat1);
        p14.getCategories().add(cat1);
        p15.getCategories().add(cat1);
        p16.getCategories().add(cat1);
        p17.getCategories().add(cat1);
        p18.getCategories().add(cat1);
        p19.getCategories().add(cat1);
        p20.getCategories().add(cat1);
        p21.getCategories().add(cat1);
        p22.getCategories().add(cat1);
        p23.getCategories().add(cat1);
        p24.getCategories().add(cat1);
        p25.getCategories().add(cat1);
        p26.getCategories().add(cat1);
        p27.getCategories().add(cat1);
        p28.getCategories().add(cat1);
        p29.getCategories().add(cat1);
        p30.getCategories().add(cat1);
        p31.getCategories().add(cat1);
        p32.getCategories().add(cat1);
        p33.getCategories().add(cat1);
        p34.getCategories().add(cat1);
        p35.getCategories().add(cat1);
        p36.getCategories().add(cat1);
        p37.getCategories().add(cat1);
        p38.getCategories().add(cat1);
        p39.getCategories().add(cat1);
        p40.getCategories().add(cat1);
        p41.getCategories().add(cat1);
        p42.getCategories().add(cat1);
        p43.getCategories().add(cat1);
        p44.getCategories().add(cat1);
        p45.getCategories().add(cat1);
        p46.getCategories().add(cat1);
        p47.getCategories().add(cat1);
        p48.getCategories().add(cat1);
        p49.getCategories().add(cat1);
        p50.getCategories().add(cat1);

        cat1.getProducts().addAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
                p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
                p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));
        cat2.getProducts().addAll(Arrays.asList(p2, p4));
        cat3.getProducts().addAll(Arrays.asList(p5, p6));
        cat4.getProducts().addAll(Arrays.asList(p1, p2, p3, p7));
        cat5.getProducts().addAll(Collections.singletonList(p8));
        cat6.getProducts().addAll(Arrays.asList(p9, p10));
        cat7.getProducts().addAll(Collections.singletonList(p11));

        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20,
                p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, p37, p38,
                p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));

        Estado est1 = new Estado(null, "Minas Gerais");
        Estado est2 = new Estado(null, "São Paulo");

        City c1 = new City(null, "Urbelandia", est1);
        City c2 = new City(null, "São Paulo", est2);
        City c3 = new City(null, "Campinas", est2);

        est1.getCities().addAll(Collections.singletonList(c1));
        est2.getCities().addAll(Arrays.asList(c2, c3));

        estadoRepository.saveAll(Arrays.asList(est1, est2));
        cityRepository.saveAll(Arrays.asList(c1, c2, c3));

        Client cli1 = new Client(null, "Maria", "rodolfoguerraster@gmail.com", "66216689371", ClientType.PESSOA_FISICA, pe.encode("123"));
        cli1.getPhones().addAll(Arrays.asList("123456", "54645"));

        Client cli2 = new Client(null, "Ana Costa", "rodolfoguerraster1@gmail.com", "66086132020", ClientType.PESSOA_FISICA, pe.encode("123"));
        cli2.addProfile(Profile.ADMIN);
        cli2.getPhones().addAll(Arrays.asList("4234234", "234234243"));

        Address address1 = new Address(null, "Rua Flores", "123", "casa", "Al", "12.456.879", cli1, c1);
        Address address2 = new Address(null, "Rua Margarida", "4564", "apto", "Ald", "12.456.879", cli1, c2);
        Address address3 = new Address(null, "Rua Flores", "546", null, "Ald", "12.534.879", cli2, c2);

        cli1.getAddresses().addAll(Arrays.asList(address1, address2));
        cli2.getAddresses().addAll(Collections.singletonList(address3));

        clientRepository.saveAll(Arrays.asList(cli1, cli2));
        addressRepository.saveAll(Arrays.asList(address1, address2, address3));

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
