package com.bisa.interview.model.service;

import com.bisa.interview.model.dao.IClienteDao;
import com.bisa.interview.model.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private IClienteDao clienteDao;

    public Cliente save(Cliente cliente) {
        return this.clienteDao.save(cliente);
    }

    public Cliente obtenerClientePorId(Long id) {
        return clienteDao.findById(id).orElse(null);
    }

    @Transactional
    public void actualizarEstado(Long id, String estado) {
        clienteDao.updateEstado(id, estado);
    }

    public List<Cliente> findAll() {
        return clienteDao.findAll();
    }
}
