package com.example.Integrador_Amaya_Nicolas_Agustin.service;

import com.example.Integrador_Amaya_Nicolas_Agustin.model.Odontologo;
import com.example.Integrador_Amaya_Nicolas_Agustin.model.dao.OdontologoDAO;
import com.example.Integrador_Amaya_Nicolas_Agustin.repository.IOdontologoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class OdontologoService implements IService<OdontologoDAO>{
    @Autowired
    IOdontologoRepository iOdontologoRepository;
    @Autowired
    ObjectMapper mapper;
    @Override
    public List<OdontologoDAO> findAll() {
        List<Odontologo> odontologos = iOdontologoRepository.findAll(Sort.by(Sort.Order.by("apellido")));
        List<OdontologoDAO> odontologoDAOS = new ArrayList<>();
        for (Odontologo o : odontologos) {
            odontologoDAOS.add(mapper.convertValue(o, OdontologoDAO.class));
        }
        return odontologoDAOS;
    }

    @Override
    public Optional<OdontologoDAO> findById(Long id) {
        Optional<Odontologo> odontologo = iOdontologoRepository.findById(id);
        OdontologoDAO odontologoDAO = null;
        if (odontologo.isPresent()){
            odontologoDAO = mapper.convertValue(odontologo, OdontologoDAO.class);
        }
        return Optional.ofNullable(odontologoDAO);
    }

    @Override
    public Boolean save(OdontologoDAO odontologoDAO) {
        if (odontologoDAO != null){
            iOdontologoRepository.save(mapper.convertValue(odontologoDAO, Odontologo.class));
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(Long id) {
        if (iOdontologoRepository.findById(id).isPresent()){
            iOdontologoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Boolean update(OdontologoDAO odontologoDAO) {
        if (odontologoDAO != null){
            iOdontologoRepository.save(mapper.convertValue(odontologoDAO, Odontologo.class));
            return true;
        }
        return false;
    }
}
