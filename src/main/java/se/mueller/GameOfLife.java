package se.mueller;
import java.util.Scanner;

public class GameOfLife {

    public static void main(String[] args) {

        final Scanner scanner = new Scanner(System.in);
        boolean inputSessionIsFinished;

        Grid grid = new Grid();
        grid.printArray();

        do {
            UserInput input = inputStream(scanner);
            grid.changeCellStatusToAlive(input.getIndexOfRow(), input.getIndexOfColumn());
            inputSessionIsFinished = isInputSessionFinished(scanner, true);

        } while (inputSessionIsFinished);
        grid.printArray();


    }

    private static boolean isInputSessionFinished(Scanner scanner, boolean inputSessionIsFinished) {
        System.out.println("Are you done? - Press y to quit or any other key to continue");
        String quitInputSession = scanner.next();
        if (quitInputSession.equalsIgnoreCase("y")) {
            inputSessionIsFinished = false;

        }
        return inputSessionIsFinished;
    }


    public static UserInput inputStream(Scanner scanner) {
        System.out.println("Choose the cells that are alive?");
        int inputIndexOfRowFromUser = scanner.nextInt();
        int inputIndexOfColumnFromUser = scanner.nextInt();
        return new UserInput(inputIndexOfRowFromUser,inputIndexOfColumnFromUser);
    }
}




