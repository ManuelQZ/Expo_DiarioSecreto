package org.example.diariosecreto.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.diariosecreto.Factory.ModelFactory;
import org.example.diariosecreto.Models.Diario;

import java.util.ArrayList;

public class DiarioController {

    private ObservableList<Diario> listaDiariosObservable;

    private ModelFactory factory;

    public DiarioController() {
        this.listaDiariosObservable = FXCollections.observableArrayList();
        this.factory = ModelFactory.getInstance();
        this.sincronizarData();
    }

    public ObservableList<Diario> getListaDiariosObservable() {
        return listaDiariosObservable;
    }

    public void addDiario(Diario diario) {
        this.listaDiariosObservable.add(diario);
        this.factory.getGestorDiario().addDiario(diario);
    }

    public void removeDiario(Diario diario) {
        this.listaDiariosObservable.remove(diario);
        this.factory.getGestorDiario().removeDiario(diario);
    }

    public void sincronizarData() {
        this.listaDiariosObservable.addAll(this.factory.getGestorDiario().getDiarios());
    }

    public ArrayList<Diario> getListaDiarios() {

        return this.factory.getGestorDiario().getDiarios();
    }
}
