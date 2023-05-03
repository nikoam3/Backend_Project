package com.example.Integrador_Amaya_Nicolas_Agustin.service;


import com.example.Integrador_Amaya_Nicolas_Agustin.model.Direccion;
import com.example.Integrador_Amaya_Nicolas_Agustin.model.dao.DireccionDAO;
import com.example.Integrador_Amaya_Nicolas_Agustin.repository.IDireccionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class DireccionService implements IService<DireccionDAO>{
    @Autowired
    IDireccionRepository iDireccionRepository;
    @Autowired
    ObjectMapper mapper;
    @Override
    public List<DireccionDAO> findAll() {
        List<Direccion> direcciones = iDireccionRepository.findAll();
        List<DireccionDAO> direccionDAOS = new ArrayList<>();
        for (Direccion d : direcciones) {
            direccionDAOS.add(mapper.convertValue(d, DireccionDAO.class));
        }
        return direccionDAOS;
    }

    @Override
    public Optional<DireccionDAO> findById(Long id) {
        Optional<Direccion> direccion = iDireccionRepository.findById(id);
        DireccionDAO direccionDAO = null;
        if (direccion.isPresent()){
            direccionDAO = mapper.convertValue(direccion, DireccionDAO.class);
        }
        return Optional.ofNullable(direccionDAO);
    }

    @Override
    public Boolean save(DireccionDAO direccionDAO) {
        if (direccionDAO != null){
            iDireccionRepository.save(mapper.convertValue(direccionDAO, Direccion.class));
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Long id) {
        if (iDireccionRepository.findById(id).isPresent()){
            iDireccionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Boolean update(DireccionDAO direccionDAO) {
        if (direccionDAO != null){
            iDireccionRepository.save(mapper.convertValue(direccionDAO, Direccion.class));
            return true;
        }
        return false;
    }
}
