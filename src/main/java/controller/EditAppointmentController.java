package main.java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.java.dao.AppointmentDAO;
import main.java.model.Appointment;

import java.sql.Date;

public class EditAppointmentController {

    @FXML private TextField txtPatientNationalId;
    @FXML private TextField txtClinicName;
    @FXML private DatePicker dpAppointmentDate;
    @FXML private TextField txtStatus;
    @FXML private Button btnSave;
    @FXML private Button btnCancel;

    private AppointmentDAO appointmentDAO = new AppointmentDAO();
    private Appointment appointment;

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
        txtPatientNationalId.setText(appointment.getPatientName());
        txtClinicName.setText(appointment.getClinicName());
        dpAppointmentDate.setValue(appointment.getAppointmentDate().toLocalDate());
        txtStatus.setText(appointment.getStatus());
    }

    @FXML
    private void initialize() {
        btnSave.setOnAction(e -> saveChanges());
        btnCancel.setOnAction(e -> ((Stage)btnCancel.getScene().getWindow()).close());
    }

    private void saveChanges() {
        appointment.setAppointmentDate(Date.valueOf(dpAppointmentDate.getValue()));
        appointment.setStatus(txtStatus.getText());
        appointment.setClinicName(txtClinicName.getText());
        appointmentDAO.updateAppointment(appointment);
        ((Stage)btnSave.getScene().getWindow()).close();
    }
}
