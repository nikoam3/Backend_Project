package com.example.Integrador_Amaya_Nicolas_Agustin.repository.security;

import com.example.Integrador_Amaya_Nicolas_Agustin.model.security.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
//@Transactional(readOnly = true)
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String email);
}
