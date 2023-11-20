package com.bisa.interview.controller;

import com.bisa.interview.model.entity.Referencia;
import com.bisa.interview.model.service.ClienteService;
import com.bisa.interview.model.service.PersonaService;
import com.bisa.interview.model.service.ReferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;

import static com.bisa.interview.model.DataUtil.ACTIVO;
import static com.bisa.interview.model.DataUtil.BLOQUEADO;

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

        Long idCliente = nuevaReferencia.getCliente().getId();
        try {
            Referencia referenciaGuardada = referenciaService.save(idCliente, nuevaReferencia.getPersonaReferencia().getId());
            response.put("cliente", referenciaGuardada);
            String newEstado = updateEstado(idCliente);
            response.put("nuevo_estado", newEstado);
            response.put("mensaje", "El registro de la referencia fue satisfactorio.");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            response.put("mensaje", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            referenciaService.delete(id);
            String newEstado = updateEstado(id);
            response.put("nuevo_estado", newEstado);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar referenca en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El registro de la referencia con id: '".concat(id.toString().concat("' se elimino correctamente")));
        return new ResponseEntity<Map>(response, HttpStatus.OK);
    }

    private String updateEstado(Long idCliente) {
        Integer references = referenciaService.countReferencias(idCliente);
        if (references >= 1) {
            clienteService.actualizarEstado(idCliente, ACTIVO);
            return ACTIVO;
        } else if (references == 0) {
            clienteService.actualizarEstado(idCliente, BLOQUEADO);
            return BLOQUEADO;
        }
        return BLOQUEADO;
    }
}
