package com.rodolfoguerra.cursomc.repositories;

import com.rodolfoguerra.cursomc.model.Client;
import com.rodolfoguerra.cursomc.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
