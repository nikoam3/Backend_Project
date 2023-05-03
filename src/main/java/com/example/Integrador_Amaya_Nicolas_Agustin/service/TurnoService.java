package com.example.Integrador_Amaya_Nicolas_Agustin.service;

import com.example.Integrador_Amaya_Nicolas_Agustin.model.Paciente;
import com.example.Integrador_Amaya_Nicolas_Agustin.model.Turno;
import com.example.Integrador_Amaya_Nicolas_Agustin.model.dao.TurnoDAO;
import com.example.Integrador_Amaya_Nicolas_Agustin.repository.ITurnoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TurnoService implements IService<TurnoDAO> {
    @Autowired
    ITurnoRepository iTurnoRepository;
    @Autowired
    ObjectMapper mapper;

    @Override
    public List<TurnoDAO> findAll() {
        List<Turno> turnos = iTurnoRepository.findAll(Sort.by(Sort.Order.by("fechaHora")));
        List<TurnoDAO> turnoDAOS = new ArrayList<>();
        for (Turno t : turnos) {
            turnoDAOS.add(mapper.convertValue(t, TurnoDAO.class));
        }
        return turnoDAOS;
    }

    @Override
    public Optional<TurnoDAO> findById(Long id) {
        Optional<Turno> turno = iTurnoRepository.findById(id);
        TurnoDAO turnoDAO = null;
        if (turno.isPresent()){
            turnoDAO = mapper.convertValue(turno, TurnoDAO.class);
        }
        return Optional.ofNullable(turnoDAO);
    }

    @Override
    public Boolean save(TurnoDAO turnoDAO) {
        if (turnoDAO != null) {
            iTurnoRepository.save(mapper.convertValue(turnoDAO, Turno.class));
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Long id) {
        if (iTurnoRepository.findById(id).isPresent()){
            iTurnoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Boolean update(TurnoDAO turnoDAO) {
        if (turnoDAO != null) {
            iTurnoRepository.save(mapper.convertValue(turnoDAO, Turno.class));
            return true;
        }
        return false;
    }
}
