package org.example.diariosecreto.Models;

public class Historial {
    private static DiarioMemento historial;

    public static void generarHistorial(Diario diario) {
        historial = diario.crearMemento();
    }

    public static void volver() {
        if (historial != null) {
            historial.restore();
        }
    }
}
