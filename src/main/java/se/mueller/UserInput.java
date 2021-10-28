package se.mueller;
import java.io.InputStream;
import java.util.Scanner;

class UserInput {

    private int indexOfRow;
    private int indexOfColumn;

    int inputIndexOfRowFromUser;
    int inputIndexOfColumnFromUser;

    public UserInput(int indexOfRow, int indexOfColumn) {
        this.indexOfRow = indexOfRow;
        this.indexOfColumn = indexOfColumn;
    }

    public UserInput() {

    }

    public UserInput inputStream(Scanner scanner) {
        System.out.println("Which cells are alive");
        inputIndexOfRowFromUser = scanner.nextInt();
        inputIndexOfColumnFromUser = scanner.nextInt();
        return new UserInput(inputIndexOfRowFromUser,inputIndexOfColumnFromUser);
    }

    public int getIndexOfRow() {
        return indexOfRow;
    }

    public int getIndexOfColumn() {
        return indexOfColumn;
    }
}
