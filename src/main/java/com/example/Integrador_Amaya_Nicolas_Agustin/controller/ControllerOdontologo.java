package com.example.Integrador_Amaya_Nicolas_Agustin.controller;

import com.example.Clase28.model.Odontologo;
import com.example.Clase28.repository.implement.OdontologoDaoH2;
import com.example.Clase28.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/odontologo")
public class ControllerOdontologo {
    @Autowired
    private final OdontologoService odontologoService;
    public ControllerOdontologo(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
        odontologoService.setOdontologoIDao(new OdontologoDaoH2());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Odontologo> buscarOdontologo(@PathVariable ("id") Long id){
        ResponseEntity response = null;
        if (odontologoService.listar(id) == null){
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            response = new ResponseEntity(odontologoService.listar(id), HttpStatus.OK);
        }
        return response;
    }
    @GetMapping("/listartodos")
    public ResponseEntity<List<Odontologo>> listarTodosOdontologo(){
        ResponseEntity response = null;
        if (odontologoService.listarTodos().size() == 0){
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            response = new ResponseEntity(odontologoService.listarTodos(), HttpStatus.OK);
        }
        System.out.println(response);

        return response;
    }
    @PostMapping("/agregar")
    public ResponseEntity<Odontologo> agregarOdontologo(@RequestBody Odontologo odontologo){
        Odontologo odontologoAgregar = odontologoService.agregar(odontologo);
        ResponseEntity response = null;
        if (odontologoAgregar == null){
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            response = new ResponseEntity(odontologoAgregar, HttpStatus.CREATED);
        }
        return response;
    }
    @PutMapping("/modificar/{id}")
    public ResponseEntity<Odontologo> modificarOdontologo(@PathVariable ("id") long id, @RequestBody Odontologo odontologo){
        ResponseEntity response = null;
        if (odontologoService.listar(id) == null){
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            odontologoService.modificar(id, odontologo);
            response = new ResponseEntity(odontologoService.listar(id), HttpStatus.CREATED);
        }
        return response;
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable ("id") Long id){
        ResponseEntity response = null;
        if (odontologoService.listar(id) == null){
            //response = new ResponseEntity(HttpStatus.NOT_FOUND);
            return ResponseEntity.status(400).body("Id de odontologo incorrecto");
        }else{
            odontologoService.eliminar(id);
            //response = new ResponseEntity(HttpStatus.NO_CONTENT);
            return ResponseEntity.status(204).body("Odontologo eliminado correctamente");
        }
    }
}
