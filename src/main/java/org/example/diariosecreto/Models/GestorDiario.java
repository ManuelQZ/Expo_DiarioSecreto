package org.example.diariosecreto.Models;

import java.util.ArrayList;

public class GestorDiario {

    private ArrayList<Diario> diarios;
    private ArrayList<Autor> autores;
    private AutorProxy autorTemporal;

    public GestorDiario(){
        this.diarios = new ArrayList<Diario>();
        this.autores = new ArrayList<Autor>();
        this.autorTemporal = new AutorProxy();
    }

    public ArrayList<Diario> getDiarios(){
        return this.diarios;
    }

    public ArrayList<Autor> getAutores(){
        return this.autores;
    }

    public void addDiario(Diario diario){
        this.diarios.add(diario);
        System.out.println("Added " + diario.getTitulo());
    }

    public void addAutor(Autor autor){
        this.autores.add(autor);
    }

    public void removeDiario(int index){
        this.diarios.remove(index);
    }

    public void removeAutor(Autor autor){
        this.autores.remove(autor);
    }

    public AutorProxy getAutorTemporal(){
        return this.autorTemporal;
    }

}
