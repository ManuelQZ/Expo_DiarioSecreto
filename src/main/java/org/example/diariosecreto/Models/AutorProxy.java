package org.example.diariosecreto.Models;


import org.example.diariosecreto.Services.ManejoDiario;

public class AutorProxy implements ManejoDiario {


    private String correo;
    private String contrasena;
    private Autor autor;

    public AutorProxy(String correo, String contrasena, Autor autor) {
        this.correo = correo;
        this.contrasena = contrasena;
        this.autor = autor;

    }

    public boolean autenticar(){

        String correoAutor = autor.getCorreo();
        String contrasenaAutor = autor.getContrasena();
        return (this.correo.equals(correoAutor) && this.contrasena.equals(contrasenaAutor));
    }


    @Override
    public String guardarDiario() {
        if (this.autenticar()){
            autor.guardarDiario();
            return "Diario guardado con éxito";
        } else {
            return "Error de autenticación";
        }
    }
}
