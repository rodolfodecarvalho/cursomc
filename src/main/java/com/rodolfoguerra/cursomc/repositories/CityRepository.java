package com.rodolfoguerra.cursomc.repositories;

import com.rodolfoguerra.cursomc.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {

    @Transactional(readOnly = true)
    @Query("SELECT obj FROM City obj WHERE obj.estado.id = :estadoId ORDER BY obj.name")
    List<City> findCidades(@Param("estadoId") Long estadoId);
}