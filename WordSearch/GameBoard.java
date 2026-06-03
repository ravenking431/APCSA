import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;
public class GameBoard extends Game {
   
    private int size;
    private int numberOfWords;
    private String[][] board;
    private String[] targetWords;
    ArrayList<Integer> usedIndices = new ArrayList<>();

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
        if (board[row][col] == null) {
            board[row][col] = getRandomLetter();
        }
    }

    public void getWords(int numberOfWords) {
        int a = 0;
        while (a < numberOfWords) {
            int randomIndex = getRandomNumber(words.length);
            String word = words[randomIndex];
            if (word.length() <= size && !isMatch(randomIndex)) {
                usedIndices.add(randomIndex);
                targetWords[a] = word.toUpperCase();
                a++;
            }
        }
    }

    public boolean isMatch(int number) {
        for (int a = 0; a < usedIndices.size(); a++) {
            if (usedIndices.get(a) == number) {
                return true;
            }
        }
        return false;
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
            String word = targetWords[a];
            boolean placed = false;
            while (!placed) {
                int direction = getRandomNumber(3);
                int row = getRandomNumber(size);
                int col = getRandomNumber(size);
                if (direction == 0 && canPlaceDiagonal(word, row, col)) {
                    placeDiagonal(word, row, col);
                    placed = true;
                } else if (direction == 1 && canPlaceHorizontal(word, row, col)) {
                    placeHorizontal(word, row, col);
                    placed = true;
                } else if (direction == 2 && canPlaceVertical(word, row, col)) {
                    placeVertical(word, row, col);
                    placed = true;
                }
            }
            if (!placed) {
                System.out.println("Could not place the word: " + word);
            }
        }
    }
  

    }