package com.bisa.interview.model.dao;

import com.bisa.interview.model.entity.ReferenciaPersona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IReferenciaPersonaDao extends JpaRepository<ReferenciaPersona, Long> {
//    @Query(value = "SELECT COUNT(CLIENTE_ID)")
//    Integer countAllReferences(Long idCliente);
}
