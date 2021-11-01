package se.mueller;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

public class GameOfLife {

    public static void main(String[] args) {



        final Scanner scanner = new Scanner(System.in);
        boolean inputSessionIsFinished;

        Board board = new Board();
        board.printGrid();

        UserInput input = inputStream(scanner);
        board.changeCellStatusToAlive(new Position(input.getIndexOfRow(), input.getIndexOfColumn()));
        inputSessionIsFinished = isInputSessionFinished(scanner, true);

        while (inputSessionIsFinished) {
            input = inputStream(scanner);
            board.changeCellStatusToAlive(new Position(input.getIndexOfRow(), input.getIndexOfColumn()));
            inputSessionIsFinished = isInputSessionFinished(scanner, true);
        }

        System.out.println(("How many generations do you want to see?"));
        int numberOfGenerationsToBeGenerated = scanner.nextInt();

        generationBuilder(numberOfGenerationsToBeGenerated, board);

    }


    private static void generationBuilder(int numberOfGenerationsToBeGenerated, Board board) {
        board.printGrid();
        IntStream.range(0, numberOfGenerationsToBeGenerated).forEach(i -> {
            System.out.println(("--Generation " + (i + 1) + "--"));
            Board nextGeneration = new Board(board.nextGeneration());
            nextGeneration.printGrid();
        });
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




