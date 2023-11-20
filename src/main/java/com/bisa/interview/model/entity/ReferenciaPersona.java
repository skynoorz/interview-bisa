package com.bisa.interview.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter @Setter
public class ReferenciaPersona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "custom-id-generator")
    @GenericGenerator(name = "custom-id-generator", strategy = "com.bisa.interview.CustomIdGenerator")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;

    private boolean eliminado;
    private String motivoEliminado;
}
