package se.mueller;

public class Grid {

    private int rowsOfGrid = 10;
    private int columnsOfGrid = 10;
    int[][] grid;


    public Grid() {
        this.grid = new int[rowsOfGrid][columnsOfGrid];
    }

    public int[][] initializeGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int y = 0; y < grid[i].length; y++) {
                System.out.print("0");
            }
            System.out.println();
        }
        return grid;
    }

    public int[][] getGrid() {
        return grid;
    }
}


