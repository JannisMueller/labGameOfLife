package se.mueller;

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
