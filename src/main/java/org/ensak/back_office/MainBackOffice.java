package org.ensak.back_office;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainBackOffice extends Application {
    public static Stage MainStage;
    @Override
    public void start(Stage stage) throws Exception {
        MainStage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
        MainStage.setTitle("User Login");
        MainStage.setScene(new Scene(root, 800, 500));
        MainStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
