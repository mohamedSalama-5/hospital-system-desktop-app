package main.java.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import main.java.db.DBConnection;

import java.io.File;

public class Main extends Application {

    public static void main(String[] args) {

        try {
            if (DBConnection.getConnection() != null) {
                System.out.println("Connected Successfully!");
            } else {
                System.out.println("Connection Failed!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        File fxmlFile = new File("src/main/resources/fxml/home.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlFile.toURI().toURL());
        Scene scene = new Scene(loader.load());
        stage.setTitle("Hospital App");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
}
