package org.example.diariosecreto.Models;

public class DiarioMemento {
    private Diario diario;
    private String titulo;
    private String contenido;
    private Autor  autor;

    public DiarioMemento(Diario diario, String titulo, String contenido, Autor autor) {
        this.diario = diario;
        this.titulo = titulo;
        this.contenido = contenido;
        this.autor = autor;
    }

    public void restore() {
        this.diario.setTitulo(this.titulo);
        this.diario.setContenido(this.contenido);
        this.diario.setAutor(this.autor);
    }

    public String getContenido() {
        return contenido;
    }
}
