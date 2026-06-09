import java.util.Random;
import java.util.Arrays;
import java.util.ArrayList;
public class GameBoard extends Game {

    // Class done by Elizabeth
   
    private int size; // Size of the game board (e.g., 10 for a 10x10 board)
    private int numberOfWords; // Number of words to be placed on the board
    private String[][] board; // 2D array representing the game board
    private String[] targetWords; // Array to store the target words that will be placed on the board
    ArrayList<Integer> usedIndices = new ArrayList<>(); // List to keep track of used word indices 
                                                        // in order to avoid duplicates

    // Constructor to initialize the game board with the specified size and number of words.
    // it also initializes the board and targetWords arrays, retrieves the words to be placed on the board,
    // and sorts the target words by length in descending order.
    public GameBoard(int size, int numberOfWords) {
        this.size = size;
        this.numberOfWords = numberOfWords;
        this.board = new String[size][size];
        this.targetWords = new String[numberOfWords];

        getWords(numberOfWords);
        sortWordsByLength(targetWords);
    }

    // returns the size of the game board.
    public int getSize() {
        return size;
    }

    // returns the number of words that will be placed on the game board.
    public int getNumberOfWords() {
        return numberOfWords;
    }

    // fills a cell on the game board with a random letter if it is currently empty (null).
    public void fillLetter(int row, int col) {
        if (board[row][col] == null) {
            board[row][col] = getRandomLetter();
        }
    }

    // traverses the list of available words and randomly selects words to be placed on the game board 
    // until the specified number of words is reached. It ensures that the selected words fit within 
    // the board size and that there are no duplicate words by checking against the usedIndices list.
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

    // returns a boolean value indicating whether the specified number (index) is already present in the 
    // usedIndices list, which is used to track the indices of words that have already been selected for 
    // placement on the game board. This method helps to prevent duplicate words from being selected.
    public boolean isMatch(int number) {
        for (int a = 0; a < usedIndices.size(); a++) {
            if (usedIndices.get(a) == number) {
                return true;
            }
        }
        return false;
    }

    // sorts the targetWords array so that the longest words are placed on the game board first 
    // in order to use the game board space more efficiently; increasing the chances of 
    // successfully placing all the words on the board.
    // sorts using a bubble sort algorithm
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

    // Prints out the gameboard to console in a grid format, 
    // visually representing the current state of the game board
    public void displayBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Prints out the list of target words to console so that players 
    // can see which words they need to find on the game board.
    public void displayTargetWords() {
        System.out.println("Target Words:");
        for (String word : targetWords) {
            System.out.println(word);
        }
    }

    // fills empty cells on the gameboard with random uppercase letters
    public void fillBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                fillLetter(i, j);
            }
        }
    }

    // Method to place a word on the game board in a diagonal direction starting from the specified row and column.
    public void placeDiagonal(String word, int row, int col) {
        for (int i = 0; i < word.length(); i++) {
            board[row + i][col + i] = String.valueOf(word.charAt(i));
        }
    }

    // Method to check if a word can be placed on the game board in a diagonal direction starting 
    // from the specified row and column. Returns true if the word can be placed without going 
    // out of bounds and without conflicting with existing letters on the board, otherwise returns false.
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

    // Method to place a word on the game board in a horizontal direction starting from the specified row and column.
    public void placeHorizontal(String word, int row, int col) {
        for (int i = 0; i < word.length(); i++) {
            board[row][col + i] = String.valueOf(word.charAt(i));
        }
    } 

    // Method to check if a word can be placed on the game board in a horizontal direction starting from the specified 
    // row and column. Returns true if the word can be placed without going out of bounds and without conflicting with
    //  existing letters on the board, otherwise returns false.
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

    // Method to place a word on the game board in a vertical direction starting from the specified row and column.
    public void placeVertical(String word, int row, int col) {
        for (int i = 0; i < word.length(); i++) {
            board[row + i][col] = String.valueOf(word.charAt(i));
        }
    }

    // Method to check if a word can be placed on the game board in a vertical direction starting from the specified
    // row and column. Returns true if the word can be placed without going out of bounds and without conflicting with
    // existing letters on the board, otherwise returns false.
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

    // Method to place the target words on the game board in random directions (diagonal, horizontal, vertical) and random starting positions.
    // It iterates through the targetWords array and attempts to place each word on the board until it is successfully placed. If a word 
    // cannot be placed after multiple attempts, it prints a message indicating that the word could not be placed.
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
    public String[][] getBoard() {
        return board;
    }
    public String[] getTargetWords() {
        return targetWords;
    }
}