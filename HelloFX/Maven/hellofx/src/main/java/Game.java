import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Game {

    // Class done by Elizabeth

    protected String[] words = new String[1525]; // Array to store the list of words that can be used in the game, 
                                            // which is filled by reading from a file.
    protected String[] alphabet = new String[25]; // Array to store the letters of the alphabet, which is filled by reading from a file.

    // Constructor for the Game class, which calls the methods to fill the words and alphabet arrays by reading from their respective files.
    public Game() {
        fillWords();
        fillAlphabet();
    }

    // Method to fill the words array by reading from a file named "Words.txt".
    //  It uses a Scanner to read each line of the file and store it in the words array
    // if the file is not found, it catches the FileNotFoundException and prints an error message.
   public void fillWords() {
    try {
        File file = new File("/workspaces/APCSA/HelloFX/Maven/hellofx/src/main/resources/Words.txt");
        Scanner scanner = new Scanner(file);
        int index = 0;
        while (scanner.hasNextLine() && index < words.length) {
            words[index] = scanner.nextLine();
            index++;
        }
        scanner.close();
    } catch (FileNotFoundException e) {
        System.out.println("An error occurred while reading the file: " + e.getMessage());
    }
   }

    // Method to fill the alphabet array by reading from a file named "Alphabet.txt".
     // It uses a Scanner to read each line of the file and store it in the alphabet array.
     // if the file is not found, it catches the FileNotFoundException and prints an error message.
   public void fillAlphabet() {
    try {
        File file = new File("/workspaces/APCSA/HelloFX/Maven/hellofx/src/main/resources/Alphabet.txt");
        Scanner scanner = new Scanner(file);
        int index = 0;
        while (scanner.hasNextLine() && index < alphabet.length) {
            alphabet[index] = scanner.nextLine();
            index++;
        }
        scanner.close();
    } catch (FileNotFoundException e) {
        System.out.println("An error occurred while reading the file: " + e.getMessage());
    }
}

    // Method to generate a random number between 0 and 24, which is used to select a random letter from the alphabet array.
    public int getRandomNumber() {
        return (int)(Math.random() * 25);
    }

    // Method to generate a random number between 0 and the specified limit, which is used to select a random word from the words array.
    public int getRandomNumber(int limit) {
        return (int)(Math.random() * limit);
    }

    // Method to get a random letter from the alphabet array by using the getRandomNumber method to select a random index.
    public String getRandomLetter() {
        return alphabet[getRandomNumber()];
    }   
   

}