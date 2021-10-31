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

    public int[][] initializeGridWithDeadCellsOnly() {
            return new int [ROWS_GRID][COLUMNS_GRID];
    }


    public int[][] getGrid() {
        return grid;
    }

    public int sum(){
         int sum = Arrays.stream(grid)
                 .flatMapToInt(Arrays::stream)
                 .sum();
         return sum;
    }

    public List<int[]>findAllIndexOfCellsThatAreAlive() {
        List<int[]> listOfIndex = new ArrayList<>();
        for (int i = 0; i < ROWS_GRID; i++)
            for (int j = 0; j < COLUMNS_GRID; j++) {
                if (grid[i][j] == 1) {
                    listOfIndex.add(new int[]{i, j});
                }
            }
        return listOfIndex;
    }

    public void printArray(){
        for(int[] rows : grid){
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
    public void changeCellStatusToDead(int row, int column) {
        grid[row][column] = CELL_IS_DEAD;
    }

    public List<Integer> findNeighbours(int row, int column) {

        int overCell = row -1;
        int underCell = row + 1;

        int leftFromCell = column -1;
        int rightFromCell = column +1;

        return Arrays.asList(
                getPositionOfCell(overCell,column),
                getPositionOfCell(overCell,leftFromCell),
                getPositionOfCell(overCell,rightFromCell),

                getPositionOfCell(underCell,column),
                getPositionOfCell(underCell,leftFromCell),
                getPositionOfCell(underCell,rightFromCell),

                getPositionOfCell(row,leftFromCell),
                getPositionOfCell(row,rightFromCell)
        );

    }

    private int getPositionOfCell(int index, int index1) {
        int position  = grid[index][index1];
        return position;
    }

    public int findNumberOfAliveNeighbours(int row, int column) {
        return (int) findNeighbours(row,column).stream().filter(x -> x == 1).count();
    }

    public int[][] nextGeneration () {
        int[][] nextGeneration = new int[ROWS_GRID][COLUMNS_GRID];
        for (int i = 1; i < ROWS_GRID - 1; i++)
            for (int j = 1; j < COLUMNS_GRID - 1; j++) {
                if (grid[i][j] == 1) {
                    if (findNumberOfAliveNeighbours(i, j) == 0 || findNumberOfAliveNeighbours(i, j) > 3)
                        changeCellStatusToDead(i, j);

                } else {
                    if (findNumberOfAliveNeighbours(i, j) == 3) {
                        changeCellStatusToAlive(i, j);
                        i++;j++;
                    }
                }



            }
        return nextGeneration;

    }

}


