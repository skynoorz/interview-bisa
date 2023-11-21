package com.bisa.interview.model.entity.accesibilidad;

import com.bisa.interview.model.entity.Persona;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class AccesibilidadTotal {
    @JsonProperty("BUENA")
    private List<Persona> buena;

    @JsonProperty("REGULAR")
    private List<Persona> regular;

    @JsonProperty("MALA")
    private List<Persona> mala;

    @JsonProperty("NULA")
    private List<Persona> nula;

    public AccesibilidadTotal(List<Persona> buena, List<Persona> regular, List<Persona> mala, List<Persona> nula) {
        this.buena = buena;
        this.regular = regular;
        this.mala = mala;
        this.nula = nula;
    }

    public AccesibilidadTotal() {
        this.buena = new ArrayList<>();
        this.regular = new ArrayList<>();
        this.mala = new ArrayList<>();
        this.nula = new ArrayList<>();
    }
}
