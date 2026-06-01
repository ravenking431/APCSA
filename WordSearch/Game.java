import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Game {
    protected String[] words = new String[1525];
    protected String[] alphabet = new String[25];

    public Game() {
        fillWords();
        fillAlphabet();
    }

   public void fillWords() {
    try {
        File file = new File("WordSearch/Words.txt");
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

   public void fillAlphabet() {
    try {
        File file = new File("WordSearch/Alphabet.txt");
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

    public int getRandomNumber() {
        return (int)(Math.random() *25);
    }

    public String getRandomLetter() {
        return alphabet[getRandomNumber()];
    }   
   

}