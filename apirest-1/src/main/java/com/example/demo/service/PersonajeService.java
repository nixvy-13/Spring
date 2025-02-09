package com.example.demo.service;

import com.example.demo.model.Personaje;
import com.example.demo.repository.PersonajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PersonajeService {
    
    @Autowired
    private PersonajeRepository repository;

    public List<Personaje> findAll() {
        return repository.findAll();
    }

    public Optional<Personaje> findById(Long id) {
        return repository.findById(id);
    }

    public boolean existsPersonaje(Personaje personaje) {
        return repository.existsByNombreAndClase(personaje.getNombre(), personaje.getClase());
    }

    public Personaje save(Personaje personaje) {
        return repository.save(personaje);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    public List<Personaje> findByClase(String clase) {
        return repository.findByClase(clase);
    }
    
    public List<Personaje> findByNivelGreaterThan(int nivel) {
        return repository.findByNivelGreaterThan(nivel);
    }
    
    public List<Personaje> findByNombreContaining(String nombre) {
        return repository.findByNombreContaining(nombre);
    }
    
    public List<Personaje> findByClaseAndNivelGreaterThanEqual(String clase, int nivel) {
        return repository.findByClaseAndNivelGreaterThanEqual(clase, nivel);
    }
}