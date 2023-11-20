package com.bisa.interview.model.dao;

import com.bisa.interview.model.entity.Referencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface IReferenciaDao extends JpaRepository<Referencia, Long> {
    @Query("SELECT COUNT(r) FROM Referencia r WHERE r.cliente.id = :clienteId AND r.eliminado <> true")
    Integer countReferencias(@Param("clienteId") Long clienteId);

    @Modifying
    @Transactional
    @Query("UPDATE Referencia r SET r.eliminado = true, r.motivoEliminado = :motivo WHERE r.id = :idReferencia")
    void updateEliminado(@Param("idReferencia") Long idReferencia, @Param("motivo") String motivo);
}
