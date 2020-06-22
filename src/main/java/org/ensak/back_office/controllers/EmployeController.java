package org.ensak.back_office.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.ensak.back_office.MainBackOffice;
import org.ensak.back_office.metier.managerbeans.EmployeManager;
import org.ensak.back_office.modele.Employe;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeController implements Initializable {
    public TableView<Employe> tableEmploye;
    public TableColumn<Employe,String> numero;
    public TableColumn<Employe,String> nom;
    public TableColumn<Employe,String> prenom;
    public TableColumn<Employe, Button> consulter;


    /**
     * c'est la methode sui s'executera en entrée lorsque la vue pageEmploye sera sollicitée
     * elle permettra d'initialiser les ressources de la vue
     * @param location
     * @param resources
     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        load();
    }


    /**
     * cette methode permettra simplement de charger des données de l'employé du tableView
     */

    public void load()
    {
        numero.setCellValueFactory(new PropertyValueFactory<Employe,String>("numero"));
        nom.setCellValueFactory(new PropertyValueFactory<Employe,String >("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<Employe,String>("prenom"));
        consulter.setCellValueFactory(new PropertyValueFactory<Employe,Button>("consulter"));
            ObservableList<Employe> dataEmployeView = FXCollections.observableArrayList();
            for(org.ensak.back_office.metier.beans.Employe employe: EmployeManager.getAllEmployes())
            {
                Button button = new Button("Consulter");
                dataEmployeView.add(new Employe(String.valueOf(employe.getNumero()),employe.getNom(),employe.getPrenom(),button));
                button.setOnAction(event -> {
                    ConsulterEmployeController.id=employe.getNumero();
                    ConsulterEmployeController.consulterEmploye();});
            }

            tableEmploye.setItems(dataEmployeView);
    }

    /**
     * action qui sera exécuté lorsque l'utilisateur voudra enregistrer des informations dans
     * la base de données
     * @param actionEvent
     */

    public void ajouterEmploye(ActionEvent actionEvent) {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainBackOffice.class.getResource("/views/ajouterEmploye.fxml"));
            MenuController.mainLayout.setCenter(loader.load());
        } catch (IOException e) {
            Logger.getLogger(EmployeController.class.getName()).log(Level.SEVERE, null,e);
        }
    }

}
