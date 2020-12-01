package kakuro.ui;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;


public class HelloFX extends Application {

    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        HelloFX.primaryStage = primaryStage;
        HelloFX.primaryStage.setTitle("Kakuro");
        Gameboard gameboard = new Gameboard();

        Scene scene = new Scene(gameboard.getScene());
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

    public static void main(String[] args) {
        launch(HelloFX.class);
    }
}

