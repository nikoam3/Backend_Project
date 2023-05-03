package com.example.Integrador_Amaya_Nicolas_Agustin.model.dao;

import com.example.Integrador_Amaya_Nicolas_Agustin.model.Direccion;
import com.example.Integrador_Amaya_Nicolas_Agustin.model.Odontologo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Setter
@Getter
@ToString
public class PacienteDAO {
    private Long id;
    private String nombre;
    private String apellido;
    private Long dni;
    private LocalDate fechaAlta;
    private Direccion direccion;
    //private Odontologo odontologo;
}

