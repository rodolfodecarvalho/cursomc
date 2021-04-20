package com.rodolfoguerra.cursomc.repositories;

import com.rodolfoguerra.cursomc.model.Address;
import com.rodolfoguerra.cursomc.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
