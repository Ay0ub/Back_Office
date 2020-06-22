package org.ensak.back_office.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.ensak.back_office.dao.*;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController {

    @FXML
    private TextField login;
    @FXML
    private TextField pass;


    /**
     * methode permettant d'analiser le login et le mot de passe
     * entré par l'administrateur et le rediriger vres la page d'accueil.
     */

    @FXML
    public void login(javafx.event.ActionEvent actionEvent) throws IOException, SQLException {
        String login = this.login.getText();
        String pass = this.pass.getText();

        AdminConnexion adminConnexion = new AdminConnexion();
        boolean validationConnexion;
        validationConnexion = adminConnexion.validation(login, pass);
        if (validationConnexion)
        {
            MenuController.Menu();
        }
        else {

            /**
             * afficahe d'une alerte en cas d'erreur ou d'absence
             * du mot de passe ou du login
             */
            Alert  errorAlert = new Alert(Alert.AlertType.ERROR);
            if (login.isEmpty())
            {

                errorAlert.setHeaderText("Aucun login");
                errorAlert.setContentText("Veillez entrer un login");
                errorAlert.showAndWait();
            }else if (pass.isEmpty())
                {
                    errorAlert.setHeaderText("Aucun mot de passe");
                    errorAlert.setContentText("Veillez entrer votre mot de passe");
                    errorAlert.showAndWait();
                }
            else {
                errorAlert.setHeaderText("Erreur");
                errorAlert.setContentText("Veillez entrer le login et mot de passe correct");
                errorAlert.showAndWait();

            }

        }

    }

    @FXML
    public void exit(javafx.event.ActionEvent actionEvent) {
        System.exit(0);
    }
}
