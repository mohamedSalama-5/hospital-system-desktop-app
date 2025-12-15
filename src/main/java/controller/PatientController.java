package main.java.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class PatientController {
    @FXML
    private Button btnBackPatient;
    @FXML
    private void initialize() {
        btnBackPatient.setOnAction(event -> openHomePage());
    }

    public void openHomePage(){
        try {
            File fxmlFile = new File("src/main/resources/fxml/home.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlFile.toURI().toURL());
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) btnBackPatient.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
