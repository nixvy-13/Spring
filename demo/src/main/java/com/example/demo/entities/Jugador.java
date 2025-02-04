package com.example.demo.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Jugador {
    public Jugador(){}

    public Jugador(String nombre){
        super();
        this.nombre = nombre;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJugador;

    private String nombre;

    @OneToMany(mappedBy = "jugador", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Puntuacion> puntuaciones = new HashSet<Puntuacion>();

    public Long getIdJugador(){
        return idJugador;
    }

    public void setIdJugador(Long idJugador){
        this.idJugador = idJugador;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public Set<Puntuacion> getPuntuaciones(){
        return puntuaciones;
    }

    public void setPuntuaciones(Set<Puntuacion> puntuaciones){
        this.puntuaciones = puntuaciones;
    }

    public void addPuntuacion(Puntuacion puntuacion) {
        puntuacion.setJugador(this);
        
        if (puntuaciones.size() >= 5) {
            Puntuacion oldest = puntuaciones.stream()
                .min((p1, p2) -> p1.getFecha().compareTo(p2.getFecha()))
                .orElse(null);
                
            if (oldest != null) {
                puntuaciones.remove(oldest);
            }
        }
        
        puntuaciones.add(puntuacion);
    }
    
    public void removePuntuacion(Puntuacion puntuacion) {
        puntuaciones.remove(puntuacion);
        puntuacion.setJugador(null);
    }

    @Override
    public String toString(){
        return "Jugador [idJugador=" + idJugador + ", nombre=" + nombre + "]";
    }
}
