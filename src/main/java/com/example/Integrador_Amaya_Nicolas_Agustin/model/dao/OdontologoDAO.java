package com.example.Integrador_Amaya_Nicolas_Agustin.model.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Setter
@Getter
@ToString
public class OdontologoDAO {
    private Long id;
    private Long matricula;
    private String nombre;
    private String apellido;
}


