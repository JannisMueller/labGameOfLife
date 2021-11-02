package se.mueller;

record userInputForStartPositions(int indexOfRow, int indexOfColumn) {

    public int indexOfRow() {
        return indexOfRow;
    }

    public int indexOfColumn() {
        return indexOfColumn;
    }
}
