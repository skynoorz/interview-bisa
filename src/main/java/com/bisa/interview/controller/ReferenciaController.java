package com.bisa.interview.controller;

import com.bisa.interview.model.entity.Cliente;
import com.bisa.interview.model.entity.Persona;
import com.bisa.interview.model.entity.ReferenciaPersona;
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
    public ResponseEntity<?> crearNuevaReferencia(@RequestBody ReferenciaPersona nuevaReferencia) {
        Map<String, Object> response = new HashMap<>();

        Cliente cliente = clienteService.obtenerClientePorId(nuevaReferencia.getCliente().getId());
        Persona persona = personaService.obtenerPersonaPorId(nuevaReferencia.getPersona().getId());

        if (null != cliente && null != persona) {
            nuevaReferencia.setCliente(cliente);
            nuevaReferencia.setPersona(persona);
            ReferenciaPersona referenciaGuardada = referenciaService.save(nuevaReferencia);
            response.put("cliente", referenciaGuardada);
            response.put("mensaje", "El registro de la referencia fue satisfactorio.");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }

        response.put("mensaje", "No se encontro el cliente o la persona con los ids solicitados");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
