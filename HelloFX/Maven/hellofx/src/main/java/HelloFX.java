import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

//take the size from the GameBoard class so that it can autoadjust to whatever is inputted
//take the list of randomly generated words and take their letters displaying them in each gridspace, then fill the rest with random letters
//add the list of words at the bottom of the screen 
//add an interactable ui that crosses out words when you drag over them, if they're proper
//crosses them out at the bottom as well
//just take the array that has everything already inputted in it



public class HelloFX extends Application {

    // @Override
    // public void start(Stage stage) {
    //     String javaVersion = System.getProperty("java.version");
    //     String javafxVersion = System.getProperty("javafx.version");
    //     Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
    //     Scene scene = new Scene(new StackPane(l), 640, 480);
    //     stage.setScene(scene);
    //     stage.show();
    // }

    private final Button[][] buttons = new Button[10][20];
    private boolean xTurn = true;
    private boolean gameOver = false;
    private final Label status = new Label("X's turn");

    @Override
    public void start(Stage stage) {
        GridPane board = new GridPane();

        for (int row = 0; row < 10; row++) { //14 max reccomended,10 if we wants words at the bottom,these adjusts the grid size, max of whatever the button array is set to
            for (int col = 0; col < 20; col++) { //20 max reccomended 
                Button button = new Button("");
                button.setPrefSize(60, 60); //adjusts the button size
                button.setStyle("-fx-font-size: 25px;"); //font of the text in the buttons

                final int r = row;
                final int c = col;

                button.setOnAction(e -> handleMove(r, c));

                buttons[row][col] = button;
                board.add(button, col, row);
            }
        }

        Button reset = new Button("Reset");
        reset.setOnAction(e -> resetGame());

        BorderPane root = new BorderPane();
        root.setTop(status);
        root.setCenter(board);
        root.setBottom(reset);

        Scene scene = new Scene(root, 1100, 690); //adjusts the window size
        stage.setTitle("The Best WordSearch Game"); //adjusts the window pane of text
        stage.setScene(scene);
        stage.show();
    }

    private void handleMove(int row, int col) {
        if (gameOver || !buttons[row][col].getText().isEmpty()) {
            return;
        }

        buttons[row][col].setText(xTurn ? "Q" : "A"); //adjusts the text that appears in the buttons

        if (hasWinner()) {
            status.setText((xTurn ? "X" : "O") + " wins!");
            gameOver = true;
        } else if (isBoardFull()) {
            status.setText("It's a draw!");
            gameOver = true;
        } else {
            xTurn = !xTurn;
            status.setText((xTurn ? "X" : "O") + "'s turn");
        }
    }

    private boolean hasWinner() {
        for (int i = 0; i < 3; i++) {
            if (same(buttons[i][0], buttons[i][1], buttons[i][2])) return true;
            if (same(buttons[0][i], buttons[1][i], buttons[2][i])) return true;
        }

        return same(buttons[0][0], buttons[1][1], buttons[2][2])
            || same(buttons[0][2], buttons[1][1], buttons[2][0]);
    }

    private boolean same(Button a, Button b, Button c) {
        return !a.getText().isEmpty()
            && a.getText().equals(b.getText())
            && b.getText().equals(c.getText());
    }

    private boolean isBoardFull() {
        for (Button[] row : buttons) {
            for (Button button : row) {
                if (button.getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetGame() {
        for (Button[] row : buttons) {
            for (Button button : row) {
                button.setText("");
            }
        }

        xTurn = true;
        gameOver = false;
        status.setText("X's turn");
    }

    public static void main(String[] args) {
        launch(args);
    }
}