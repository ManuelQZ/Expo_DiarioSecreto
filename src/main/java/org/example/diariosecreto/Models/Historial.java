package org.example.diariosecreto.Models;

public class Historial {
    private DiarioMemento historial;

    private Diario diario;

    public Historial() {

    }
    public void generarHistorial(Diario diario) {
        historial = diario.crearMemento();
    }

    public void volver() {
        if (historial != null) {
            historial.restore();
        }
    }

    public Diario getDiario() {
        return diario;
    }

    public DiarioMemento getHistorial() {
        return historial;
    }
}
