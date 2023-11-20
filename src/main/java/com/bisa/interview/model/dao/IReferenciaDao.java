package com.bisa.interview.model.dao;

import com.bisa.interview.model.entity.Referencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReferenciaDao extends JpaRepository<Referencia, Long> {
//    @Query(value = "SELECT COUNT(CLIENTE_ID)", nativeQuery = true)
//    Integer countAllReferences(Long idCliente);
}
