package org.ensak.back_office.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.ensak.back_office.MainBackOffice;
import org.ensak.back_office.metier.beans.Division;
import org.ensak.back_office.metier.beans.Employe;
import org.ensak.back_office.metier.managerbeans.DivisionManager;
import org.ensak.back_office.metier.managerbeans.EmployeManager;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsulterEmployeController implements Initializable {
    @FXML
    public Label numero;
    public Label nom;
    public Label prenom;
    public Label email;
    public Label telephone;
    public Label division;
    public static int id;

    /**
     * c'est la methode sui s'executera en entrée lorsque la vue consulterEmployé sera sollicitée
     * elle permettra d'initialiser les ressources de la vue
     * @param location
     * @param resources
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Employe employe =new Employe();
        Division divisionemp = new Division();
        try {
            employe = EmployeManager.getEmployeById(id);
            divisionemp =DivisionManager.getDivisionById(employe.getIdDivision());
            numero.setText(String.valueOf(employe.getNumero()));
            nom.setText(employe.getNom());
            prenom.setText(employe.getPrenom());
            email.setText(employe.getEmail());
            telephone.setText(employe.getTel());
            division.setText(divisionemp.getNomDivision());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    /**
     * il s'agit d'une methode static qu'un autre controller pourra utiliser pour
     * afficher la vue consulterEmploye
     */

    public static void consulterEmploye() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainBackOffice.class.getResource("/views/consulterEmploye.fxml"));
            MenuController.mainLayout.setCenter(loader.load());

        } catch (IOException e) {
            Logger.getLogger(ConsulterEmployeController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * il s'agit d'une action suit un evenement qui permetra l'utilisateur de retourner
     * a la page précédente
     * @param actionEvent
     */

    public void back(ActionEvent actionEvent) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainBackOffice.class.getResource("/views/pageEmploye.fxml"));
            MenuController.mainLayout.setCenter(loader.load());

        } catch (IOException e) {
            Logger.getLogger(ConsulterDivisionController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * cette methode permettra de supprimer un employé
     * @param actionEvent
     * @throws SQLException
     */

    public void supprimer(ActionEvent actionEvent) throws SQLException {

        int alert = JOptionPane.showConfirmDialog(null,"Voulez vous vraiment supprimmer lemployé ?? :"+EmployeManager.getEmployeById(id).getPrenom(),"Delete",JOptionPane.YES_NO_OPTION);
        if (alert == 0) {
            if (EmployeManager.deleteEmploye(id)) {
                JOptionPane.showMessageDialog(null, "Employé Supprimée");
                try {

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(MainBackOffice.class.getResource("/views/pageEmploye.fxml"));
                    MenuController.mainLayout.setCenter(loader.load());

                } catch (IOException e) {
                    Logger.getLogger(ConsulterEmployeController.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }

        }

    /**
     * cette methode permettra de modifier un employé
     * @param actionEvent
     * @throws SQLException
     */

    public void update(ActionEvent actionEvent) throws SQLException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainBackOffice.class.getResource("/views/ajouterEmploye.fxml"));
            MenuController.mainLayout.setCenter(loader.load());

        } catch (IOException e) {
            Logger.getLogger(ConsulterEmployeController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
