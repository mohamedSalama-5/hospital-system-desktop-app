package main.java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.java.dao.PatientDAO;
import main.java.model.Patient;

import java.sql.Date;

public class AddPatientController {

    @FXML private TextField txtFirstName, txtLastName, txtNationalId, txtEmail,
            txtPhone, txtAddress, txtBloodType, txtRoomId;
    @FXML private DatePicker dpBirthDate, dpDischargeDate;
    @FXML private ComboBox<String> cbGender;

    private final PatientDAO patientDAO = new PatientDAO();

    @FXML
    private void initialize() {

        cbGender.getItems().addAll("Male", "Female");
    }

    @FXML
    private void savePatient() {


        if (txtFirstName.getText().isEmpty() ||
                txtLastName.getText().isEmpty() ||
                txtNationalId.getText().isEmpty() ||
                dpBirthDate.getValue() == null ||
                cbGender.getValue() == null) {

            showAlert("Validation Error", "Please fill required fields");
            return;
        }


        Integer roomId = null;
        if (!txtRoomId.getText().isBlank()) {
            try {
                roomId = Integer.parseInt(txtRoomId.getText());
            } catch (NumberFormatException e) {
                showAlert("Invalid Input", "Room ID must be a number");
                return;
            }
        }


        Patient patient = new Patient(
                txtFirstName.getText(),
                txtLastName.getText(),
                txtNationalId.getText(),
                txtEmail.getText(),
                txtPhone.getText(),
                Date.valueOf(dpBirthDate.getValue()),
                cbGender.getValue(),
                txtAddress.getText(),
                txtBloodType.getText(),
                dpDischargeDate.getValue() == null
                        ? null
                        : Date.valueOf(dpDischargeDate.getValue()),
                roomId
        );


        patientDAO.insert(patient);


        close();
    }

    private void close() {
        Stage stage = (Stage) txtFirstName.getScene().getWindow();
        stage.close();
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
