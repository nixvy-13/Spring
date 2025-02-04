package com.example.quiz;

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
    }

    public String nombre_jugador = null;

    // Inicialización de arrays dentro del constructor
    public String[] hobbys = {"ITACHI", "DEIDARA", "OROCHIMARU", "SASORI", "TOBI"};
    public String[] musica = {"OROCHIMARU", "TOBI", "ITACHI", "SASORI", "DEIDARA"};
    public String[] comida = {"DEIDARA", "ITACHI", "OROCHIMARU", "TOBI", "SASORI"};
    public String[] arte = {"SASORI", "TOBI", "ITACHI", "OROCHIMARU", "DEIDARA"};
    public String[] arma = {"DEIDARA", "ITACHI", "SASORI", "OROCHIMARU", "TOBI"};

    public boolean sab_pod = false;
    
    public String getNombreJugador() {
    	return nombre_jugador; 
    }
    public void setNombreJugador(String nombre_jugador) {
    	this.nombre_jugador=nombre_jugador;
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
    public boolean isValidPersonaje(String nombre) {
        for (Personajes personaje : Personajes.values()) {
            if (personaje.name().equals(nombre)) {
                return true;
            }
        }
        return false;
    }

}
