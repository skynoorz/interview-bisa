package com.bisa.interview.model.service;

import com.bisa.interview.model.dao.IPersonaDao;
import com.bisa.interview.model.entity.Cliente;
import com.bisa.interview.model.entity.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaService {

    @Autowired
    private IPersonaDao personaDao;

    public Persona save(Persona persona) {
        return this.personaDao.save(persona);
    }

    public Persona obtenerPersonaPorId(Long id) {
        return personaDao.findById(id).orElse(null);
    }

    public List<Persona> findAll() {
        return personaDao.findAll();
    }
}
