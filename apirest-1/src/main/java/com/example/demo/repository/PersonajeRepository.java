package com.example.demo.repository;

import com.example.demo.model.Personaje;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonajeRepository extends JpaRepository<Personaje, Long> {
    boolean existsByNombreAndClase(String nombre, String clase);
    
    List<Personaje> findByClase(String clase);
    
    List<Personaje> findByNivelGreaterThan(int nivel);
    
    List<Personaje> findByNombreContaining(String nombre);
    
    List<Personaje> findByClaseAndNivelGreaterThanEqual(String clase, int nivel);
}