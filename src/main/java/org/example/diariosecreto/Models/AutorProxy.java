package org.example.diariosecreto.Models;


import org.example.diariosecreto.Services.ManejoDiario;

public class AutorProxy implements ManejoDiario {


    private String correo;
    private String contrasena;
    private Autor autor;

    public AutorProxy() {
    }

    public boolean autenticar(){

        String correoAutor = autor.getCorreo();
        String contrasenaAutor = autor.getContrasena();
        return (this.correo.equals(correoAutor) && this.contrasena.equals(contrasenaAutor));
    }


    @Override
    public String guardarDiario(Diario diario) {
        if (this.autenticar()){
            autor.guardarDiario(diario);
            return "Diario guardado con éxito";
        } else {
            return "Error de autenticación";
        }
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

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
