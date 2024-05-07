package org.example.diariosecreto.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.diariosecreto.Factory.ModelFactory;
import org.example.diariosecreto.Models.Autor;
import org.example.diariosecreto.Models.AutorProxy;
import org.example.diariosecreto.Models.Diario;


import java.util.ArrayList;
import java.util.List;

public class AutorController {

    private ObservableList<Autor> listaAutoresObservable;

    private ModelFactory factory;
    private String log;

    private static AutorController instance;


    public AutorController() {
        this.listaAutoresObservable = FXCollections.observableArrayList();
        this.factory = ModelFactory.getInstance();
        this.sincronizarData();
    }

    public void guardarDiario(String correo, String  contrasena, Diario diario) {

        ArrayList<Autor> listaAutores = factory.getGestorDiario().getAutores();
        AutorProxy autorTemporal = factory.getGestorDiario().getAutorTemporal();

        for (Autor autor : listaAutores) {

            if(autor.getCorreo().equals(correo)) {

                autorTemporal.setCorreo(correo);
                autorTemporal.setContrasena(contrasena);
                autorTemporal.setAutor(autor);
            }
        }
        log = autorTemporal.guardarDiario(diario);
    }

    public static AutorController getInstance() {
        if (instance == null) {
            instance = new AutorController();
        }
        return instance;
    }

    public void agregarAutor(Autor autor) {
        this.factory.getGestorDiario().addAutor(autor);
        this.listaAutoresObservable.add(autor);
    }

    public String getLog() {
        return log;
    }

    public ObservableList<Autor> getListaAutoresObservable() {
        return listaAutoresObservable;
    }

    public List<Autor> getListaAutores() {
        return factory.getGestorDiario().getAutores();
    }

    public void sincronizarData() {
        this.listaAutoresObservable.addAll(this.factory.getGestorDiario().getAutores());
    }

}
