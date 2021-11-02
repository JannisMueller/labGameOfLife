package se.mueller;
import java.util.Scanner;

public class GameOfLife {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        menuGameOfLife(scanner);
    }

    private static void menuGameOfLife(Scanner scanner) {
        String gameSettings = getUserInputForGameSettings(scanner);

        switch (gameSettings){
            case ("1"):
                caseOne(scanner);
                break;
            case ("2"):
                caseTwo();
                break;
            default:
                System.out.println(("Something went wrong..."));
        }
    }

    private static void caseTwo() {
        Board randomBoard = new Board();
        randomBoard.initializeGridRandom();
        randomBoard.printGrid();
        printOutBorder();
        generationBuilder(randomBoard).printGrid();
        printOutBorder();
    }

    private static void printOutBorder() {
        System.out.println(("__________"));
        System.out.println(("__________"));
    }

    private static void caseOne(Scanner scanner) {
        Board board = new Board();
        getUserInput(scanner, board);
        board.printGrid();
        printOutBorder();
        generationBuilder(board).printGrid();
        printOutBorder();
    }

    private static String getUserInputForGameSettings(Scanner scanner) {
        System.out.println(("Random start (Press 1) or set first generation yourself (Press 2)?"));
        return scanner.next();
    }

    private static void getUserInput(Scanner scanner, Board board) {
        boolean inputSessionIsFinished;
        UserInput input = inputStream(scanner);
        board.changeCellStatusToAlive(new Position(input.indexOfRow(), input.indexOfColumn()));
        inputSessionIsFinished = isInputSessionFinished(scanner, true);

        while (inputSessionIsFinished) {
            input = inputStream(scanner);
            board.changeCellStatusToAlive(new Position(input.indexOfRow(), input.indexOfColumn()));
            inputSessionIsFinished = isInputSessionFinished(scanner, true);
        }
    }

    private static int getNumberOfGenerationsToBeGenerated(Scanner scanner) {
        System.out.println(("How many generations do you want to see?"));
        return scanner.nextInt();
    }

    private static Board generationBuilder(Board board) {
        return new Board(board.getNextGeneration());
    }

    private static boolean isInputSessionFinished(Scanner scanner, boolean inputSessionIsFinished) {
        System.out.println("Are you done? - Press y to quit or any other key to continue");
        String quitInputSession = scanner.next();
        if (quitInputSession.equalsIgnoreCase("y"))
            inputSessionIsFinished = false;
        return inputSessionIsFinished;
    }

    public static UserInput inputStream(Scanner scanner) {
        System.out.println("Choose the cells that are alive?");
        System.out.print(("Row: "));
        int inputIndexOfRowFromUser = scanner.nextInt();
        System.out.print(("Column: "));
        int inputIndexOfColumnFromUser = scanner.nextInt();
        return new UserInput(inputIndexOfRowFromUser,inputIndexOfColumnFromUser);
    }

}




