package org.example.diariosecreto.Models;

import java.util.ArrayList;

public class Historial {
    private ArrayList<DiarioMemento> historial;


    public Historial() {
        this.historial = new ArrayList<>();

    }
    public void generarHistorial(Diario diario) {
        historial.add(diario.crearMemento());

    }


    public void volver() {
        if (historial != null) {
            historial.removeLast();
            historial.getLast().restore();
        }
    }

    public DiarioMemento getHistorial() {
        return historial.getLast();
    }
}
