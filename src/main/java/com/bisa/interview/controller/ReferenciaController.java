package com.bisa.interview.controller;

import com.bisa.interview.model.entity.Referencia;
import com.bisa.interview.model.service.ClienteService;
import com.bisa.interview.model.service.PersonaService;
import com.bisa.interview.model.service.ReferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/referencia")
public class ReferenciaController {

    @Autowired
    private ReferenciaService referenciaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PersonaService personaService;


    @PostMapping
    public ResponseEntity<?> save(@RequestBody Referencia nuevaReferencia) {
        Map<String, Object> response = new HashMap<>();

        try {
            Referencia referenciaGuardada = referenciaService.save(nuevaReferencia.getCliente().getId(), nuevaReferencia.getPersonaReferencia().getId());
            response.put("cliente", referenciaGuardada);
            response.put("mensaje", "El registro de la referencia fue satisfactorio.");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            response.put("mensaje", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
