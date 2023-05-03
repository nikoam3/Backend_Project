package com.example.Integrador_Amaya_Nicolas_Agustin.repository.security;

import com.example.Integrador_Amaya_Nicolas_Agustin.model.security.Autoridad;
import com.example.Integrador_Amaya_Nicolas_Agustin.model.security.UsuarioRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface IAutoridadRepository extends JpaRepository<Autoridad, Long> {
    Optional<Autoridad> findByName(UsuarioRoles name);
}
