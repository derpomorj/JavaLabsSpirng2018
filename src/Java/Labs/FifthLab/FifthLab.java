package Java.Labs.FifthLab;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FifthLab extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FifthLab.fxml"));
        primaryStage.setTitle("FIFTH LAB");
        primaryStage.setScene(new Scene(root, 1200, 750));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
