package com.example.Integrador_Amaya_Nicolas_Agustin.controller;

import com.example.Integrador_Amaya_Nicolas_Agustin.model.dao.TurnoDAO;
import com.example.Integrador_Amaya_Nicolas_Agustin.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/turno")
public class TurnoController {
    @Autowired
    TurnoService turnoService;
    @GetMapping("/{id}")
    public ResponseEntity<TurnoDAO> findById(@PathVariable ("id") Long id){
        if (turnoService.findById(id).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(turnoService.findById(id), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<TurnoDAO> findAll(){
        if (turnoService.findAll().isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(turnoService.findAll(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> save(@RequestBody TurnoDAO turnoDAO) {
        if(turnoService.save(turnoDAO)){
            return ResponseEntity.ok(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping
    public ResponseEntity<?> update(@RequestBody TurnoDAO turnoDAO) {
        if(turnoService.update(turnoDAO)){
            return ResponseEntity.ok(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable ("id") Long id) {
        if(turnoService.delete(id)){
            return ResponseEntity.ok(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
