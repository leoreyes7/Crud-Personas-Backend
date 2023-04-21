package com.pr7.crudpersonas.controller;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.pr7.crudpersonas.entity.Persona;
import com.pr7.crudpersonas.service.PersonaService;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/persona/")
@CrossOrigin(origins = "http://localhost:4200")

public class PersonaController {

    @Autowired
    public PersonaService personaService;

    // Listar Personas
    /*@GetMapping ("listar")
    public ResponseEntity<List<Persona>> listarPersona(){
        return ResponseEntity.ok(personaService.findAll());
    }*/

    @GetMapping ("listar")
    public List<Persona> listarPersona(){
        return personaService.findAll();
    }

    // Listar Persona por ID
    @GetMapping ("listar/{id}")
    public ResponseEntity<Optional<Persona>> listarPersonaPorId(@PathVariable("id") Long id){
        return ResponseEntity.ok(personaService.findById(id));
    }

    // Actualizar Persona por ID
    @PutMapping("actualizar/{id}")
    public ResponseEntity <Persona> actualizarPersona(@PathVariable("id") Long id, @RequestBody Persona datosPersona){
        Persona persona = personaService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe la persona con el ID : " + id));

        persona.setNombre(datosPersona.getNombre());
        persona.setApellido(datosPersona.getApellido());
        persona.setEdad(datosPersona.getEdad());
        persona.setCorreo(datosPersona.getCorreo());

        Persona personaActualizada = personaService.save(persona);
        return ResponseEntity.ok(personaActualizada);

    }

    // Guardar Persona
    @PostMapping("agregar")
    public ResponseEntity <Persona> agregarPersona(@RequestBody Persona persona){
       return ResponseEntity.ok(personaService.save(persona));
    }

    // Eliminar Persona
    @DeleteMapping("eliminar")
    public void eliminar(@RequestBody Persona persona) {
        personaService.delete(persona);
    }

    // Eliminar por ID
    @DeleteMapping(value = "eliminar/{id}")
    public ResponseEntity<Map<String,Boolean>> deletePersona (@PathVariable("id") Long id){
        Persona persona= personaService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe la persona con el ID : " + id));
        personaService.deleteById(id);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminar",Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
        //return ResponseEntity.ok((personaService.findById(id).isEmpty()));
    }
}
