package org.ensak.back_office.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.ensak.back_office.MainBackOffice;
import org.ensak.back_office.metier.beans.Employe;
import org.ensak.back_office.metier.managerbeans.EmployeManager;
import org.ensak.back_office.utils.SecurityData;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class AjouterEmployeController implements Initializable {
    @FXML
    public TextField nonEmploye;
    @FXML
    public TextField prenom;
    @FXML
    public TextField email;
    @FXML
    public TextField telephone;
    @FXML
    public TextField passe;

    /**
     * il s'agit d'une action qui sera executé lorsque l'administrateur voudra enregistrer un employé
     * dans la base de données il suffira pour lui de cliquer su enregistrer
     * @param actionEvent
     */

    public void save(ActionEvent actionEvent){
        ConsulterEmployeController.id =0;
        String nomEmploye =nonEmploye.getText();
        String prenomEmploye = prenom.getText();
        String emailEmploye = email.getText();
        String telephoneEmploye = telephone.getText();
        String passwordEmploye = passe.getText();
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        /**
         * on teste si l'administrateur rentré tous les champs sinon un le lui signal au
         * travers d'une alerte
         */
        if(nomEmploye.isEmpty() || prenomEmploye.isEmpty() || emailEmploye.isEmpty() || telephoneEmploye.isEmpty()
                || passwordEmploye.isEmpty())
        {
            errorAlert.setHeaderText("Tous les les champs n'ont pas été remplis ");
            errorAlert.setContentText("Veillez remplir tous les champs");
            errorAlert.showAndWait();
        }
        else if (!SecurityData.isValidEmail(emailEmploye))
        {
            errorAlert.setHeaderText("Mauvaise addresse email ");
            errorAlert.setContentText("Veillez entrer une addresse a la forme ZZZZZ.@ZZZ.ZZZ");
            errorAlert.showAndWait();
        }
        else if(SecurityData.isValidPhoneNumber(telephoneEmploye))
        {

            errorAlert.setHeaderText("Mauvais numéro de telephone");
            errorAlert.setContentText("Veillez entrer un numéro de la forme +212 .........");
            errorAlert.showAndWait();
        }
        else {

            Employe employe = new Employe();
            employe.setNom(nomEmploye);
            employe.setPrenom(prenomEmploye);
            employe.setEmail(emailEmploye);
            employe.setTel(telephoneEmploye);
            employe.setPassword(passwordEmploye);

            int alert = JOptionPane.showConfirmDialog(null,"Voulez vous vraiment emregistrer lemployé ?? :"+nomEmploye,"Enregistrer",JOptionPane.YES_NO_OPTION);
            if (alert == 0) {

                if (EmployeManager.saveEmploye(employe)) {
                    JOptionPane.showMessageDialog(null, "L'employé"+nomEmploye+"a été enregistré");
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

    }


    /**
     * back permet simplement de retourner a la page precédenete lorsque l'on
     * clique sur le button retour
     * @param actionEvent
     */

    public void back(ActionEvent actionEvent) {

        ConsulterEmployeController.id =0;

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainBackOffice.class.getResource("/views/pageEmploye.fxml"));
            MenuController.mainLayout.setCenter(loader.load());

        } catch (IOException e) {
            Logger.getLogger(AjouterEmployeController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * methode initialize qui est la methode d'entrée relative au fichier fxml representant
     * la vue ajouterEmploye elle nous servira au moment de la modification d'un employé
     * on prendra l'id de l'employé et on insere les informattions de l'employé dans les Textfields
     * @param location
     * @param resources
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int id = ConsulterEmployeController.id;
        Employe employe = new Employe();
        try {
            employe = EmployeManager.getEmployeById(id);
            if ( id > 0) {
                nonEmploye.setText(employe.getNom());
                passe.setText(employe.getPassword());
                prenom.setText(employe.getPrenom());
                telephone.setText(employe.getTel());
                email.setText(employe.getEmail());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
