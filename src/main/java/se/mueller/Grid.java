package se.mueller;

import java.util.Arrays;

public class Grid {

    private final int rowsOfGrid = 10;
    private final int columnsOfGrid = 10;
    private int[][] grid;
    public final static int CELL_IS_DEAD = 0;
    public final static int CELL_IS_ALIVE = 1;

    public Grid() {
        this.grid = new int[rowsOfGrid][columnsOfGrid];
    }

    public void initializeGridWithDeadCellsOnly() {
            for (int y = 0; y < rowsOfGrid; y++) {
                Arrays.fill(grid[y],CELL_IS_DEAD);
        }
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

    public void printArray(){
        System.out.println(Arrays.deepToString(grid));
    }

}


