package main.java.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.java.dao.PatientDAO;
import main.java.db.DBConnection;
import main.java.model.Appointment;
import main.java.model.Employee;
import main.java.model.Patient;

import java.sql.Date;
import java.time.LocalDate;

public class Main extends Application {
    public static void main(String[] args) {
//        launch();

        LocalDate date = LocalDate.now();

        PatientDAO dao = new PatientDAO();
     Patient patient = new Patient("mohamed","salama","4545454545","","male", Date.valueOf(date) ,"01222222","egypt","o+",null, null, null);
//        Patient newPatient = dao.insert(patient);
//        System.out.println(newPatient.toString());
        boolean deleted = dao.delete("4545454545");
        System.out.println(deleted);
        try{

        if (DBConnection.getConnection() != null) {
            System.out.println("Connected Successfully!");
        } else {
            System.out.println("Connection Failed!");
        }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void start(Stage stage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 400, 300);

        stage.setScene(scene);
        stage.setTitle("JavaFX App");
        stage.show();
    }
}
