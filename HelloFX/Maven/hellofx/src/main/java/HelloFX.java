import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import java.util.HashMap;
import java.util.Map; //imports dependencies 
//look at the README.MD on how to run the program
public class HelloFX extends Application { 

    // Class done by Duncan

    private final Button[][] buttons = new Button[20][20]; // 2D array to store the buttons representing the game board
    private final Map<String, Label> wordLabels = new HashMap<>(); // Map to store the labels for the target words
    private int startRow = -1; // Variables to track the starting position of a word selection
    private int startCol = -1; 

    @Override
    public void start(Stage stage) { 
            
            /*makes the game board and puts the words into the grid as well as filling empty spots with letters   */

        GameBoard gameBoard = new GameBoard(20, 20);
        gameBoard.placeWords(); 
        gameBoard.fillBoard(); 
        //retrieves the 2d array of the gameboard with the letters and words, and the array with the target words
        String[][] board = gameBoard.getBoard(); 
        String[] words = gameBoard.getTargetWords(); 

        GridPane grid = new GridPane(); // creates a new GridPane layout to arrange the buttons representing the game board in a grid format

        for (int row = 0; row < 20; row++) { //loops through the board
            for (int col = 0; col < 20; col++) { 
                //creates a new button for each square, inserting the proper letter, setting the size of the button and letter
                Button button = new Button(board[row][col]); 
                button.setPrefSize(40, 40); 
                button.setStyle("-fx-font-size: 16px;"); 
                
                final int r = row; 
                final int c = col;

                button.setOnAction(e -> {  
                    if (startRow == -1) { //sets the starting point of the word selection and changes the clicked button to show its been clicked
                        startRow = r;
                        startCol = c;
                        buttons[startRow][startCol].setStyle("-fx-font-size: 16px; -fx-background-color: #add8e6;");
                    } else { // when the second button is clicked, it takes the word that was selected and checks if it is a target word
                        String selectedWord = buildWord(startRow, startCol, r, c);
                        Label label = wordLabels.get(selectedWord);

                        if (label != null) { // if a match is found it highlights the word on the board and underlines the word in the list of target words
                            label.setStyle("-fx-font-size: 16px; -fx-text-fill: #a0a0a0; -fx-underline: true;");
                            highlightWord(startRow, startCol, r, c);
                        } else {
                            buttons[startRow][startCol].setStyle("-fx-font-size: 16px;"); //if there is no match it resets the button style
                        }
                        // resets for the next selection
                        startRow = -1; 
                        startCol = -1;
                    }
                });
                // stores the button in the buttons array and adds it to the grid
                buttons[row][col] = button;
                grid.add(button, col, row);
            }
        }
        //creates a vbox to show the targetwords
        VBox wordBox = new VBox(5);
        wordBox.setStyle("-fx-padding: 10;");
        //adds a title 
        Label title = new Label("WORDS");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        wordBox.getChildren().add(title);
        // loops through the target words and creates a label for each word
        for (String word : words) {
            Label label = new Label(word);
            label.setStyle("-fx-font-size: 16px;");
            // stores the label in the wordLabels map and adds it to the wordBox
            wordLabels.put(word.toUpperCase(), label);
            wordBox.getChildren().add(label); 
        }
        //creates the layout on where to arrange the grid and the list of target words
        BorderPane root = new BorderPane();
        root.setCenter(grid);
        root.setRight(wordBox);
        //sets the size of the window
        Scene scene = new Scene(root, 1280, 690); 
        //adds a title to the titlebar
        stage.setTitle("The Best WordSearch Game");
        stage.setScene(scene);
        stage.show();
    }    
    // builds the word that is selected by the user by traversing from the starting button to the ending button
    private String buildWord(int startRow, int startCol, int endRow, int endCol) {
        if (!(startRow == endRow || startCol == endCol || Math.abs(endRow - startRow) == Math.abs(endCol - startCol))) {
            return "";
        }
        //determines which direction is selected
        StringBuilder word = new StringBuilder();
        int rowStep = Integer.compare(endRow, startRow);
        int colStep = Integer.compare(endCol, startCol);
        int row = startRow;
        int col = startCol;
        //turns the letters into a word by traversing in the selected direction
        while (true) {
            word.append(buttons[row][col].getText());
            
            if (row == endRow && col == endCol) {
                break;
            }
            
            row += rowStep;
            col += colStep;
        }
        // returns the selected word in uppercase to stay consistent 
        return word.toString().toUpperCase();
    }
    //highlights the selected buttons, changes the background color and strikes through the text on the right
    private void highlightWord(int startRow, int startCol, int endRow, int endCol) {
        int rowStep = Integer.compare(endRow, startRow);
        int colStep = Integer.compare(endCol, startCol);
        int row = startRow;
        int col = startCol;
        
        while (true) {
            buttons[row][col].setStyle("-fx-font-size: 16px; -fx-background-color: #00ff0033; -fx-text-fill: #555555; -fx-strikethrough: true;");
            //checks if you're at the end position, if so it breaks the loop
            if (row == endRow && col == endCol) {
                break;
            }
            
            row += rowStep;
            col += colStep;
        }
    }

    public static void main(String[] args) {
        launch();
    }
}