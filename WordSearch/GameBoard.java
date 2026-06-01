import java.util.Random;
import java.util.Arrays;
public class GameBoard extends Game {
   
    private int size;
    private int numberOfWords;
    private String[][] board;
    private String[] targetWords;

    public GameBoard(int size, int numberOfWords) {
        this.size = size;
        this.numberOfWords = numberOfWords;
        this.board = new String[size][size];
        this.targetWords = new String[numberOfWords];

        getWords(numberOfWords);
        sortWordsByLength(targetWords);
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

    public void getWords(int numberOfWords) {
        int a = 0;
        while (a < numberOfWords) {
            String word = words[getRandomNumber()];
            if (word.length() <= size && !Arrays.asList(targetWords).contains(word)) {
                targetWords[a] = word;
                a++;
            }
        }
    }

    public void sortWordsByLength(String[] words) {
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (words[j].length() > words[i].length()) {
                    String temp = words[i];
                    words[i] = words[j];
                    words[j] = temp;
                }
            }
        }
    }

    public void displayBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void displayTargetWords() {
        System.out.println("Target Words:");
        for (String word : targetWords) {
            System.out.println(word);
        }
    }

    public void fillBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                fillLetter(i, j);
            }
        }
    }

    public void placeDiagonal(String word, int row, int col) {
        for (int i = 0; i < word.length(); i++) {
            board[row + i][col + i] = String.valueOf(word.charAt(i));
        }
    }

    public void placeHorizontal(String word, int row, int col) {
        for (int i = 0; i < word.length(); i++) {
            board[row][col + i] = String.valueOf(word.charAt(i));
        }
    }

    public void placeVertical(String word, int row, int col) {
        for (int i = 0; i < word.length(); i++) {
            board[row + i][col] = String.valueOf(word.charAt(i));
        }
    }

   
}