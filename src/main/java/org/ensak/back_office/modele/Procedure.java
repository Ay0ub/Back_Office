package org.example.modele;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

public class Procedure {
    private final SimpleStringProperty numero = new SimpleStringProperty("");
    private final SimpleStringProperty nom = new SimpleStringProperty("");
    private final SimpleStringProperty division = new SimpleStringProperty("");
    private final Button consulter = new Button("Consulter");

    public Procedure() {
    }

    public String getNumero() {
        return numero.get();
    }

    public SimpleStringProperty numeroProperty() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero.set(numero);
    }

    public String getNom() {
        return nom.get();
    }

    public SimpleStringProperty nomProperty() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public String getDivision() {
        return division.get();
    }

    public SimpleStringProperty divisionProperty() {
        return division;
    }

    public void setDivision(String division) {
        this.division.set(division);
    }

    public Button getConsulter() {
        return consulter;
    }
}
