package main.java.controller;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.java.dao.AppointmentDAO;
import main.java.model.Appointment;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class AppointmentController {

    @FXML private Button btnBackAppointments;
    @FXML private TableView<Appointment> tableAppointments;
    @FXML private TableColumn<Appointment,Integer> colAppointmentID;
    @FXML private TableColumn<Appointment,String> colAppointmentsPatientName;
    @FXML private TableColumn<Appointment,String> colAppointmentsClinicName;
    @FXML private TableColumn<Appointment, Date> colAppointmentsDate;
    @FXML private TableColumn<Appointment,String> colAppointmentsStatus;
    @FXML private Button btnSearchAppointments;
    @FXML private TextField txtSearchAppointments;

    @FXML private Button btnAddAppointment;
    @FXML private Button btnEditAppointment;
    @FXML private Button btnDeleteAppointment;

    private AppointmentDAO appointmentDAO;

    @FXML
    private void initialize() {
        btnBackAppointments.setOnAction(event -> openHomePage());
        colAppointmentID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colAppointmentsPatientName.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        colAppointmentsClinicName.setCellValueFactory(new PropertyValueFactory<>("clinicName"));
        colAppointmentsDate.setCellValueFactory(new PropertyValueFactory<>("appointmentDate"));
        colAppointmentsStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        appointmentDAO = new AppointmentDAO();
        tableAppointments.setItems(FXCollections.observableArrayList(appointmentDAO.getAllAppointment()));

        btnSearchAppointments.setOnAction(e -> {
            String keyword = txtSearchAppointments.getText();
            tableAppointments.setItems(FXCollections.observableArrayList(appointmentDAO.getAppointmentByNationalId(keyword)));
        });

        // add
        btnAddAppointment.setOnAction(e -> openAddPage());

        // edit
        btnEditAppointment.setOnAction(e -> openEditPage());

        // delete
        btnDeleteAppointment.setOnAction(e -> deleteAppointment());
    }

    private void openAddPage() {
        try {
            FXMLLoader loader = new FXMLLoader(new File("src/main/resources/fxml/AddAppointment.fxml").toURI().toURL());
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(loader.load()));
            stage.showAndWait();
            refreshTable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openEditPage() {
        Appointment selected = tableAppointments.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Please select an appointment to edit.");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(new File("src/main/resources/fxml/EditAppointment.fxml").toURI().toURL());
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(loader.load()));

            EditAppointmentController controller = loader.getController();
            controller.setAppointment(selected);

            stage.showAndWait();
            refreshTable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteAppointment() {
        Appointment selected = tableAppointments.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Please select an appointment to delete.");
            return;
        }
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this appointment?", ButtonType.YES, ButtonType.NO);
        confirm.showAndWait();
        if (confirm.getResult() == ButtonType.YES) {
            appointmentDAO.deleteByAppointmentId(selected.getId());
            refreshTable();
        }
    }

    public void refreshTable() {
        tableAppointments.setItems(FXCollections.observableArrayList(appointmentDAO.getAllAppointment()));
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public void openHomePage() {
        try {
            File fxmlFile = new File("src/main/resources/fxml/home.fxml");
            FXMLLoader loader = new FXMLLoader(fxmlFile.toURI().toURL());
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) btnBackAppointments.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
