package se.mueller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

class UserInput {

    private int indexOfRow;
    private int indexOfColumn;


    public UserInput(int indexOfRow, int indexOfColumn) {
        this.indexOfRow = indexOfRow;
        this.indexOfColumn = indexOfColumn;
    }

    public UserInput() {

    }

    public UserInput inputStream() {
        UserInput input;
        InputStream stream = System.in;
        Scanner scanner = new Scanner(stream);
        System.out.println("Which cells are alive");
        int inputIndexOfRowFromUser = scanner.nextInt();
        int inputIndexOfColumnFromUser = scanner.nextInt();
        scanner.close();
        input = new UserInput(inputIndexOfRowFromUser, inputIndexOfColumnFromUser);
        return input;
    }

    public int getIndexOfRow() {
        return indexOfRow;
    }

    public int getIndexOfColumn() {
        return indexOfColumn;
    }
}
