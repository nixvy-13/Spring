package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.entities.Puntuacion;

public interface RepositorioPuntuaciones extends CrudRepository<Puntuacion, Long> {
    
    List<Puntuacion> findAll();
    
    List<Puntuacion> findByJugadorNombreOrderByFechaDesc(String nombre);
}
