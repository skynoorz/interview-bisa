package com.bisa.interview.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

import static com.bisa.interview.model.DataUtil.CREADO;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Cliente{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "custom-id-generator")
    @GenericGenerator(name = "custom-id-generator", strategy = "com.bisa.interview.CustomIdGenerator")
    private Long id;

    @Email(message = "Por favor, ingrese una dirección de correo electrónico válida.")
    @NotBlank(message = "El campo de correo electrónico no puede estar en blanco.")
    private String email;

    @NotNull(message = "El campo de teléfono no puede estar nulo.")
    private Long telefono;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe estar en el pasado")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;

    @NotBlank(message = "El campo de ocupación no puede estar en blanco.")
    private String ocupacion;

    private String estado;

    public Cliente(String email, Long telefono, LocalDate fechaNacimiento, String ocupacion) {
        this.email = email;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.ocupacion = ocupacion;
        this.estado = CREADO;
    }

    public void validarEdad() {
        LocalDate fechaActual = LocalDate.now();
        if (fechaNacimiento.plusYears(18).isAfter(fechaActual)) {
            throw new IllegalArgumentException("El cliente debe ser mayor de edad");
        }
    }
}
