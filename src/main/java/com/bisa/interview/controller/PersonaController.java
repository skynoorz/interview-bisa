package com.bisa.interview.controller;

import com.bisa.interview.model.entity.Persona;
import com.bisa.interview.model.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @PostMapping("/persona")
    public ResponseEntity<?> createPersona(@RequestBody @Valid Persona persona, BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = new ArrayList<>();
            for (FieldError err : result.getFieldErrors()) {
                errors.add(err.getDefaultMessage());
            }
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        Persona personaNew = personaService.save(persona);
        response.put("cliente", personaNew);
        response.put("mensaje", "El registro de la persona fue satisfactorio.");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
