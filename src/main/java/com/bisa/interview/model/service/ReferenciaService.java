package com.bisa.interview.model.service;

import com.bisa.interview.model.dao.IClienteDao;
import com.bisa.interview.model.dao.IPersonaDao;
import com.bisa.interview.model.dao.IReferenciaDao;
import com.bisa.interview.model.entity.Cliente;
import com.bisa.interview.model.entity.Persona;
import com.bisa.interview.model.entity.Referencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

import static com.bisa.interview.model.DataUtil.CREADO;

@Service
public class ReferenciaService {

    @Autowired
    private IReferenciaDao referenciaDao;

    @Autowired
    private IClienteDao clienteDao;

    @Autowired
    private IPersonaDao personaDao;

    public Referencia save(Long clienteId, Long referenciaId) {
        Cliente cliente = clienteDao.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con ID: " + clienteId));

        Persona referencia = personaDao.findById(referenciaId)
                .orElseThrow(() -> new EntityNotFoundException("Referencia no encontrada con ID: " + referenciaId));

        Referencia nuevaReferencia = new Referencia();
        nuevaReferencia.setCliente(cliente);
        nuevaReferencia.setPersonaReferencia(referencia);
        nuevaReferencia.setEliminado(false);
        nuevaReferencia.setMotivoEliminado("Sin eliminación");

        return referenciaDao.save(nuevaReferencia);
    }

    public Integer countReferencias(Long clienteId) {
        return referenciaDao.countReferencias(clienteId);
    }

    public void delete(Long idReferencia) {
        referenciaDao.deleteById(idReferencia);
    }
}
