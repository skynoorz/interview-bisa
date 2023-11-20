package com.bisa.interview.model.dao;

import com.bisa.interview.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface IClienteDao extends JpaRepository<Cliente, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Cliente c SET c.estado = :estadoNuevo WHERE c.id = :id")
    void updateEstado(Long id, String estadoNuevo);
}
