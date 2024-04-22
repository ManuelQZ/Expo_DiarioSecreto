package org.example.diariosecreto.Models;

import java.util.ArrayList;

public class GestorDiario {

    private ArrayList<Diario> diarios;
    private ArrayList<Autor> autores;

    public GestorDiario(){
        this.diarios = new ArrayList<Diario>();
        this.autores = new ArrayList<Autor>();
    }

    public ArrayList<Diario> getDiarios(){
        return this.diarios;
    }

    public ArrayList<Autor> getAutores(){
        return this.autores;
    }

    public void addDiario(Diario diario){
        this.diarios.add(diario);
    }

    public void addAutor(Autor autor){
        this.autores.add(autor);
    }

    public void removeDiario(Diario diario){
        this.diarios.remove(diario);
    }

    public void removeAutor(Autor autor){
        this.autores.remove(autor);
    }

}
