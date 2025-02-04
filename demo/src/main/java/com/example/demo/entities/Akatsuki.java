package com.example.demo.entities;

public class Akatsuki {

    public enum Personajes {
        ITACHI(0),
        DEIDARA(0),
        OROCHIMARU(0),
        SASORI(0),
        TOBI(0);

        // Campo para almacenar la puntuación de cada personaje
        private int puntuacion;

        // Constructor para asignar la puntuación
        Personajes(int puntuacion) {
            this.puntuacion = puntuacion;
        }

        // Método para obtener la puntuación
        public int getPuntuacion() {
            return puntuacion;
        }

        // Método para establecer una nueva puntuación
        public void setPuntuacion(int puntuacion) {
            this.puntuacion = puntuacion;
        }

        // Método para incrementar la puntuación
        public void incrementarPuntuacion() {
            this.puntuacion++;
        }

        // Método estático para incrementar la puntuación de un personaje por su nombre
        public static void incrementarPuntuacionPorNombre(String nombre) {
            for (Personajes personaje : Personajes.values()) {
                if (personaje.name().equals(nombre)) {
                    personaje.incrementarPuntuacion();
                    break;
                }
            }
        }
        public void resetPuntuacion() {
            this.puntuacion = 0;
        }
    }

    public String nombre_jugador = null;
    public boolean sab_pod = false;
    public String nombre_ranking = null;
    
    public String getNombreJugador() {
    	return nombre_jugador; 
    }
    public void setNombreJugador(String nombre_jugador) {
    	this.nombre_jugador=nombre_jugador;
    }
    public void setNombreRanking(String nombre_ranking) {
    	this.nombre_ranking=nombre_ranking;
    }
    public Personajes[] getPersonajes() {
        return Personajes.values(); // Retorna todos los personajes
    }
    public Personajes obtenerPersonajeConMayorPuntuacion() {
        // Devuelve el personaje con la puntuación más alta
        Personajes personajeMaximo = null;
        for (Personajes personaje : Personajes.values()) {
            if (personajeMaximo == null || personaje.getPuntuacion() > personajeMaximo.getPuntuacion()) {
                personajeMaximo = personaje;
            }
        }
        return personajeMaximo;
    }
    
    public void reset() {
        this.nombre_jugador = null;
        this.sab_pod=false;
        this.nombre_ranking=null;
        for (Personajes p : Personajes.values()) {
            p.resetPuntuacion();
        }
    }
    
    public boolean isValidPersonaje(String nombre) {
        for (Personajes personaje : Personajes.values()) {
            if (personaje.name().equals(nombre)) {
                return true;
            }
        }
        return false;
    }

}
