package main.java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.java.dao.PatientDAO;
import main.java.model.Patient;

import java.sql.Date;

public class EditPatientController {

    @FXML private TextField txtFirstName, txtLastName, txtNationalId, txtEmail,
            txtPhone, txtAddress, txtBloodType, txtRoomId;
    @FXML private DatePicker dpBirthDate, dpDischargeDate;
    @FXML private ComboBox<String> cbGender;

    private Patient patient;
    private final PatientDAO patientDAO = new PatientDAO();

    @FXML
    private void initialize() {
        cbGender.getItems().addAll("Male", "Female");
    }

    public void setPatient(Patient patient) {
        this.patient = patient;

        txtFirstName.setText(patient.getFirstName());
        txtLastName.setText(patient.getLastName());
        txtNationalId.setText(patient.getNationalId());
        txtEmail.setText(patient.getEmail());
        txtPhone.setText(patient.getPhoneNumber());
        txtAddress.setText(patient.getAddress());
        txtBloodType.setText(patient.getBloodType());
        cbGender.setValue(patient.getGender());

        if (patient.getBirthDate() != null)
            dpBirthDate.setValue(patient.getBirthDate().toLocalDate());

        if (patient.getDischargeDate() != null)
            dpDischargeDate.setValue(patient.getDischargeDate().toLocalDate());

        if (patient.getRoomId() != null)
            txtRoomId.setText(String.valueOf(patient.getRoomId()));
    }

    @FXML
    private void updatePatient() {
        if (txtFirstName.getText().isEmpty() ||
                txtLastName.getText().isEmpty() ||
                txtNationalId.getText().isEmpty() ||
                dpBirthDate.getValue() == null ||
                cbGender.getValue() == null) {
            showAlert("Validation Error", "Please fill all required fields.");
            return;
        }

        Integer roomId = null;
        if (!txtRoomId.getText().isBlank()) {
            try {
                roomId = Integer.parseInt(txtRoomId.getText());
            } catch (NumberFormatException e) {
                showAlert("Invalid Input", "Room ID must be a number.");
                return;
            }
        }


        patient.setFirstName(txtFirstName.getText());
        patient.setLastName(txtLastName.getText());
        patient.setNationalId(txtNationalId.getText());
        patient.setEmail(txtEmail.getText());
        patient.setPhoneNumber(txtPhone.getText());
        patient.setAddress(txtAddress.getText());
        patient.setBloodType(txtBloodType.getText());
        patient.setGender(cbGender.getValue());
        patient.setBirthDate(Date.valueOf(dpBirthDate.getValue()));
        patient.setDischargeDate(dpDischargeDate.getValue() == null ? null : Date.valueOf(dpDischargeDate.getValue()));
        patient.setRoomId(roomId);

        Patient updated = patientDAO.updateById(patient.getId(), patient);
        if (updated != null) {
            close();
        } else {
            showAlert("Update Failed", "Could not update patient. Please try again.");
        }
    }

    @FXML
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
