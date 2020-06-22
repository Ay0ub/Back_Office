package org.ensak.back_office.modele;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

public class Division {
    private  String numero;
    private  String nom;
    private  String chefDivision;
    private  Button consulter ;

    public Division(String numero, String nom, String chefDivision,Button button) {
        this.numero = numero;
        this.nom = nom;
        this.chefDivision = chefDivision;
        this.consulter = button;
        consulter.setStyle("-fx-background-color:#FF9900");

    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getChefDivision() {
        return chefDivision;
    }

    public void setChefDivision(String chefDivision) {
        this.chefDivision = chefDivision;
    }

    public Button getConsulter() {
        return consulter;
    }
}
