package se.mueller;

record UserInput(int indexOfRow, int indexOfColumn) {

    public int indexOfRow() {
        return indexOfRow;
    }

    public int indexOfColumn() {
        return indexOfColumn;
    }
}
