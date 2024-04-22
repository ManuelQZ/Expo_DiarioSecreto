package org.example.diariosecreto.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.diariosecreto.Models.Diario;

public class DiarioController {

    ObservableList<Diario> listaDiariosObservable;

    public DiarioController() {
        this.listaDiariosObservable = FXCollections.observableArrayList();
    }

    public ObservableList<Diario> getListaDiariosObservable() {
        return listaDiariosObservable;
    }
}
