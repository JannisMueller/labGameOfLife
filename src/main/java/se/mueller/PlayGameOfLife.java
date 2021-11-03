package se.mueller;
import java.util.Scanner;

class PlayGameOfLife {

    private PlayGameOfLife() {
    }

    static void playGameOfLife(Scanner scanner) {
        String gameSettings = getUserInputForGameSettings(scanner);

        switch (gameSettings) {
            case ("1") -> randomStartPositions(scanner);
            case ("2") -> userDecidesStartPosition(scanner);
            default -> System.out.println(("Something went wrong..."));
        }
    }

    private static void userDecidesStartPosition(Scanner scanner) {
        Grid board = new Grid();
        getUserInput(scanner, board);
        getGame(scanner, board);

    }

    private static void randomStartPositions(Scanner scanner) {
        Grid randomBoard = new Grid();
        randomBoard.initializeGridRandom();
        getGame(scanner, randomBoard);
    }

    private static void getGame(Scanner scanner, Grid board) {
        printGrid(board.getGrid());
        int numberOfGenerations = getNumberOfGenerationsToBeGenerated(scanner);
        generationsBuilder(board, numberOfGenerations);
    }

    private static void generationsBuilder(Grid randomBoard, int numberOfGenerations) {
        for (int generation = 0; generation < numberOfGenerations; generation++) {
            randomBoard.insertNewGeneration(randomBoard.getNextGeneration());
            printGrid(randomBoard.getGrid());
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("__________");
        }
    }

    private static String getUserInputForGameSettings(Scanner scanner) {
        System.out.println(("Random start (Press 1) or set first generation yourself (Press 2)?"));
        return scanner.next();
    }

    private static void getUserInput(Scanner scanner, Grid board) {
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

    private static boolean isInputSessionFinished(Scanner scanner, boolean inputSessionIsFinished) {
        System.out.println("Are you done? - Press y to quit or any other key to continue");
        String quitInputSession = scanner.next();
        if (quitInputSession.equalsIgnoreCase("y"))
            inputSessionIsFinished = false;
        return inputSessionIsFinished;
    }

    private static UserInput inputStream(Scanner scanner) {
        System.out.println("Choose the cells that are alive?");
        System.out.print(("Row: "));
        int inputIndexOfRowFromUser = scanner.nextInt();
        System.out.print(("Column: "));
        int inputIndexOfColumnFromUser = scanner.nextInt();
        return new UserInput(inputIndexOfRowFromUser,inputIndexOfColumnFromUser);
    }

    private static void printGrid(int[][] grid) {
        for (int indexRow = 0; indexRow < Grid.ROWS_GRID; indexRow++) {
            for (int indexColumn = 0; indexColumn < Grid.COLUMNS_GRID; indexColumn++) {
                if (grid[indexRow][indexColumn] == 0)
                    System.out.print(".");
                else
                    System.out.print("*");
            }
            System.out.println();
        }
    }

}




