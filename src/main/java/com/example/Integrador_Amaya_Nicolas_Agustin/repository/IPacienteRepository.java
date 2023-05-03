package com.example.Integrador_Amaya_Nicolas_Agustin.repository;

import com.example.Integrador_Amaya_Nicolas_Agustin.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPacienteRepository extends JpaRepository<Paciente, Long> {
}
