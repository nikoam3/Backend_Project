package com.example.Integrador_Amaya_Nicolas_Agustin.model.dao;

import com.example.Integrador_Amaya_Nicolas_Agustin.model.Odontologo;
import com.example.Integrador_Amaya_Nicolas_Agustin.model.Paciente;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class TurnoDAO {
    private Long id;
    private LocalDateTime fechaHora;
    private Paciente paciente;
    private Odontologo odontologo;
}