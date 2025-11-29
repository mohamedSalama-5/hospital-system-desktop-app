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
import main.java.db.DBConnection;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
        if (DBConnection.getConnection() != null) {
            System.out.println("Connected Successfully!");
        } else {
            System.out.println("Connection Failed!");
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
