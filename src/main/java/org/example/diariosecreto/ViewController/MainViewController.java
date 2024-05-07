package org.example.diariosecreto.ViewController;

import java.net.URL;
import java.util.ArrayList;
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

    Diario historialDiario = new Diario("","");
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

        historial.volver();
        txtContenido.setText(historial.getHistorial().getContenido());
        System.out.println("soy yo bebe");
    }

    @FXML
    void initialize() {
        initView();


        txtContenido.textProperty().addListener((observable, oldValue, newValue) -> {

            historialDiario.setContenido(txtContenido.getText());
            System.out.println(txtContenido.getText());
            historial.generarHistorial(historialDiario);
            System.out.println("hila veve");
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
            txtContenido.setText(seleccionado.getContenido());
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