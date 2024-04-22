package org.example.diariosecreto.Models;

import org.example.diariosecreto.Services.ManejoDiario;

import java.util.ArrayList;

public class Autor implements ManejoDiario {

    private String nombre;
    private String correo;
    private String contrasena;
    private ArrayList<Diario> diariosCreados;

    public Autor() {
    }

    public Autor(String nombre, String correo, String contrasena) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.diariosCreados = new ArrayList<>();
    }

    public ArrayList<Diario> getDiariosCreados() {
        return diariosCreados;
    }

    public void addDiario(Diario diario) {
        this.diariosCreados.add(diario);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public String guardarDiario() {
        return null;
    }
}
