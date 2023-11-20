package com.bisa.interview.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import java.time.LocalDate;

import static com.bisa.interview.model.DataUtil.CREADO;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Cliente extends Persona{

    @Email(message = "Por favor, ingrese una dirección de correo electrónico válida.")
    private String email;

    private Long telefono;

    private String ocupacion;

    private String estado;

    public Cliente(String nombre, String apPaterno, String apMaterno, LocalDate fechaNacimiento, String direccion, Long carnet, String email, Long telefono, String ocupacion) {
        super(nombre, apPaterno, apMaterno, fechaNacimiento, direccion, carnet);
        this.email = email;
        this.telefono = telefono;
        this.ocupacion = ocupacion;
        this.estado = CREADO;
    }

//    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<Referencia> referencias = new HashSet<>();

    public void validarEdad() {
        LocalDate fechaActual = LocalDate.now();
        if (getFechaNacimiento().plusYears(18).isAfter(fechaActual)) {
            throw new IllegalArgumentException("El cliente debe ser mayor de edad");
        }
    }
}
