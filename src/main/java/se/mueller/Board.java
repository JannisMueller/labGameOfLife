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
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (rand.nextInt(2) == 1) {
                    grid[row][col] = CELL_IS_ALIVE;
                }
            }
        }
    }

    public int[][] getGrid() {
        return grid;
    }

    public void printGrid() {
        for (int[] rows : grid) {
            for (int row : rows) {
                System.out.print(row);
            }
            System.out.println();
        }
        System.out.println();
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

    private int getPositionOfCell(Position position) {
        if(isInTheGrid(new Position(position.row(), position.column())))
            return grid[position.row()][position.column()];
        return 0;
    }

    public int findNumberOfAliveNeighbours(Position position) {
        return (int) findNeighbours(new Position(position.row(), position.column())).stream().filter(x -> x == CELL_IS_ALIVE).count();
    }

    public int[][] nextGeneration() {

        int[][] nextGeneration = new int[ROWS_GRID][COLUMNS_GRID];
        for (int i = 0; i < ROWS_GRID; i++)
            for (int j = 0; j < COLUMNS_GRID; j++) {
                int numberOfNeighbours = findNumberOfAliveNeighbours(new Position(i, j));

                if (grid[i][j] == CELL_IS_ALIVE && numberOfNeighbours < 2)
                    nextGeneration[i][j] = CELL_IS_DEAD;

                else if (grid[i][j] == CELL_IS_ALIVE && numberOfNeighbours > 3)
                    nextGeneration[i][j] = CELL_IS_DEAD;

                else if ((grid[i][j] == CELL_IS_ALIVE) && (numberOfNeighbours == 2 || numberOfNeighbours == 3))
                    nextGeneration[i][j] = CELL_IS_ALIVE;

                else if ((grid[i][j] == CELL_IS_DEAD) && (numberOfNeighbours == 3)) {
                    nextGeneration[i][j] = CELL_IS_ALIVE;
                }
            }
        return nextGeneration;
    }

    private boolean isInTheGrid(Position position){
            return position.row() >= 0 && position.column() >= 0 && position.row() < ROWS_GRID && position.column() < COLUMNS_GRID;
        }

   public List<int[]> findAllIndexOfCellsThatAreAlive() {
        List<int[]> listOfIndex = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++) {
                if (grid[i][j] == 1) {
                    listOfIndex.add(new int[]{i, j});
                }
            }
        return listOfIndex;
    }
    }

