package se.mueller;

import java.util.Random;

public class Grid {

    private static final int ROWS_GRID = 10;
    private static final int COLUMNS_GRID = 10;

    private final int[][] grid;

    private static final int CELL_IS_DEAD = 0;
    private static final int CELL_IS_ALIVE = 1;

    private final Random rand = new Random();

    public Grid() {
        this.grid = initializeGridWithDeadCellsOnly();
    }

    public Grid(int[][] grid) {
        this.grid = grid;
    }

    public int[][] initializeGridWithDeadCellsOnly() {
        return new int[ROWS_GRID][COLUMNS_GRID];
    }

    public void initializeGridRandom(){
        for (int indexRow = 0; indexRow < grid.length; indexRow++) {
            for (int indexColumn = 0; indexColumn < grid[indexRow].length; indexColumn++) {
                if (rand.nextInt(2) == 1) {
                    grid[indexRow][indexColumn] = CELL_IS_ALIVE;
                }
            }
        }
    }

    public int[][] getGrid() {
        return grid;
    }

    public void changeCellStatusToAlive(Position position) {
        grid[position.row()][position.column()] = CELL_IS_ALIVE;
    }

    private int getPositionOfCell(Position position) {
        if(isInTheGrid(new Position(position.row(), position.column())))
            return grid[position.row()][position.column()];
        return 0;
    }

    public boolean isInTheGrid(Position position){
        return position.row() >= 0 && position.column() >= 0 && position.row() < ROWS_GRID && position.column() < COLUMNS_GRID;
    }

    public int findNumberOfAliveNeighbours(Position position) {
        return (int) position.findNeighbours(new Position(position.row(), position.column())).stream().map(this::getPositionOfCell).filter( x -> x == CELL_IS_ALIVE).count();
    }

    public int[][] getNextGeneration() {
        int[][] nextGeneration = new int[ROWS_GRID][COLUMNS_GRID];

        for (int indexRow = 0; indexRow < ROWS_GRID; indexRow++)
            for (int indexColumn = 0; indexColumn < COLUMNS_GRID; indexColumn++) {
                int numberOfNeighbours = findNumberOfAliveNeighbours(new Position(indexRow, indexColumn));
                int positionInCurrentGeneration = grid[indexRow][indexColumn];

                if (positionInCurrentGeneration == CELL_IS_ALIVE && numberOfNeighbours < 2 || numberOfNeighbours > 3)
                    nextGeneration[indexRow][indexColumn] = CELL_IS_DEAD;

                if ((positionInCurrentGeneration == CELL_IS_DEAD) && (numberOfNeighbours == 3) || (positionInCurrentGeneration == CELL_IS_ALIVE) && (numberOfNeighbours == 2 || numberOfNeighbours == 3)) {
                    nextGeneration[indexRow][indexColumn] = CELL_IS_ALIVE;}
            }
        return nextGeneration;
    }

    public void insertNewGeneration(int[][] nextGeneration){
        for (int indexRow = 0; indexRow < ROWS_GRID; indexRow++)
            System.arraycopy(nextGeneration[indexRow], 0, grid[indexRow], 0, COLUMNS_GRID);
    }

    }
