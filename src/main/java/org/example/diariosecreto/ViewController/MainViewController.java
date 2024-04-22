package org.example.diariosecreto.ViewController;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.diariosecreto.Controller.DiarioController;
import org.example.diariosecreto.Models.Diario;

public class MainViewController {

    Diario diarioProcesado;

    DiarioController diarioController = new DiarioController();
    

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
    void crearUsuario(ActionEvent event) {

    }

    @FXML
    void guardarDiario(ActionEvent event) {

    }

    @FXML
    void consultarHistorial(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }

    private void inicializardatos() {



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
        obtenerUsuarios();
        tableUsuario.getItems().clear();
        tableUsuario.setItems(listaDiarios);
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

    private void obtenerDiarios () {
        return diarioController.getListaDiariosObservable();
    }

}

