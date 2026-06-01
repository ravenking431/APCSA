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

    public boolean canPlaceDiagonal(String word, int row, int col) {
        if (row + word.length() > size || col + word.length() > size) {
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            if (board[row + i][col + i] != null && !board[row + i][col + i].equals(String.valueOf(word.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    public void placeHorizontal(String word, int row, int col) {
        for (int i = 0; i < word.length(); i++) {
            board[row][col + i] = String.valueOf(word.charAt(i));
        }
    }

    public boolean canPlaceHorizontal(String word, int row, int col) {
        if (col + word.length() > size) {
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            if (board[row][col + i] != null && !board[row][col + i].equals(String.valueOf(word.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    public void placeVertical(String word, int row, int col) {
        for (int i = 0; i < word.length(); i++) {
            board[row + i][col] = String.valueOf(word.charAt(i));
        }
    }

    public boolean canPlaceVertical(String word, int row, int col) {
        if (row + word.length() > size) {
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            if (board[row + i][col] != null && !board[row + i][col].equals(String.valueOf(word.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    public void placeWords() {
        for (int a = 0; a < targetWords.length; a++) {
                boolean placed = false;
                while (!placed) {
                int row = 0;
                int col = 0;
                if (canPlaceDiagonal(targetWords[a], row, col)) {
                    placeDiagonal(targetWords[a], row, col);
                    placed = true;
                } else if (canPlaceHorizontal(targetWords[a], row, col)) {
                    placeHorizontal(targetWords[a], row, col);
                    placed = true;
                } else if (canPlaceVertical(targetWords[a], row, col)) {
                    placeVertical(targetWords[a], row, col);
                    placed = true;
                } else {
                    row++;
                    col++;
                }
          }
        }
    }

    }