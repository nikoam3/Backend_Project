package com.example.Integrador_Amaya_Nicolas_Agustin.controller;

import com.example.Clase28.model.Direccion;
import com.example.Clase28.repository.implement.DireccionDaoH2;
import com.example.Clase28.service.DireccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/direccion")
public class ControllerDireccion {

    @Autowired
    private final DireccionService direccionService;
    public ControllerDireccion(DireccionService direccionService) {
    this.direccionService = direccionService;
    direccionService.setDireccionIdao(new DireccionDaoH2());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Direccion> buscarDireccion(@PathVariable ("id") Long id){
        ResponseEntity response = null;
        if (direccionService.listar(id) == null){
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            response = new ResponseEntity(direccionService.listar(id), HttpStatus.OK);
        }
        return response;
    }
    @GetMapping("/listartodos")
    public ResponseEntity<List<Direccion>> listarTodosDirecciones(){
        ResponseEntity response = null;
        if (direccionService.listarTodos().size() == 0){
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            response = new ResponseEntity(direccionService.listarTodos(), HttpStatus.OK);
        }
        return response;
    }
    @PostMapping("/agregar")
    public ResponseEntity<Direccion> agregarDireccion(@RequestBody Direccion direccion){
        Direccion direccionAgreagar = direccionService.agregar(direccion);
        ResponseEntity response = null;
        if (direccionService == null){
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            response = new ResponseEntity(direccionAgreagar, HttpStatus.CREATED);
        }
        return response;
    }
    @PutMapping("/modificar/{id}")
    public ResponseEntity<Direccion> modificarDireccion(@PathVariable ("id") long id, @RequestBody Direccion direccion){
        ResponseEntity response = null;
        if (direccionService.listar(id) == null){
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            direccionService.modificar(id, direccion);
            response = new ResponseEntity(direccionService.listar(id), HttpStatus.CREATED);
        }
        return response;
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarDireccion(@PathVariable ("id") Long id){
        ResponseEntity response = null;
        if (direccionService.listar(id) == null){
            //response = new ResponseEntity(HttpStatus.NOT_FOUND);
            return ResponseEntity.status(400).body("Id de direccion incorrecto");
        }else{
            direccionService.eliminar(id);
            //response = new ResponseEntity(HttpStatus.NO_CONTENT);
            return ResponseEntity.status(204).body("Direccion eliminado correctamente");
        }
    }
}
