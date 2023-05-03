package com.example.Integrador_Amaya_Nicolas_Agustin.controller;


import com.example.Integrador_Amaya_Nicolas_Agustin.model.dao.DireccionDAO;
import com.example.Integrador_Amaya_Nicolas_Agustin.service.DireccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/direccion")
public class DireccionController {
    @Autowired
    DireccionService direccionService;

    @GetMapping("/{id}")
    public ResponseEntity<DireccionDAO> findById(@PathVariable ("id") Long id){
        if (direccionService.findById(id).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(direccionService.findById(id), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<DireccionDAO> findAll(){
        if (direccionService.findAll().isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(direccionService.findAll(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> save(@RequestBody DireccionDAO direccionDAO) {
        if(direccionService.save(direccionDAO)){
            return ResponseEntity.ok(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping
    public ResponseEntity<?> update(@RequestBody DireccionDAO direccionDAO) {
        if(direccionService.update(direccionDAO)){
            return ResponseEntity.ok(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable ("id") Long id) {
        if(direccionService.delete(id)){
            return ResponseEntity.ok(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
