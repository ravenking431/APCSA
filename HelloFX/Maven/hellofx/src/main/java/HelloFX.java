import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class HelloFX extends Application {

    @Override
    public void start(Stage stage) {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + "." + " this is a test" + ".");
        
        StackPane root = new StackPane(l);

        Scene scene = new Scene(root, 1200, 670);

        scene.setOnMouseClicked(event -> {
            Color randomColor = Color.color(Math.random(), Math.random(), Math.random());
            root.setBackground(new Background(new BackgroundFill(randomColor, null, null)));
        });

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
