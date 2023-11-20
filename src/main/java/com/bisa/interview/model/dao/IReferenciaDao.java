package com.bisa.interview.model.dao;

import com.bisa.interview.model.entity.Referencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IReferenciaDao extends JpaRepository<Referencia, Long> {
    @Query("SELECT COUNT(r) FROM Referencia r WHERE r.cliente.id = :clienteId AND r.eliminado <> true")
    Integer countReferencias(@Param("clienteId") Long clienteId);
}
