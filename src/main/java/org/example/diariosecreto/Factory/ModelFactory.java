package org.example.diariosecreto.Factory;

import org.example.diariosecreto.Models.Autor;
import org.example.diariosecreto.Models.AutorProxy;
import org.example.diariosecreto.Models.GestorDiario;

public class ModelFactory {

    private static ModelFactory modelFactory;
    private GestorDiario gestorDiario;


    private ModelFactory () {

        this.gestorDiario = new GestorDiario();
        this.inicilizarDatos();
    }

    public static ModelFactory getInstance() {
        if (modelFactory == null) {
            modelFactory = new ModelFactory();
        }
        return modelFactory;
    }

    public GestorDiario getGestorDiario() {
        return gestorDiario;
    }

    public void inicilizarDatos(){
        Autor autor1 = new Autor("Alan Turing", "alan@example.com", "contraseña1");
        Autor autor2 = new Autor("Grace Hopper", "grace@example.com", "contraseña2");
        Autor autor3 = new Autor("Linus Torvalds", "linus@example.com", "contraseña3");
        Autor autor4 = new Autor("Ada Lovelace", "ada@example.com", "contraseña4");
        Autor autor5 = new Autor("Margaret Hamilton", "margaret@example.com", "contraseña5");

        this.gestorDiario.addAutor(autor1);
        this.gestorDiario.addAutor(autor2);
        this.gestorDiario.addAutor(autor3);
        this.gestorDiario.addAutor(autor4);
        this.gestorDiario.addAutor(autor5);
    }


}
