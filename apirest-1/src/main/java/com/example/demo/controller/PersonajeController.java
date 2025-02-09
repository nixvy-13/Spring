package com.example.demo.controller;

import com.example.demo.model.Personaje;
import com.example.demo.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/personajes")
public class PersonajeController {

    @Autowired
    private PersonajeService service;

    @GetMapping
    public ResponseEntity<List<Personaje>> getAllPersonajes() {
        List<Personaje> personajes = service.findAll();
        return ResponseEntity.ok(personajes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personaje> getPersonajeById(@PathVariable Long id) {
        System.out.println("ID recibido: " + id);
        try {
            return service.findById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            System.err.println("Error al buscar personaje con ID " + id + ": " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
    @PostMapping
    public ResponseEntity<Personaje> createPersonaje(@RequestBody Personaje personaje) {
        if (service.existsPersonaje(personaje)) {
            return ResponseEntity.badRequest().build();
        }
        Personaje savedPersonaje = service.save(personaje);
        return ResponseEntity.ok(savedPersonaje);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personaje> updatePersonaje(@PathVariable Long id, @RequestBody Personaje personaje) {
        return service.findById(id)
                .map(existingPersonaje -> {
                    personaje.setId(id);
                    Personaje updatedPersonaje = service.save(personaje);
                    return ResponseEntity.ok(updatedPersonaje);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonaje(@PathVariable Long id) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/clase/{clase}")
    public ResponseEntity<List<Personaje>> getByClase(@PathVariable String clase) {
        List<Personaje> personajes = service.findByClase(clase);
        return ResponseEntity.ok(personajes);
    }
    
    @GetMapping("/nivel-mayor/{nivel}")
    public ResponseEntity<List<Personaje>> getByNivelGreaterThan(@PathVariable int nivel) {
        List<Personaje> personajes = service.findByNivelGreaterThan(nivel);
        return ResponseEntity.ok(personajes);
    }
    
    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<List<Personaje>> getByNombreContaining(@PathVariable String nombre) {
        List<Personaje> personajes = service.findByNombreContaining(nombre);
        return ResponseEntity.ok(personajes);
    }
    
    @GetMapping("/clase/{clase}/nivel-minimo/{nivel}")
    public ResponseEntity<List<Personaje>> getByClaseAndNivelMinimo(
            @PathVariable String clase, 
            @PathVariable int nivel) {
        List<Personaje> personajes = service.findByClaseAndNivelGreaterThanEqual(clase, nivel);
        return ResponseEntity.ok(personajes);
    }
}