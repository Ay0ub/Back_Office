package org.ensak.back_office.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.ensak.back_office.MainBackOffice;
import org.ensak.back_office.dao.DaoDivision;
import org.ensak.back_office.metier.beans.Division;
import org.ensak.back_office.metier.managerbeans.DivisionManager;
import org.ensak.back_office.metier.managerbeans.EmployeManager;
import org.ensak.back_office.modele.Employe;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AjouterDivsionController implements Initializable {
    @FXML
    public TextField nomDivision;
    @FXML
    public ComboBox<Employe> optionsChefDivision;
    @FXML
    public ComboBox<Employe> optionsEmployes;

    public TableView<Employe> employesAjoutes;
    public TableColumn<Employe, String> numeroEmp;
    public TableColumn<Employe, String> nomEmp;
    public TableColumn<Employe, String> prenomEmp;
    public TableColumn<Employe, Button> deleteEmp;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        load();
        onChangeChef();
    }

    private void load(){
        numeroEmp.setCellValueFactory(new PropertyValueFactory<>("numero"));
        nomEmp.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomEmp.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        deleteEmp.setCellValueFactory(new PropertyValueFactory<>("consulter"));

        ObservableList<Employe> dataEmployes = optionsEmployes.getItems();
        ObservableList<Employe> dataChef = optionsChefDivision.getItems();

        for(org.ensak.back_office.metier.beans.Employe employe:
                EmployeManager.getAllEmployes()) {

            Button enlever = new Button("Enlever");
            Employe emp = new Employe(
                    String.valueOf(employe.getNumero()),
                    employe.getNom(),
                    employe.getPrenom(),
                    enlever);

            dataEmployes.add(emp);
            dataChef.add(emp);

            enlever.setOnAction(event -> enleverEmploye(emp));
        }
    }

    /**
     * Méthode qui permet d'enlever un employé de la
     * liste des employés choisis
     * @param employe employé à enlever
     */
    private void enleverEmploye(Employe employe) {

        //On retire l'employé de la table
        employesAjoutes.getItems().remove(employe);

        //On l'ajoute dans la liste des choix
        optionsEmployes.getItems().add(employe);
    }

    public void ajouterEmploye(ActionEvent actionEvent) {
        Employe employeSelectionne = optionsEmployes.getValue();

        //Si aucun employé n'a été selectionné
        if (employeSelectionne == null){

            afficherErreur("Aucun employé selectionné",
                    "Veuillez selectionner un employé");
            return;
        }

        //On ajoute l'employé dans le tableau
        employesAjoutes.getItems().add(employeSelectionne);

        //On le retire du combobox
        optionsEmployes.getItems().remove(employeSelectionne);
    }

    /**
     * Fonction qui permet d'implémenter un listener
     * lors de la modification du choix de l'employé
     */
    public void onChangeChef() {

        optionsChefDivision.valueProperty().addListener((ov, ancienChef, nouveauChef) -> {
            //On retire le nouveau chef dans la liste des autres employés
            optionsEmployes.getItems().remove(nouveauChef);

            //On retire le nouveau chef de la liste des employés ajoutés
            employesAjoutes.getItems().remove(nouveauChef);

            //On ajoute l'ancien chef dans la liste des autres employés
            optionsEmployes.getItems().add(ancienChef);
        });
    }


    public void save(ActionEvent actionEvent) {
        String nomDivisionSaisi = nomDivision.getText();
        Employe chef = optionsChefDivision.getValue();
        ObservableList<Employe> employesDivision = employesAjoutes.getItems();
        if (nomDivisionSaisi.isEmpty() || (chef == null)){
            afficherErreur("Tous les les champs n'ont pas été remplis ",
                    "Veuillez remplir tous les champs");
            return;
        }

        if(employesDivision.size() < Division.NB_EMPLOYES_MIN){
            afficherErreur("Nombre d'employés insuffisant",
                    "Ajoutez encore des employés");
            return;
        }

        //Si toutes les informations sont correctes
        ArrayList<org.ensak.back_office.metier.beans.Employe> employes = new ArrayList<>();
        org.ensak.back_office.metier.beans.Employe chefDivision = new org.ensak.back_office.metier.beans.Employe();
        org.ensak.back_office.metier.beans.Employe employe;
        chefDivision.setNumero((Integer.valueOf(chef.getNumero())));
        for (Employe employe1 : employesDivision)
        {
            employe = new org.ensak.back_office.metier.beans.Employe();
            employe.setNumero(Integer.valueOf(employe1.getNumero()));
            employe.setNom(employe1.getNom());
            employe.setPrenom(employe1.getPrenom());
            employes.add(employe);
        }
        Division nouvelleDivision = new Division(nomDivisionSaisi,chefDivision,employes);
        DivisionManager.saveDivision(nouvelleDivision);
        JOptionPane.showMessageDialog(null, "La division"+nomDivisionSaisi+"a été enregistré");
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainBackOffice.class.getResource("/views/pageDivision.fxml"));
            MenuController.mainLayout.setCenter(loader.load());

        } catch (IOException e) {
            Logger.getLogger(ConsulterEmployeController.class.getName()).log(Level.SEVERE, null, e);
        }


    }

    public void back(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainBackOffice.class.getResource("/views/pageDivision.fxml"));
            MenuController.mainLayout.setCenter(loader.load());

        } catch (IOException e) {
            Logger.getLogger(AjouterEmployeController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void afficherErreur(String header, String content){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText(header);
        errorAlert.setContentText(content);
        errorAlert.showAndWait();
    }
}