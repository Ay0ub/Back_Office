package org.ensak.back_office.modele;
import javafx.scene.control.Button;

public class Employe {
    private String numero;
    private String nom;
    private String prenom;
    private String email;
    private Button consulter;

    public Employe(String numero, String nom, String prenom) {
        this.numero = numero;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Employe(String numero, String nom, String prenom, Button consulter) {
        this.numero = numero;
        this.nom = nom;
        this.prenom = prenom;
        this.consulter = consulter;
        this.consulter.setStyle("-fx-background-color:#FF9900");
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Button getConsulter() {
        return consulter;
    }

    public void setConsulter(Button consulter) {
        this.consulter = consulter;
    }
    /**
     * Méthode pour afficher le nom complet de l'employé
     * @return Prénom + Nom de l'employé
     */
    @Override
    public String toString() {

        String nomComplet = prenom.toString() + " " + nom.toString().toUpperCase();

        return nomComplet;
    }
}
