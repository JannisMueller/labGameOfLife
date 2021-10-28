package se.mueller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Grid {

    private final int rowsOfGrid = 10;
    private final int columnsOfGrid = 10;
    private int[][] grid;
    public final static int CELL_IS_DEAD = 0;
    public final static int CELL_IS_ALIVE = 1;

    public Grid() {
        this.grid = initializeGridWithDeadCellsOnly();
    }

    public int[][] initializeGridWithDeadCellsOnly() {
            return new int [rowsOfGrid][columnsOfGrid];
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


    List<int[]>findAllIndexOfCellsThatAreAlive() {
        List<int[]> listOfIndex = new ArrayList<>();
        for (int i = 0; i < rowsOfGrid; i++)
            for (int j = 0; j < columnsOfGrid; j++) {
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
    }

    public void changeCellStatusToAlive(int row, int column) {
        grid[row][column] = CELL_IS_ALIVE;
    }

}


