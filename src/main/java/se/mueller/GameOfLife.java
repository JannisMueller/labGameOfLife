package se.mueller;
import java.util.Scanner;

public class GameOfLife {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        playGameOfLife(scanner);
    }

    private static void playGameOfLife(Scanner scanner) {
        String gameSettings = getUserInputForGameSettings(scanner);

        switch (gameSettings) {
            case ("1") -> userDecidesStartPosition(scanner);
            case ("2") -> randomStartPositions(scanner);
            default -> System.out.println(("Something went wrong..."));
        }
    }

    private static void userDecidesStartPosition(Scanner scanner) {
        Board board = new Board();
        getUserInput(scanner, board);
        getGame(scanner, board);

    }

    private static void randomStartPositions(Scanner scanner) {
        Board randomBoard = new Board();
        randomBoard.initializeGridRandom();
        getGame(scanner, randomBoard);
    }

    private static void getGame(Scanner scanner, Board board) {
        board.printGrid();
        int numberOfGenerations = getNumberOfGenerationsToBeGenerated(scanner);
        generationsBuilder(board, numberOfGenerations);
    }

    private static void generationsBuilder(Board randomBoard, int numberOfGenerations) {
        for (int generation = 0; generation < numberOfGenerations; generation++) {
            randomBoard.insertNewGeneration(randomBoard.getNextGeneration());
            randomBoard.printGrid();
            System.out.println("__________");
        }
    }

    private static String getUserInputForGameSettings(Scanner scanner) {
        System.out.println(("Random start (Press 1) or set first generation yourself (Press 2)?"));
        return scanner.next();
    }

    private static void getUserInput(Scanner scanner, Board board) {
        boolean inputSessionIsFinished;
        userInputForStartPositions input = inputStream(scanner);
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

    private static boolean isInputSessionFinished(Scanner scanner, boolean inputSessionIsFinished) {
        System.out.println("Are you done? - Press y to quit or any other key to continue");
        String quitInputSession = scanner.next();
        if (quitInputSession.equalsIgnoreCase("y"))
            inputSessionIsFinished = false;
        return inputSessionIsFinished;
    }

    public static userInputForStartPositions inputStream(Scanner scanner) {
        System.out.println("Choose the cells that are alive?");
        System.out.print(("Row: "));
        int inputIndexOfRowFromUser = scanner.nextInt();
        System.out.print(("Column: "));
        int inputIndexOfColumnFromUser = scanner.nextInt();
        return new userInputForStartPositions(inputIndexOfRowFromUser,inputIndexOfColumnFromUser);
    }

}




