package org.ensak.back_office.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.ensak.back_office.MainBackOffice;
import org.ensak.back_office.metier.managerbeans.DivisionManager;
import org.ensak.back_office.modele.Division;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DivsionController implements Initializable {

    public TextField nomDivision;
    @FXML
    private TableView<Division> tableDivision;
    @FXML
    private TableColumn<Division,String> nom;
    @FXML
    private TableColumn<Division,String> chefDivision;
    @FXML
    private TableColumn<Division, Button> buton;
    @FXML
    private TableColumn<Division,String> numero;

    public DivsionController() {
    }

    /**
     * c'est la methode sui s'executera en entrée lorsque la vue pageDivision sera sollicitée
     * elle permettra d'initialiser les ressources de la vue
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        load();
    }

    public void ajouterDivision() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainBackOffice.class.getResource("/views/ajouterDivision.fxml"));
            MenuController.mainLayout.setCenter(loader.load());

        } catch (IOException e) {
            Logger.getLogger(DivsionController.class.getName()).log(Level.SEVERE, null,e);
        }
    }

    public void ajouterEmployeDivision(ActionEvent actionEvent) {

    }

    /**
     * cette methode permettra simplement de charger des données du tableView
     */
    public void load()
    {
        numero.setCellValueFactory(new PropertyValueFactory<Division,String>("numero"));
        nom.setCellValueFactory(new PropertyValueFactory<Division,String >("nom"));
        chefDivision.setCellValueFactory(new PropertyValueFactory<Division,String>("ChefDivision"));
        buton.setCellValueFactory(new PropertyValueFactory<Division,Button>("consulter"));
        try {

            ObservableList<Division> dataDivisionView = FXCollections.observableArrayList();
            for(org.ensak.back_office.metier.beans.Division division: DivisionManager.getAllDivisions())
            {
                Button button = new Button("Consulter");
                dataDivisionView.add(new Division(String.valueOf(division.getId()),division.getNomDivision(),division.getChefDivision().getNom(),button));
                button.setOnAction(event -> {
                    ConsulterDivisionController.consulterDivision(division.getId());});
            }

            tableDivision.setItems(dataDivisionView);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    /**
     * ajouter une division dans la base de données au travers de la vue ajouterDivision
     * @param actionEvent
     */

    public void addDivision(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainBackOffice.class.getResource("/views/ajouterDivision.fxml"));
            MenuController.mainLayout.setCenter(loader.load());

        } catch (IOException e) {
            Logger.getLogger(DivsionController.class.getName()).log(Level.SEVERE, null,e);
        }

    }
}
