package com.bisa.interview.model.dao;

import com.bisa.interview.model.entity.ReferenciaCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IReferenciaClienteDao extends JpaRepository<ReferenciaCliente, Long> {
//    @Query(value = "SELECT COUNT(CLIENTE_ID)", nativeQuery = true)
//    Integer countAllReferences(Long idCliente);
}
