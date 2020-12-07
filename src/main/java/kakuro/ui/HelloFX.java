package kakuro.ui;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Java FX aloitus

public class HelloFX extends Application {

    public static Stage gameWindow;

    @Override
    public void start(Stage gameWindow) throws Exception {
        gameWindow.setTitle("Kakuro");
        GameUi gameUi = new GameUi(gameWindow);

        Scene scene = gameUi.getScene();
        gameWindow.setScene(scene);
        gameWindow.show();
        
    }

    public static void main(String[] args) {
        launch(HelloFX.class);
    }
}

