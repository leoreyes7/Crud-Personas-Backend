package com.pr7.crudpersonas.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table (name = "persona")

public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String apellido;
    private int edad;
    private String correo;
}
