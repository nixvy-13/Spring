package com.example.demo.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Jugador;
 
@Repository
public interface RepositoryJugador extends CrudRepository<Jugador, Long> {
    List<Jugador> findByNombre(String nombre);
}
