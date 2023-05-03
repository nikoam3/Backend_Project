package com.example.Integrador_Amaya_Nicolas_Agustin.repository;



import com.example.Integrador_Amaya_Nicolas_Agustin.model.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDireccionRepository extends JpaRepository<Direccion, Long> {
}