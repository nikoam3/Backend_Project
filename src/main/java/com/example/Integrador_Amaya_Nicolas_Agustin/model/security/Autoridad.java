package com.example.Integrador_Amaya_Nicolas_Agustin.model.security;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "autoridades")
public class Autoridad {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private UsuarioRoles name;

    public Autoridad(UsuarioRoles autoridadName) {
        this.name = autoridadName;
    }
}
