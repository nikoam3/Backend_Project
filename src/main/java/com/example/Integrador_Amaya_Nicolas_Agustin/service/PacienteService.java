package com.example.Integrador_Amaya_Nicolas_Agustin.service;



import com.example.Integrador_Amaya_Nicolas_Agustin.model.Paciente;
import com.example.Integrador_Amaya_Nicolas_Agustin.model.dao.PacienteDAO;
import com.example.Integrador_Amaya_Nicolas_Agustin.repository.IPacienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PacienteService implements IService<PacienteDAO>{
    @Autowired
    IPacienteRepository iPacienteRepository;
    @Autowired
    ObjectMapper mapper;

    @Override
    public List<PacienteDAO> findAll() {
        List<Paciente> pacientes = iPacienteRepository.findAll(Sort.by(Sort.Order.by("apellido")));
        List<PacienteDAO> pacienteDAOS = new ArrayList<>();
        for (Paciente p : pacientes) {
            pacienteDAOS.add(mapper.convertValue(p, PacienteDAO.class));
        }
        return pacienteDAOS;
    }

    @Override
    public Optional<PacienteDAO> findById(Long id) {
        Optional<Paciente> paciente = iPacienteRepository.findById(id);
        PacienteDAO pacienteDAO = null;
        if (paciente.isPresent()){
            pacienteDAO = mapper.convertValue(paciente, PacienteDAO.class);
        }
        return Optional.ofNullable(pacienteDAO);
    }

    @Override
    public Boolean save(PacienteDAO pacienteDAO) {
        if (pacienteDAO != null) {
            iPacienteRepository.save(mapper.convertValue(pacienteDAO, Paciente.class));
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Long id) {
        if (iPacienteRepository.findById(id).isPresent()){
            iPacienteRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Boolean update(PacienteDAO pacienteDAO) {
        if (pacienteDAO != null) {
            iPacienteRepository.save(mapper.convertValue(pacienteDAO, Paciente.class));
            return true;
        }
        return false;
    }
}
