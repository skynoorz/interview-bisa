package com.bisa.interview.controller;

import com.bisa.interview.model.entity.accesibilidad.Accesibilidad;
import com.bisa.interview.model.entity.Cliente;
import com.bisa.interview.model.entity.Persona;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, @RequestBody Map<String, String> motivoEliminacion) {
        Map<String, Object> response = new HashMap<>();
        try {
            String motivo = motivoEliminacion.get("motivo");
            referenciaService.delete(id, motivo);
            String newEstado = updateEstado(id);
            response.put("nuevo_estado", newEstado);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar referencia en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El registro de la referencia con id: '".concat(id.toString().concat("' se eliminó correctamente")));
        return new ResponseEntity<Map>(response, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<Accesibilidad> getReferenciasByAccessibilidad() {
        Map<String, Object> response = new HashMap<>();
        Accesibilidad accesibilidad = new Accesibilidad();

        // Lista para REFERENCIAS DE TIPO CLIENTE
        List<Cliente> clienteList = clienteService.findAll();
        for (Cliente cliente : clienteList) {
            Integer totalReferencias = referenciaService.countReferenciasCliente(cliente.getId());
            if (totalReferencias >= 2)
                accesibilidad.getAccesibilidadCliente().getBuena().add(cliente);
            if (totalReferencias >= 1)
                accesibilidad.getAccesibilidadCliente().getBuena().add(cliente);
            if (totalReferencias == 1)
                accesibilidad.getAccesibilidadCliente().getRegular().add(cliente);
            if (totalReferencias == 0) {
                accesibilidad.getAccesibilidadCliente().getRegular().add(cliente);
                accesibilidad.getAccesibilidadCliente().getMala().add(cliente);
                accesibilidad.getAccesibilidadCliente().getNula().add(cliente);
            }
        }
        // Lista para el TOTAL REFERENCIAS
        List<Persona> personaList = personaService.findAll();
        for (Persona persona : personaList) {
            Integer totalReferencias = referenciaService.countReferenciasPersona(persona.getId());
            if (totalReferencias >= 2) {
                accesibilidad.getAccesibilidadTotal().getBuena().add(persona);
                accesibilidad.getAccesibilidadTotal().getRegular().add(persona);
            }
            if (totalReferencias >= 3)
                accesibilidad.getAccesibilidadTotal().getBuena().add(persona);
            if (totalReferencias == 1) {
                accesibilidad.getAccesibilidadTotal().getRegular().add(persona);
                accesibilidad.getAccesibilidadTotal().getMala().add(persona);
            }
            if (totalReferencias == 0)
                accesibilidad.getAccesibilidadTotal().getNula().add(persona);
        }

        return new ResponseEntity<>(accesibilidad, HttpStatus.OK);
    }

    private String updateEstado(Long idCliente) {
        Integer references = referenciaService.countReferenciasCliente(idCliente);
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
