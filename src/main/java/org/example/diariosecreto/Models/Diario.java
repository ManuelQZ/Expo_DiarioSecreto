package org.example.diariosecreto.Models;

public class Diario {

    private String titulo;
    private String contenido;
    private Autor  autor;

    public Diario(String titulo, String contenido) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.autor = new Autor();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    public Autor getAutor() {
        return autor;
    }
    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public DiarioMemento crearMemento() {
        return new DiarioMemento(this, titulo, contenido, autor);
    }

}
