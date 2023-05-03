package com.example.Integrador_Amaya_Nicolas_Agustin.controller;

import com.example.Clase28.model.Paciente;
import com.example.Clase28.repository.implement.PacienteDaoH2;
import com.example.Clase28.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //se puede retornar cualquier tipo de datos
@RequestMapping("/paciente")
//@Controller //solo devuelvo plantillas, no acepta otro tipo de return
public class ControllerPaciente {
    @Autowired
    private final PacienteService pacienteService;

    public ControllerPaciente(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
        pacienteService.setPacienteIdao(new PacienteDaoH2());
    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Paciente> buscarPaciente(@PathVariable ("id") Long id){
        ResponseEntity response = null;
        if (pacienteService.listar(id) == null){
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            response = new ResponseEntity(pacienteService.listar(id), HttpStatus.OK);
        }
        return response;
    }
    @GetMapping("/listartodos")
    public ResponseEntity<List<Paciente>> listarTodosPacientes(){
        ResponseEntity response = null;
        if (pacienteService.listarTodos().size() == 0){
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            response = new ResponseEntity(pacienteService.listarTodos(), HttpStatus.OK);
        }
        return response;
    }
    @PostMapping("/agregar")
    public ResponseEntity<Paciente> agregarPaciente(@RequestBody Paciente paciente){
        Paciente pacienteAgreagar = pacienteService.agregar(paciente);
        ResponseEntity response = null;
        if (pacienteAgreagar == null){
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            response = new ResponseEntity(pacienteAgreagar, HttpStatus.CREATED);
        }
        return response;
    }
    @PutMapping("/modificar/{id}")
    public ResponseEntity<Paciente> modificarOdontologo(@PathVariable ("id") long id, @RequestBody Paciente paciente){
        ResponseEntity response = null;
        if (pacienteService.listar(id) == null){
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            pacienteService.modificar(id, paciente);
            response = new ResponseEntity(pacienteService.listar(id), HttpStatus.CREATED);
        }
        return response;
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable ("id") Long id){
        ResponseEntity response = null;
        if (pacienteService.listar(id) == null){
            //response = new ResponseEntity(HttpStatus.NOT_FOUND);
            return ResponseEntity.status(400).body("Id de paciente incorrecto");
        }else{
            pacienteService.eliminar(id);
            //response = new ResponseEntity(HttpStatus.NO_CONTENT);
            return ResponseEntity.status(204).body("Paciente eliminado correctamente");
        }
    }


//    @GetMapping("/index") //FUNCIONA SOLO CON @CONTROLLER
//    public String welcome(Model model) {
//    model.addAttribute("nombre", "pim pam pum");
//    model.addAttribute("apellido", "pim pam pum");
//    return "index";
//    }
}
