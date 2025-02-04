package com.example.demo.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Puntuacion {

  public Puntuacion() {}
  public Puntuacion(String nombre_personaje) {
    super();
    this.nombre_personaje = nombre_personaje;
  }
  public Puntuacion(Jugador jugador, String nombre_personaje) {
    super();
    this.jugador = jugador;
    this.nombre_personaje = nombre_personaje;
  }

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer idPunt;

  private String nombre_personaje;
  private LocalDateTime fecha;
  
  @ManyToOne
  private Jugador jugador;

  public Integer getId() {
    return idPunt;
  }

  public void setIdPunt(Integer idPunt) {
    this.idPunt = idPunt;
  }

  public Jugador getJugador() {
    return jugador;
  }

  public void setJugador (Jugador jugador) {
    this.jugador = jugador;
  }

  public String getNombre_personaje() {
    return nombre_personaje;
  }

  public void setNombre_personaje(String nombre_personaje) {
    this.nombre_personaje = nombre_personaje;
  }

  public LocalDateTime getFecha() {
    return fecha;
  }
  public void setFecha(LocalDateTime fecha) {
    this.fecha = fecha;
  }

  @Override
    public String toString() {
        return String.format(
            "Puntuacion[id=%d, jugador='%s', personaje='%s', fecha='%s']",
            idPunt, jugador.getNombre(), nombre_personaje, fecha);
    }
}
