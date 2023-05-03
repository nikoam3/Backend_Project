package com.example.Integrador_Amaya_Nicolas_Agustin.model.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString

public class DireccionDAO {
    private Long id;
    private String domicilio;
    private String localidad;
    private String provincia;
}
