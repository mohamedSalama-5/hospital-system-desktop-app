package main.java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.java.dao.AppointmentDAO;
import main.java.dao.ClinicDAO;
import main.java.model.Appointment;
import main.java.model.Clinic;

import java.sql.Date;
import java.util.List;

public class AddAppointmentController {

    @FXML private TextField txtPatientNationalId;
    @FXML private ComboBox<String> cbClinicName;
    @FXML private DatePicker dpAppointmentDate;
    @FXML private TextField txtStatus;
    @FXML private Button btnSave;
    @FXML private Button btnCancel;

    private AppointmentDAO appointmentDAO = new AppointmentDAO();
    private AppointmentController parentController;
    private ClinicDAO clinicDAO = new ClinicDAO();

    @FXML
    private void initialize() {
        List<Clinic> clinics = clinicDAO.getAll();
        for (Clinic clinic : clinics) {
            cbClinicName.getItems().add(clinic.getName());
        }

        btnSave.setOnAction(e -> saveAppointment());
        btnCancel.setOnAction(e -> ((Stage)btnCancel.getScene().getWindow()).close());
    }

    private void saveAppointment() {
        if(dpAppointmentDate.getValue() == null || txtPatientNationalId.getText().isEmpty()
                || cbClinicName.getValue() == null || txtStatus.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all fields!");
            alert.showAndWait();
            return;
        }

        try {
            Date appointmentDate = Date.valueOf(dpAppointmentDate.getValue());
            String status = txtStatus.getText();
            String patientNationalId = txtPatientNationalId.getText();
            String clinicName = cbClinicName.getValue();

            Appointment appointment = new Appointment(appointmentDate, status);
            appointmentDAO.insert(appointment, patientNationalId, clinicName);

            if(parentController != null){
                parentController.refreshTable();
            }

            ((Stage)btnSave.getScene().getWindow()).close();

        } catch (Exception ex){
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Failed to save appointment!");
            alert.showAndWait();
        }
    }

    public void setParentController(AppointmentController parentController) {
        this.parentController = parentController;
    }
}
