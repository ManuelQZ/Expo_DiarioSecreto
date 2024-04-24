package org.example.diariosecreto.Controller;

import org.example.diariosecreto.Factory.ModelFactory;
import org.example.diariosecreto.Models.Autor;
import org.example.diariosecreto.Models.AutorProxy;
import org.example.diariosecreto.Models.Diario;
import org.example.diariosecreto.Models.GestorDiario;

import java.util.ArrayList;

public class AutorController {

    private ModelFactory factory;
    private String log;

    private static AutorController instance;


    public AutorController() {
        this.factory = ModelFactory.getInstance();
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
    }

    public String getLog() {
        return log;
    }
}
