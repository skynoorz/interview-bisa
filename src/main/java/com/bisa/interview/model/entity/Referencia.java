package com.bisa.interview.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Referencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "custom-id-generator")
    @GenericGenerator(name = "custom-id-generator", strategy = "com.bisa.interview.CustomIdGenerator")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "persona_referencia_id")
    private Persona personaReferencia;

    private boolean eliminado;
    private String motivoEliminado;
}

