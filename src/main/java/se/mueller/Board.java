package se.mueller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Board {

    private static final int ROWS_GRID = 10;
    private static final int COLUMNS_GRID = 10;

    private final int[][] grid;

    public static final int CELL_IS_DEAD = 0;
    public static final int CELL_IS_ALIVE = 1;

    Random rand = new Random();

    public Board() {
        this.grid = initializeGridWithDeadCellsOnly();
    }

    public Board(int[][] grid) {
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

    public void printGrid() {
        for (int indexRow = 0; indexRow < ROWS_GRID; indexRow++) {
            for (int indexColumn = 0; indexColumn < COLUMNS_GRID; indexColumn++) {
                if (grid[indexRow][indexColumn] == 0)
                    System.out.print(".");
                else
                    System.out.print("*");
            }
            System.out.println();
        }
    }

    public void changeCellStatusToAlive(Position position) {
        grid[position.row()][position.column()] = CELL_IS_ALIVE;
    }

    public List<Integer> findNeighbours(Position position) {

        int overCell = position.row() - 1;
        int underCell = position.row() + 1;

        int leftFromCell = position.column() - 1;
        int rightFromCell = position.column() + 1;

        return Arrays.asList(
                getPositionOfCell(new Position(overCell, position.column())),
                getPositionOfCell(new Position(overCell, leftFromCell)),
                getPositionOfCell(new Position(overCell, rightFromCell)),

                getPositionOfCell(new Position(underCell, position.column())),
                getPositionOfCell(new Position(underCell, leftFromCell)),
                getPositionOfCell(new Position(underCell, rightFromCell)),

                getPositionOfCell(new Position(position.row(), leftFromCell)),
                getPositionOfCell(new Position(position.row(), rightFromCell))
        );

    }

    public int getPositionOfCell(Position position) {
        if(isInTheGrid(new Position(position.row(), position.column())))
            return grid[position.row()][position.column()];
        return 0;
    }

    public int findNumberOfAliveNeighbours(Position position) {
        return (int) findNeighbours(new Position(position.row(), position.column())).stream().filter(x -> x == CELL_IS_ALIVE).count();
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
            for (int indexColumn = 0; indexColumn < COLUMNS_GRID; indexColumn++) {
                grid[indexRow][indexColumn]= nextGeneration[indexRow][indexColumn];
            }
    }

    private boolean isInTheGrid(Position position){
            return position.row() >= 0 && position.column() >= 0 && position.row() < ROWS_GRID && position.column() < COLUMNS_GRID;
        }

    public List<int[]> findAllIndexOfCellsThatAreAlive() {
        List<int[]> listOfIndex = new ArrayList<>();
        for (int indexRow = 0; indexRow < ROWS_GRID; indexRow++)
            for (int indexColumn = 0; indexColumn < COLUMNS_GRID; indexColumn++) {
                if (grid[indexRow][indexColumn] == 1) {
                    listOfIndex.add(new int[]{indexRow, indexColumn});
                }
            }
        return listOfIndex;
    }
    }
