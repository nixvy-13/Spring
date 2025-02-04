package com.example.quiz;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Puntuacion {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;
  private String nombre_jugador;
  private String nombre_personaje;

  protected Puntuacion() {}
  public Puntuacion(String nombre_jugador, String nombre_personaje) {
    this.nombre_jugador = nombre_jugador;
    this.nombre_personaje = nombre_personaje;
  }

  @Override
  public String toString() {
    return String.format(
        "Customer[id=%d, firstName='%s', lastName='%s']",
        id, nombre_jugador, nombre_personaje);
  }

  public Integer getId() {
    return id;
  }

  public String getNombre_jugador() {
    return nombre_jugador;
  }

  public String getNombre_personaje() {
    return nombre_personaje;
  }
  public void setId(Integer id) {
	    this.id = id;
	  }
  public void setNombre_jugador(String nombre_jugador) {
	    this.nombre_jugador = nombre_jugador;
	  }
  public void setNombre_personaje(String nombre_personaje) {
	    this.nombre_personaje = nombre_personaje;
	  }
}
