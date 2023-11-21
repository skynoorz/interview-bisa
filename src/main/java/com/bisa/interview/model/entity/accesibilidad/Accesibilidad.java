package com.bisa.interview.model.entity.accesibilidad;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Accesibilidad {
    @JsonProperty("ACCESIBILIDAD TOTAL")
    private AccesibilidadTotal accesibilidadTotal;

    @JsonProperty("REFERENCIAS TIPO CLIENTE")
    private AccesibilidadCliente accesibilidadCliente;

    public Accesibilidad() {
        this.accesibilidadCliente = new AccesibilidadCliente();
        this.accesibilidadTotal = new AccesibilidadTotal();
    }
}

