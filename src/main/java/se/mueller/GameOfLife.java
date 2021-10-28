package se.mueller;
import java.io.InputStream;
import java.util.Scanner;

public class GameOfLife {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        boolean inputSessionIsFinished = true;
        
        Grid grid = new Grid();

        do {
            UserInput userInput = new UserInput().inputStream(scanner);
            grid.changeCellStatusToAlive(userInput.getIndexOfRow(), userInput.getIndexOfColumn());
            System.out.println("Are you done? - Press y to quit or any other key to continue");
            String quitInputSession = scanner.next();
            if (quitInputSession.equalsIgnoreCase("y")) {
                inputSessionIsFinished = false;
                grid.printArray();
            }

        } while (inputSessionIsFinished);


    }
}



