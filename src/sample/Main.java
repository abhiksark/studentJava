package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("studentLoginUpdated.fxml"));
        primaryStage.setTitle("Student Management");
        primaryStage.setScene(new Scene(root, 580, 460));
        primaryStage.show();
    }
}
