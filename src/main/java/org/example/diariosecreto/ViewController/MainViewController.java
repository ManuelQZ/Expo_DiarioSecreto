package org.example.diariosecreto.ViewController;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.diariosecreto.Controller.AutorController;
import org.example.diariosecreto.Controller.DiarioController;
import org.example.diariosecreto.Models.Autor;
import org.example.diariosecreto.Models.Diario;

public class MainViewController {

    Diario diarioProcesado;

    AutorController autorController = new AutorController();

    DiarioController diarioController = new DiarioController();

    ObservableList<Diario> listaDiarios = diarioController.getListaDiariosObservable();
    

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
        String nombre = txtnombre.getText();
        String correo = txtcorreo.getText();
        String contrasena = txtcontrasena.getText();

        if(nombre.isEmpty() || correo.isEmpty() || contrasena.isEmpty()){
            mostrarMensaje("Error", "Faltan datos", "Debe llenar todos los campos", Alert.AlertType.ERROR);
        }else{
            Autor autor = new Autor(nombre, correo, contrasena);
            autorController.agregarAutor(autor);
            limpiarCampos();
            initView();
        }

    }

    @FXML
    void guardarDiario(ActionEvent event) {

//        String titulo = txtTitulo.getText();
//        ArrayList<Diario> listaDiarios = diarioController.getListaDiarios();
//
//        for (Diario diario : listaDiarios) {
//            if (titulo.equals(diario.getTitulo())) {
//
//                autorController.guardarDiario(txtcorreo.getText(), txtcontrasena.getText(), diario);
//            }
//        }
        this.ventanaEmergente();

    }

    public void ventanaEmergente() {
        // Crear etiqueta para la segunda ventana
        Label label = new Label("¡Segunda Ventana!");

        // Crear diseño de la segunda ventana
        VBox root = new VBox();
        root.getChildren().add(label);
        Label labelNombre = new Label("Nombre:");
        TextField textFieldNombre = new TextField();
        Label labelEmail = new Label("Email:");
        TextField textFieldEmail = new TextField();

        // Crear botones
        Button botonGuardar = new Button("Guardar");
        Button botonCancelar = new Button("Cancelar");

        // Configurar escena y mostrar la segunda ventana
        Stage stage = new Stage();
        Scene scene = new Scene(root, 200, 100);
        stage.setScene(scene);
        stage.setTitle("Segunda Ventana");
        stage.show();
    }

    @FXML
    void consultarHistorial(ActionEvent event) {

    }

    @FXML
    void initialize() {

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

    private ObservableList<Diario> obtenerDiarios () {
        return diarioController.getListaDiariosObservable();
    }

}

