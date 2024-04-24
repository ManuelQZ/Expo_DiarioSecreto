package org.example.diariosecreto.ViewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.stage.Stage;
import org.example.diariosecreto.Controller.AutorController;
import org.example.diariosecreto.Controller.DiarioController;
import org.example.diariosecreto.Models.Diario;


public class GuardarViewController {

        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private TextField txtContrasena;

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
                System.out.println(diario.getTitulo());
                String correo = txtCorreo.getText();
                String contrasena = txtContrasena.getText();

                autorController.guardarDiario(correo, contrasena, diario);
                if (!diarioController.getListaDiariosObservable().contains(diario)){
                        diarioController.addDiario(diario);
                        }
                this.mostrarMensaje("Información", "La acción a devuelto el siguiente mensaje: ", autorController.getLog(), Alert.AlertType.INFORMATION);

                this.cerrarVentana();
        }

        private void cerrarVentana(){
                Stage stage = (Stage) ((Node) txtCorreo).getScene().getWindow();
                stage.close();
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

