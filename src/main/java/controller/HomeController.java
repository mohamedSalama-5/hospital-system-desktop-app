package main.java.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.io.File;
import java.io.IOException;
public class HomeController {

    @FXML
    private Button btnPatient ;
    @FXML
    private Button btnAppointments;

    @FXML
    private void initialize() {
        btnPatient.setOnAction(event -> openPatientPage());
        btnAppointments.setOnAction(event -> openAppointmentPage());
    }

    public void openPatientPage(){
        try {
            File fxmlFile = new File("src/main/resources/fxml/patients1.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlFile.toURI().toURL());
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) btnPatient.getScene().getWindow();
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.setTitle("Patients Page");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void openAppointmentPage(){
        try {
            File fxmlFile = new File("src/main/resources/fxml/Appointments.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlFile.toURI().toURL());
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) btnPatient.getScene().getWindow();
            stage.setScene(scene);
            stage.setMaximized(true);
            stage.setTitle("Patients Page");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}



