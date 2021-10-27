package se.mueller;

import java.util.Arrays;

public class GameOfLife {

    public static void main(String[] args) {

        Grid grid = new Grid();
        grid.initializeGridWithDeadCellsOnly();
        grid.printArray();

    }
}



