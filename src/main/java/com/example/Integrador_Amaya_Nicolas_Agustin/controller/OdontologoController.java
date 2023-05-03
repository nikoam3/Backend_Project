package com.example.Integrador_Amaya_Nicolas_Agustin.controller;


import com.example.Integrador_Amaya_Nicolas_Agustin.model.dao.OdontologoDAO;
import com.example.Integrador_Amaya_Nicolas_Agustin.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {
    @Autowired
    OdontologoService odontologoService;

    @GetMapping("/{id}")
    public ResponseEntity<OdontologoDAO> findById(@PathVariable ("id") Long id){
        if (odontologoService.findById(id).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(odontologoService.findById(id), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<OdontologoDAO> findAll(){
        if (odontologoService.findAll().isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(odontologoService.findAll(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> save(@RequestBody OdontologoDAO odontologoDAO) {
        if(odontologoService.save(odontologoDAO)){
            return ResponseEntity.ok(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping
    public ResponseEntity<?> update(@RequestBody OdontologoDAO odontologoDAO) {
        if(odontologoService.update(odontologoDAO)){
            return ResponseEntity.ok(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable ("id") Long id) {
        if(odontologoService.delete(id)){
            return ResponseEntity.ok(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
