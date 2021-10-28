package se.mueller;
import java.io.InputStream;
import java.util.Scanner;

class UserInput {

    private int indexOfRow;
    private int indexOfColumn;


    public UserInput(int indexOfRow, int indexOfColumn) {
        this.indexOfRow = indexOfRow;
        this.indexOfColumn = indexOfColumn;
    }

    public int getIndexOfRow() {
        return indexOfRow;
    }

    public int getIndexOfColumn() {
        return indexOfColumn;
    }
}
