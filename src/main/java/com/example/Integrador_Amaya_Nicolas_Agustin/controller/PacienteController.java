package com.example.Integrador_Amaya_Nicolas_Agustin.controller;


import com.example.Integrador_Amaya_Nicolas_Agustin.model.dao.PacienteDAO;
import com.example.Integrador_Amaya_Nicolas_Agustin.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //se puede retornar cualquier tipo de datos
@RequestMapping("/paciente")
//@Controller //solo devuelvo plantillas, no acepta otro tipo de return
public class PacienteController {
    @Autowired
    PacienteService pacienteService;

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDAO> findById(@PathVariable ("id") Long id){
        if (pacienteService.findById(id).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(pacienteService.findById(id), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<PacienteDAO> findAll(){
        if (pacienteService.findAll().isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(pacienteService.findAll(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> save(@RequestBody PacienteDAO pacienteDAO) {
        if(pacienteService.save(pacienteDAO)){
            return ResponseEntity.ok(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping
    public ResponseEntity<?> update(@RequestBody PacienteDAO pacienteDAO) {
        if(pacienteService.update(pacienteDAO)){
            return ResponseEntity.ok(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable ("id") Long id) {
        if(pacienteService.delete(id)){
            return ResponseEntity.ok(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
