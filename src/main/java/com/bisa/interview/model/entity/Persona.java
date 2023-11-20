package com.bisa.interview.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Getter @Setter
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "custom-id-generator")
    @GenericGenerator(name = "custom-id-generator", strategy = "com.bisa.interview.CustomIdGenerator")
    private Long id;

    @NotNull(message = "El nombre es obligatorio")
    @Size(min = 2, max = 40, message = "El nombre debe tener como maximo 40 caracteres")
    private String nombre;

    @NotNull(message = "El apellido paterno es obligatorio")
    @Size(min = 2, max = 30, message = "El apellido paterno debe tener como maximo 30 caracteres")
    private String apPaterno;

    @Size(max = 30, message = "El apellido materno debe tener como maximo 30 caracteres")
    private String apMaterno;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe estar en el pasado")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate fechaNacimiento;

    @Size(max = 50, message = "La direccion debe tener como maximo 50 caracteres")
    private String direccion;

    private Long carnet;

    public Persona(String nombre, String apPaterno, String apMaterno, LocalDate fechaNacimiento, String direccion, Long carnet) {
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.carnet = carnet;
    }
}
