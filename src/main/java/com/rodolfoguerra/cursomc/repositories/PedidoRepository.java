package com.rodolfoguerra.cursomc.repositories;

import com.rodolfoguerra.cursomc.model.Client;
import com.rodolfoguerra.cursomc.model.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    @Transactional(readOnly=true)
    Page<Pedido> findByClient(Client client, Pageable pageRequest);
}