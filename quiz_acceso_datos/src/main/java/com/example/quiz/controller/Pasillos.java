package com.example.quiz.controller;

public enum Pasillos {
    PASILLO_CONGELADOS(0),
    PASILLO_BEBIDAS_ALCOHOLICAS(0),
    PASILLO_DULCES(0),
    PASILLO_FRUTA_VERDURAS(0);

    private int puntuacion; // Puntuación inicializada a 0

    //Constructor para inicializar la puntuación
    Pasillos(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    //Método para incrementar la puntuación del pasillo
    public void incrementarPuntuacion() {
        this.puntuacion++;
    }

    //Método para incrementar la puntuación de un pasillo por su nombre
    public static void incrementarPuntuacionPorNombre(String nombre) {
        for (Pasillos pasillo : Pasillos.values()) {
            if (pasillo.name().equalsIgnoreCase(nombre)) {
                pasillo.incrementarPuntuacion(); // Llamamos al método que incrementa la puntuación
                //System.out.println(pasillo)
                return; // Termina después de encontrar el pasillo
            }
        }
    }

    //Método para obtener el pasillo con la mayor puntuación
    public static Pasillos obtenerPasilloConMayorPuntuacion() {
        Pasillos pasilloMaximo = null;
        for (Pasillos pasillo : Pasillos.values()) {
            if (pasilloMaximo == null || pasillo.puntuacion > pasilloMaximo.puntuacion) {
                pasilloMaximo = pasillo;
            }
        }
        return pasilloMaximo;
    }

    public String getNombrePasillo() {
        switch (this) {
            case PASILLO_CONGELADOS:
                return "Pasillo de Congelados";
            case PASILLO_BEBIDAS_ALCOHOLICAS:
                return "Pasillo de Bebidas Alcohólicas";
            case PASILLO_DULCES:
                return "Pasillo de Dulces";
            case PASILLO_FRUTA_VERDURAS:
                return "Pasillo de Frutas y Verduras";
            default:
                return this.name(); // Devuelve el nombre original en caso de que no se haya definido
        }
    }

    //Método para reiniciar todas las puntuaciones a 0
    public static void reiniciarPuntuaciones() {
        for (Pasillos pasillo : Pasillos.values()) {
            pasillo.puntuacion = 0; //Reiniciar la puntuación de cada pasillo
        }
    }
    public int getPuntuacion() {
        return this.puntuacion;
    }
}
