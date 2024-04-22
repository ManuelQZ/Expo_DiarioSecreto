module org.example.diariosecreto {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.diariosecreto.ViewController to javafx.fxml;
    exports org.example.diariosecreto;

}