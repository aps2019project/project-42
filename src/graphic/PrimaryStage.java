package graphic;

import javafx.stage.Stage;

public class PrimaryStage {
    public static PrimaryStage primaryStage = new PrimaryStage();
    Stage stage = new Stage();

    public static PrimaryStage getInstance() {
        return primaryStage;
    }

    public static void setPrimaryStage(Stage stage) {
        primaryStage.stage = stage;
    }
}
