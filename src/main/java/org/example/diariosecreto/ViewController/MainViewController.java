package org.example.diariosecreto.ViewController;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
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
import org.example.diariosecreto.Models.Historial;

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
    private Button btnCrearUsuario;

    @FXML
    private TableColumn<Diario, String> columnAutor;

    @FXML
    private TableColumn<Diario, String> columnDiario;

    @FXML
    private TableColumn<Diario, String> columnCorreo;

    @FXML
    private TableColumn<Autor, String> tableCorreoUsuario;

    @FXML
    private TableColumn<Autor, String> tableNombreUsuario;

    @FXML
    private TableView<Autor> tableUsuario;

    @FXML
    private TableView<Diario> tableDiario;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtClave;

    @FXML
    private TextArea txtContenido;

    @FXML
    private TextField txtTitulo;

    Diario historialDiario = new Diario("", new String[]{""});
    Historial historial = new Historial();

    @FXML
    void addUsuario(ActionEvent event) {
        String nombre = txtNombre.getText();
        String correo = txtCorreo.getText();
        String contrasena = txtClave.getText();

        if (nombre.isEmpty() || correo.isEmpty() || contrasena.isEmpty()){
            mostrarMensaje("Error", "Faltan datos", "Debe llenar todos los campos", Alert.AlertType.ERROR);
        } else {
            Autor autor = new Autor(nombre, correo, contrasena);
            autorController.agregarAutor(autor);
            limpiarCampos();
        }

    }

    @FXML
    void guardarDiario(ActionEvent event) {
        if(!txtTitulo.getText().isEmpty() ){
            Diario diario = new Diario(txtTitulo.getText(), txtContenido.getText().split(" "));
            diarioController.setDiarioTemporal(diario);
            ventanaEmergente();
        }else{
            mostrarMensaje("Error", "Faltan datos", "Debe llenar todos los campos", Alert.AlertType.ERROR);
        }

    }

    public void ventanaEmergente() {
        Scene scene = new Scene(new Pane());
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("guardar.fxml"));
            scene = new Scene(fxmlLoader.load());
        }catch (Exception e){
            mostrarMensaje("Error", "Error al cargar la ventana de guardado", e.getMessage(), Alert.AlertType.ERROR);
        }

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Autor");
        stage.show();
    }

    @FXML
    void consultarHistorial(ActionEvent event) {

        historial.volver();
        String contenido = String.join(" ", historial.getHistorial().getContenido());
        txtContenido.setText(contenido);
    }

    @FXML
    void initialize() {
        initView();


        txtContenido.textProperty().addListener((observable, oldValue, newValue) -> {
            String contenido = txtContenido.getText();
            if (contenido != null){
                if (contenido.charAt(contenido.length() - 1) == ' ') {
                    historial.generarHistorial(historialDiario);
                    historialDiario.setContenido(contenido.split(" "));
                }
            }
        });
    }


    private void mostrarMensaje(String title, String header, String message, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void limpiarCampos(){
        txtNombre.setText("");
        txtCorreo.setText("");
        txtClave.setText("");
    }

    private void initView(){
        initDataBinding();
        tableDiario.getItems().clear();
        tableDiario.setItems(diarioController.getListaDiariosObservable());
        tableUsuario.getItems().clear();
        tableUsuario.setItems(autorController.getListaAutoresObservable());
        listenerSelection();
    }

    private void listenerSelection() {
        tableDiario.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            mostrarInformacionDiario(newSelection);
            diarioProcesado = newSelection;
        });
    }

    private void mostrarInformacionDiario(Diario seleccionado) {
        if(seleccionado != null){
            txtTitulo.setText(seleccionado.getTitulo());
            String contenido = String.join(" ", seleccionado.getContenido());

            txtContenido.setText(contenido);
        }
    }

    private void initDataBinding(){
        //Esto para la tabla de Diario
        columnAutor.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAutor().getNombre()));
        columnCorreo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAutor().getCorreo()));
        columnDiario.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitulo()));

        //Esto es para la tabla de Usuario.
        tableNombreUsuario.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tableCorreoUsuario.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCorreo()));
    }
}