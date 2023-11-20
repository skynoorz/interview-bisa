package com.bisa.interview.model.service;

import com.bisa.interview.model.dao.IReferenciaClienteDao;
import com.bisa.interview.model.dao.IReferenciaPersonaDao;
import com.bisa.interview.model.entity.ReferenciaCliente;
import com.bisa.interview.model.entity.ReferenciaPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReferenciaService {

    @Autowired
    private IReferenciaClienteDao referenciaClienteDao;

    @Autowired
    private IReferenciaPersonaDao referenciaPersonaDao;

//    public Integer countReferencias(Long idCliente) {
//        return clienteDao.countAllReferences(idCliente) + personaDao.countAllReferences(idCliente);
//    }

    public ReferenciaPersona save(ReferenciaPersona nuevaReferencia) {
        ReferenciaPersona referenciaGuardada = referenciaPersonaDao.save(nuevaReferencia);
        return referenciaGuardada;
    }
}
