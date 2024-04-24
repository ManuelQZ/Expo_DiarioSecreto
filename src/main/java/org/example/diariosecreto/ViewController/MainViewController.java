package org.example.diariosecreto.ViewController;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.example.diariosecreto.App;
import org.example.diariosecreto.Controller.AutorController;
import org.example.diariosecreto.Controller.DiarioController;
import org.example.diariosecreto.Models.Autor;
import org.example.diariosecreto.Models.Diario;

public class MainViewController {

    Diario diarioProcesado;

    AutorController autorController = AutorController.getInstance();
    DiarioController diarioController = DiarioController.getInstance();


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnGuardat;

    @FXML
    private Button btnVolver;

    @FXML
    private TextField txtcorreo;

    @FXML
    private TableView<Diario> tableUsuario;

    @FXML
    private TextField txtcontrasena;

    @FXML
    private Button btnCrearUsuario;

    @FXML
    private TableColumn<Diario, String> columnAutor;

    @FXML
    private TableColumn<Diario, String> columnDiario;

    @FXML
    private TableColumn<Diario, String> columnCorreo;

    @FXML
    private TextArea txtContenido;

    @FXML
    private TextField txtTitulo;

    @FXML
    private TextField txtnombre;

    @FXML
    void crearAutor(ActionEvent event) {
        String nombre = txtnombre.getText();
        String correo = txtcorreo.getText();
        String contrasena = txtcontrasena.getText();

        if (nombre.isEmpty() || correo.isEmpty() || contrasena.isEmpty()){
            mostrarMensaje("Error", "Faltan datos", "Debe llenar todos los campos", Alert.AlertType.ERROR);
        } else {
            Autor autor = new Autor(nombre, correo, contrasena);
            autorController.agregarAutor(autor);
            limpiarCampos();
            initView();
        }

    }

    @FXML
    void guardarDiario(ActionEvent event) {

        String titulo = txtTitulo.getText();
        ArrayList<Diario> listaDiarios = diarioController.getListaDiarios();

        boolean noExiste = true;
        for (Diario diario : listaDiarios) {

            if (titulo.equals(diario.getTitulo())) {
                this.diarioController.setDiarioTemporal(diario);
                noExiste = false;
                this.ventanaEmergente();
            }
        }

        if(noExiste){
            if (!txtTitulo.getText().isEmpty()){
                Diario diarioNuevo = new Diario(titulo, txtContenido.getText());
                this.diarioController.setDiarioTemporal(diarioNuevo);
                this.ventanaEmergente();
            } else {
                this.mostrarMensaje("Error", "Diario NO Guardado", "Los campos estan vacios", Alert.AlertType.ERROR);
            }

        }
    }

    public void ventanaEmergente() {
        Scene scene = new Scene(new Pane());
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("guardar.fxml"));
            scene = new Scene(fxmlLoader.load());
        }catch (Exception e){
            System.out.println("Hubo un error" + e.getMessage());
        }

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Autor");
        stage.show();
    }

    @FXML
    void consultarHistorial(ActionEvent event) {

    }

    @FXML
    void initialize() {

        initView();
    }




    private void mostrarMensaje(String title, String header, String message, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void limpiarCampos(){
        txtnombre.setText("");
        txtcorreo.setText("");
        txtcontrasena.setText("");
    }

    private void initView(){
        initDataBinding();
        tableUsuario.getItems().clear();
        tableUsuario.setItems(diarioController.getListaDiariosObservable());
        listenerSelection();
    }

    private void listenerSelection() {
        tableUsuario.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            mostrarInformacionDiario(newSelection);
            diarioProcesado = newSelection;
        });
    }

    private void mostrarInformacionDiario(Diario seleccionado) {
        if(seleccionado != null){
            txtTitulo.setText(seleccionado.getTitulo());
            txtContenido.setText(seleccionado.getContenido());
        }
    }

    private void initDataBinding(){
        columnAutor.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAutor().getNombre()));
        columnCorreo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAutor().getCorreo()));
        columnDiario.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitulo()));
    }
}

