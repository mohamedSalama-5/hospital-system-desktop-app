package main.java.controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.dao.PatientDAO;
import main.java.model.Patient;

import java.io.File;
import java.io.IOException;

public class PatientController {

    @FXML private TableView<Patient> tablePatient;
    @FXML private TableColumn<Patient, Integer> colIDPatient;
    @FXML private TableColumn<Patient, String> colFirstNamePatient;
    @FXML private TableColumn<Patient, String> colLastNamePatient;
    @FXML private TableColumn<Patient, String> colNationalIdPatient;
    @FXML private TableColumn<Patient, String> colGenderPatient;
    @FXML private TableColumn<Patient, String> colPhonePatient;
    @FXML private TableColumn<Patient, String> colAddressPatient;
    @FXML private TableColumn<Patient, String> colBloodTypePatient;
    @FXML private TableColumn<Patient, java.sql.Date> colAdmissionDatePatient;
    @FXML private TableColumn<Patient, java.sql.Date> colDischargeDatePatient;
    @FXML private TableColumn<Patient, Integer> colRoomIdPatient;

    @FXML private TextField txtSearchPatient;
    @FXML private Button btnSearchPatient;
    @FXML private Button btnAddPatient;
    @FXML private Button btnEditPatient;
    @FXML private Button btnDeletePatient;
    @FXML private Button btnBackPatient;

    private final PatientDAO patientDAO = new PatientDAO();

    @FXML
    private void initialize() {
        colIDPatient.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        colFirstNamePatient.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getFirstName()));
        colLastNamePatient.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getLastName()));
        colNationalIdPatient.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNationalId()));
        colGenderPatient.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getGender()));
        colPhonePatient.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getPhoneNumber()));
        colAddressPatient.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getAddress()));
        colBloodTypePatient.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getBloodType()));
        colAdmissionDatePatient.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getAdmissionDate()));
        colDischargeDatePatient.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDischargeDate())); // جديد
        colRoomIdPatient.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getRoomId()));

        refreshTable();

        btnSearchPatient.setOnAction(e -> searchPatient());
        btnAddPatient.setOnAction(e -> openAddPatient());
        btnEditPatient.setOnAction(e -> openEditPatient());
        btnDeletePatient.setOnAction(e -> deletePatient());
        btnBackPatient.setOnAction(e -> openHomePage());
    }

    private void refreshTable() {
        tablePatient.setItems(FXCollections.observableArrayList(patientDAO.getAll()));
    }

    private void searchPatient() {
        String keyword = txtSearchPatient.getText();
        if (keyword.isEmpty()) {
            refreshTable();
        } else {
            tablePatient.setItems(FXCollections.observableArrayList(patientDAO.searchByName(keyword)));
        }
    }

    private void openAddPatient() {
        try {
            FXMLLoader loader = new FXMLLoader(new File("src/main/resources/fxml/add_patient.fxml").toURI().toURL());
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Add Patient");
            stage.showAndWait();
            refreshTable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openEditPatient() {
        Patient selectedPatient = tablePatient.getSelectionModel().getSelectedItem();
        if (selectedPatient == null) {
            showAlert("Please select a patient to edit.");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(new File("src/main/resources/fxml/edit_patient.fxml").toURI().toURL());
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Edit Patient");

            EditPatientController controller = loader.getController();
            controller.setPatient(selectedPatient);

            stage.showAndWait();
            refreshTable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deletePatient() {
        Patient selectedPatient = tablePatient.getSelectionModel().getSelectedItem();
        if (selectedPatient == null) {
            showAlert("Please select a patient to delete.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this patient?", ButtonType.YES, ButtonType.NO);
        confirm.showAndWait();
        if (confirm.getResult() == ButtonType.YES) {
            patientDAO.delete(selectedPatient.getNationalId());
            refreshTable();
        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private void openHomePage() {
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
