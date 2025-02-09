package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.Objects;

@Entity
public class Personaje {
    private @Id @GeneratedValue Long id;
    private String nombre;
    private String clase;
    private int nivel;

    public Personaje() {}

    public Personaje(String nombre, String clase, int nivel) {
        this.nombre = nombre;
        this.clase = clase;
        this.nivel = nivel;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getClase() { return clase; }
    public void setClase(String clase) { this.clase = clase; }
    public int getNivel() { return nivel; }
    public void setNivel(int nivel) { this.nivel = nivel; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Personaje)) return false;
        Personaje personaje = (Personaje) o;
        return Objects.equals(nombre, personaje.nombre) && 
               Objects.equals(clase, personaje.clase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, clase);
    }
}
