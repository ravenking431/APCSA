public class Main {
    public static void main(String[] args) {

        GameBoard test = new GameBoard(5, 2);

        test.fillBoard();
        test.displayBoard();
        test.displayTargetWords();
    }
}