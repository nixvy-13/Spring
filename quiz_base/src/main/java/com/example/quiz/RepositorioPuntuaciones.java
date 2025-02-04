package com.example.quiz;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface RepositorioPuntuaciones extends CrudRepository<Puntuacion, Long> {
    List<Puntuacion> findAll();
}
