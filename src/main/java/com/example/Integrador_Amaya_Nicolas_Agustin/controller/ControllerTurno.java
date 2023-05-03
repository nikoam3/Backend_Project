package com.example.Integrador_Amaya_Nicolas_Agustin.controller;

import com.example.Clase28.model.Turno;
import com.example.Clase28.repository.implement.TurnoDaoH2;
import com.example.Clase28.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/turno")
public class ControllerTurno {
    @Autowired
    private final TurnoService turnoService;
    public ControllerTurno(TurnoService turnoService) {
        this.turnoService = turnoService;
        turnoService.setTurnoIdao(new TurnoDaoH2());
    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Turno> buscarTurno(@PathVariable ("id") Long id){
        ResponseEntity response = null;
        if (turnoService.listar(id) == null){
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            response = new ResponseEntity(turnoService.listar(id), HttpStatus.OK);
        }
        return response;
    }
    @GetMapping("/listartodos")
    public ResponseEntity<List<Turno>> listarTodosTurnos(){
        ResponseEntity response = null;
        if (turnoService.listarTodos().size() == 0){
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            response = new ResponseEntity(turnoService.listarTodos(), HttpStatus.OK);
        }
        System.out.println(response);

        return response;
    }
    @PostMapping("/agregar")
    public ResponseEntity<Turno> agregarTurno(@RequestBody Turno turno){
        Turno turnoAgregar = turnoService.agregar(turno);
        ResponseEntity response = null;

        if (turnoAgregar.getOdontologo() == null || turnoAgregar.getPaciente() == null){
            response = new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else if (turnoAgregar == null) {
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        } else{
            response = new ResponseEntity(turnoAgregar, HttpStatus.CREATED);
        }
        return response;
    }
    @PutMapping("/modificar/{id}")
    public ResponseEntity<Turno> modificarTurno(@PathVariable ("id") Long id, @RequestBody Turno turno){
        ResponseEntity response = null;
        if (turnoService.listar(id) == null){
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            turnoService.modificar(id, turno);
            response = new ResponseEntity(turnoService.listar(id), HttpStatus.CREATED);
        }
        return response;
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable ("id") Long id){
        ResponseEntity response = null;
        if (turnoService.listar(id) == null){
            //response = new ResponseEntity(HttpStatus.NOT_FOUND);
            return ResponseEntity.status(400).body("Id de turno incorrecto");
        }else{
            turnoService.eliminar(id);
            //response = new ResponseEntity(HttpStatus.NO_CONTENT);
            return ResponseEntity.status(204).body("Turno eliminado correctamente");
        }
    }
}
