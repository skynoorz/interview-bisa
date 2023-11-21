package com.bisa.interview.model.entity.accesibilidad;

import com.bisa.interview.model.entity.Cliente;
import com.bisa.interview.model.entity.Persona;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class AccesibilidadCliente {
    @JsonProperty("BUENA")
    private List<Cliente> buena;

    @JsonProperty("REGULAR")
    private List<Cliente> regular;

    @JsonProperty("MALA")
    private List<Cliente> mala;

    @JsonProperty("NULA")
    private List<Cliente> nula;

    public AccesibilidadCliente(List<Cliente> buena, List<Cliente> regular, List<Cliente> mala, List<Cliente> nula) {
        this.buena = buena;
        this.regular = regular;
        this.mala = mala;
        this.nula = nula;
    }

    public AccesibilidadCliente() {
        this.buena = new ArrayList<>();
        this.regular = new ArrayList<>();
        this.mala = new ArrayList<>();
        this.nula = new ArrayList<>();
    }
}
