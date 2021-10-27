package se.mueller;

public class GameOfLife {

    public static void main(String[] args) {

        Grid grid = new Grid();
        grid.initializeGridWithDeadCellsOnly();
        grid.changeCellStautsToAlive(9,9);
        grid.changeCellStautsToAlive(1,1);
        grid.printArray();

    }
}



