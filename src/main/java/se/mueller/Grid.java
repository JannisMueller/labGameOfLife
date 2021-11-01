package se.mueller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Grid {

    private static final int ROWS_GRID = 10;
    private static final int COLUMNS_GRID = 10;

    private int[][] grid;

    public static final int CELL_IS_DEAD = 0;
    public static final int CELL_IS_ALIVE = 1;

    public Grid() {
        this.grid = initializeGridWithDeadCellsOnly();
    }

    public Grid(int[][] grid) {
        this.grid = grid;
    }

    public int[][] initializeGridWithDeadCellsOnly() {
        return new int[ROWS_GRID][COLUMNS_GRID];
    }


    public int[][] getGrid() {
        return grid;
    }

    public void printArray() {
        for (int[] rows : grid) {
            for (int row : rows) {
                System.out.print(row);
            }
            System.out.println();
        }
        System.out.println();
    }

    public void changeCellStatusToAlive(int row, int column) {
        grid[row][column] = CELL_IS_ALIVE;
    }


    public List<Integer> findNeighbours(int row, int column) {

        int overCell = row - 1;
        int underCell = row + 1;

        int leftFromCell = column - 1;
        int rightFromCell = column + 1;

        return Arrays.asList(
                getPositionOfCell(overCell, column),
                getPositionOfCell(overCell, leftFromCell),
                getPositionOfCell(overCell, rightFromCell),

                getPositionOfCell(underCell, column),
                getPositionOfCell(underCell, leftFromCell),
                getPositionOfCell(underCell, rightFromCell),

                getPositionOfCell(row, leftFromCell),
                getPositionOfCell(row, rightFromCell)
        );

    }

    private int getPositionOfCell(int row, int column) {
        if(isInTheGrid(row, column)) {
            int position = grid[row][column];
            return position;
        }
        return 99;
    }

    public int findNumberOfAliveNeighbours(int row, int column) {
        return (int) findNeighbours(row, column).stream().filter(x -> x == 1).count();
    }

    public int[][] nextGeneration() {
        int[][] nextGeneration = new int[ROWS_GRID][COLUMNS_GRID];
        for (int i = 0; i < ROWS_GRID; i++)
            for (int j = 0; j < COLUMNS_GRID; j++) {
                int numberOfNeighbours = findNumberOfAliveNeighbours(i, j);
                if ((grid[i][j] == CELL_IS_ALIVE) && (numberOfNeighbours < 2)) {
                    nextGeneration[i][j] = CELL_IS_DEAD;
                } else if ((grid[i][j] == CELL_IS_ALIVE) && (numberOfNeighbours > 3)) {
                    nextGeneration[i][j] = CELL_IS_DEAD;
                } else if ((grid[i][j] == CELL_IS_ALIVE) && (numberOfNeighbours == 2 || numberOfNeighbours == 3)) {
                    nextGeneration[i][j] = CELL_IS_ALIVE;
                }
                if ((grid[i][j] == CELL_IS_DEAD) && (numberOfNeighbours == 3)) {
                    nextGeneration[i][j] = CELL_IS_ALIVE;
                }

            }
        return nextGeneration;
    }

        private boolean isInTheGrid ( int row, int column){
            return row >= 0 && column >= 0 && row < ROWS_GRID && column < COLUMNS_GRID;
        }
    }

