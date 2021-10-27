package se.mueller;

public class Grid {

    private int rowsOfGrid;
    private int columnsOfGrid;
    int[][] grid;

    public Grid() {
        this.rowsOfGrid = 10;
        this.columnsOfGrid = 10;
        this.grid = new int[rowsOfGrid][columnsOfGrid];

    }

    public int[][] initializeGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int y = 0; y < grid[i].length; y++) {
                grid[i][y] = i * y;
            }
        }
        return grid;
    }

    public int[][] getGrid() {
        return grid;
    }
}


