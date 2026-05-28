import java.util.Arrays;
public class GameBoard extends Game {
   
    private int size;
    private int numberOfWords;
    String[][] board = new String[size][size];
    String[] targetWords = new String[numberOfWords];

    public GameBoard(int size, int numberOfWords) {
        this.size = size;
        this.numberOfWords = numberOfWords;
    }

    public int getSize() {
        return size;
    }

    public int getNumberOfWords() {
        return numberOfWords;
    }

    public void fillLetter(int row, int col) {
        board[row][col] = getRandomLetter();
    }

}