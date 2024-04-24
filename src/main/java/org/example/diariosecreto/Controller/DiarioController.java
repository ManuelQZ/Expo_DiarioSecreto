package org.example.diariosecreto.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.diariosecreto.Factory.ModelFactory;
import org.example.diariosecreto.Models.Diario;

import java.util.ArrayList;

public class DiarioController {

    private ObservableList<Diario> listaDiariosObservable;
    private Diario diarioTemporal;
    private ModelFactory factory;
    private static DiarioController instance;

    private DiarioController() {
        this.listaDiariosObservable = FXCollections.observableArrayList();
        this.diarioTemporal = null;
        this.factory = ModelFactory.getInstance();
        this.sincronizarData();
    }

    public static DiarioController getInstance() {
        if (instance == null) {
            instance = new DiarioController();
        }
        return instance;
    }

    public Diario getDiarioTemporal() {
        return diarioTemporal;
    }

    public void setDiarioTemporal(Diario diarioTemporal) {
        this.diarioTemporal = diarioTemporal;
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
        sincronizarData();
        return this.factory.getGestorDiario().getDiarios();
    }
}
