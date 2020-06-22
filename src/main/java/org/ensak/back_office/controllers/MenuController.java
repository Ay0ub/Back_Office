package org.ensak.back_office.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.ensak.back_office.MainBackOffice;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuController  {

    @FXML
    private Button accueil;
    @FXML
    private Button division;
    @FXML
    private Button employe;
    @FXML
    private Button procedure;
    @FXML
    private ImageView Laccueil;
    @FXML
    private ImageView Ldivision;
    @FXML
    private ImageView Lemploye;
    @FXML private ImageView Lprocedure;
    public static Stage stage;
    public static BorderPane mainLayout;


    /**
     *
     * methode qui permet d'afficher le menu de gestion de
     * l'adminbistrateur lorsque celui-ci se connecte
     *
     */
    public static void Menu()
    {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainBackOffice.class.getResource("/views/menu.fxml"));
            mainLayout = loader.load();

            FXMLLoader loader2  = new FXMLLoader();
            loader2.setLocation(MainBackOffice.class.getResource("/views/accueil.fxml"));
            mainLayout.setCenter(loader2.load());

            Scene scene = new Scene(mainLayout);
            stage= new Stage();
            stage.setTitle("");
            stage.setScene(scene);
            //stage.setMaximized(true);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            MainBackOffice.MainStage.close();

        } catch (IOException e) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null,e);
        }

    }
    /**
     * il s'agit d'une methode qui s'executera lorsque l'administrateur
     * cliquera sur accueil il sera redirigé vers cette page qui donne simplement des racp sur les donnees qu'il gere
     * @param actionEvent
     */
    @FXML
    public void accueil(javafx.event.ActionEvent actionEvent) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainBackOffice.class.getResource("/views/accueil.fxml"));
            mainLayout.setCenter(loader.load());
        } catch (IOException e) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null,e);
        }
        activeBtn(accueil,Laccueil);
    }

    /**
     * il s'agit d'une methode qui s'executera lorsque l'administrateur
     * cliquera sur gestion de division il sera redirigé vers toutes operations de crud relatives
     * a cette page il prend en parametre un action listener
     * @param actionEvent
     */
    @FXML
    public void division(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainBackOffice.class.getResource("/views/pageDivision.fxml"));
            mainLayout.setCenter(loader.load());
        } catch (IOException e) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null,e);
        }
        activeBtn(division,Ldivision);
    }

    /**
     * il s'agit d'une methode qui s'executera lorsque l'administrateur
     * cliquera sur gestion employes il sera redirigé vers toutes operations de crud relatives
     * a cette page il prend en parametre un action listener
     * @param actionEvent
     */
    @FXML
    public void employe(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainBackOffice.class.getResource("/views/pageEmploye.fxml"));
            mainLayout.setCenter(loader.load());
        } catch (IOException e) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null,e);
        }
        activeBtn(employe,Lemploye);
    }

    /**
     * il s'agit d'une methode qui s'executera lorsque l'administrateur
     * cliquera sur procedure il sera redirigé vers toutes operations de crud relatives
     * a cette page il prend en parametre un action listener
     * @param actionEvent
     */
    @FXML
    public void procedure(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainBackOffice.class.getResource("/views/pageProcedures.fxml"));
            mainLayout.setCenter(loader.load());
        } catch (IOException e) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null,e);
        }
        activeBtn(procedure,Lprocedure);
    }


    /**
     * cette methode permet simplement de donner un style au
     * menu losque l'administrateur choisira un boutton il changera de couleur
     * @param btn
     * @param img
     */

    private void activeBtn(Button btn , ImageView img) {

        Button[] btns = {accueil, division, employe, procedure};
        ImageView[] imgs = {Laccueil , Ldivision , Lemploye, Lprocedure};

        for ( Button b : btns) {
            b.setStyle("-fx-background-color:#073763");
        }

        for(ImageView i : imgs)
            i.setStyle("-fx-fill : #969494");
        btn.setStyle(
                "-fx-background-color: #FF9900;"
                        );

        img.setStyle("-fx-fill : #FFFFFF");
    }

    /**
     * il s'agit ici d'une methode qui s'executera
     * lorsque l'utlisateur voudra se deconnecter
     * elle prend an parametre un action listener
     * @param actionEvent
     */
    public void logOut(ActionEvent actionEvent) {
        MainBackOffice.MainStage.show();
        stage.close();
    }

}
