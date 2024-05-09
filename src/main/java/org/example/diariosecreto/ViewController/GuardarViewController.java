package org.example.diariosecreto.ViewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.stage.Stage;
import org.example.diariosecreto.Controller.AutorController;
import org.example.diariosecreto.Controller.DiarioController;
import org.example.diariosecreto.Models.Autor;
import org.example.diariosecreto.Models.Diario;


public class GuardarViewController {

        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private PasswordField txtContrasena;

        @FXML
        private TextField txtCorreo;

        @FXML
        void cancelarGuardado(ActionEvent event) {
                this.cerrarVentana();
        }

        @FXML
        void guardarDiario(ActionEvent event) {
                DiarioController diarioController = DiarioController.getInstance();
                AutorController autorController = AutorController.getInstance();

                Diario diario = diarioController.getDiarioTemporal();
                String correo = txtCorreo.getText();
                String contrasena = txtContrasena.getText();

                if (!txtCorreo.getText().isEmpty() && !txtContrasena.getText().isEmpty()) {
                        if (this.validarAutor(diarioController, diario, correo)) {
                                if(this.validarAutentificacion(autorController, diarioController, correo, contrasena, diario)){
                                        this.procesoGuardado(diarioController, diario);
                                        autorController.guardarDiario(correo, contrasena, diario);
                                }else {
                                        mostrarMensaje("Error", "Error", "El correo o la contraseña son incorrectos", Alert.AlertType.ERROR);
                                }
                        }else {
                                mostrarMensaje("Error", "Error", "El correo ingresado no pertenece al autor del diario", Alert.AlertType.ERROR);
                        }
                }else {
                        mostrarMensaje("Error", "Faltan datos", "Debe llenar todos los campos", Alert.AlertType.ERROR);
                }
                diario = null;
                correo = null;
                contrasena = null;
                this.cerrarVentana();
        }

        private void cerrarVentana(){
                Stage stage = (Stage) ((Node) txtCorreo).getScene().getWindow();
                stage.close();
        }

        private void procesoGuardado(DiarioController controller, Diario diario) {
                if (!controller.getListaDiarios().isEmpty()){
                        ArrayList<Diario> listaDiarios = controller.getListaDiarios();
                        boolean noExiste = true;
                        Diario diarioEliminable = null;
                        for (Diario d : listaDiarios) {
                                if (d.getTitulo().equals(diario.getTitulo())) {
                                        diarioEliminable = d;
                                        noExiste = false;
                                }
                        }
                        if (diarioEliminable != null) {
                                controller.removeDiario(diarioEliminable);
                                controller.addDiario(diario);
                        }
                        if (noExiste){
                                controller.addDiario(diario);
                        }
                } else {
                        controller.addDiario(diario);
                }
        }

        private boolean validarAutentificacion(AutorController controller, DiarioController diarioController, String correo, String contrasena, Diario diario) {
                        for (Autor autor : controller.getListaAutores()) {
                                if (autor.getCorreo().equals(correo) && autor.getContrasena().equals(contrasena)) {
                                        diario.setAutor(autor);
                                        return true;
                                }
                        }
                return false;
        }

        private boolean validarAutor(DiarioController controller, Diario diario, String correo) {
                ArrayList<Diario> listaDiarios = controller.getListaDiarios();
                Autor autorReal = null;
                for (Diario d : listaDiarios) {
                        if (d.getTitulo().equals(diario.getTitulo())) {
                                autorReal = d.getAutor();
                                break;
                        }
                }
                if (autorReal != null) {
                    return autorReal.getCorreo().equals(correo);
                }
                return true;

        }

        private void mostrarMensaje(String title, String header, String message, Alert.AlertType type){
                Alert alert = new Alert(type);
                alert.setTitle(title);
                alert.setHeaderText(header);
                alert.setContentText(message);
                alert.showAndWait();
        }

        @FXML
        void initialize() {
            assert txtContrasena != null : "fx:id=\"txtContrasena\" was not injected: check your FXML file 'guardar.fxml'.";
            assert txtCorreo != null : "fx:id=\"txtCorreo\" was not injected: check your FXML file 'guardar.fxml'.";

        }

}

