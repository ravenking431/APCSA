public class Main {
    public static void main(String[] args) {

        GameBoard test = new GameBoard(20, 15);

       test.placeWords();
        test.fillBoard();
        test.displayBoard();
        test.displayTargetWords();
    }
}