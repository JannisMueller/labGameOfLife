package se.mueller

import spock.lang.Specification

class GameOfLifeTest extends Specification {

    static final int ROWS_GRID = 10;
    static final int COLUMNS_GRID = 10;


    def "Test if grid is initialized and has the expected length"() {
        when:
        Grid grid = new Grid()

        then:
        grid.getGrid().length == 10
    }

    def "Test if cells (dead or alive) have the right value based on their status"() {
        expect:
        statusOfCell == expectedIntegerValueOFCell

        where:
        statusOfCell| expectedIntegerValueOFCell
        Grid.CELL_IS_ALIVE || 1
        Grid.CELL_IS_DEAD  || 0

    }
    def "The first grid is just filled with Dead cells (with value 0 before some cells are set to Life by the user"() {
        when:
        Grid grid = new Grid()

        then:
        findAllIndexOfCellsThatAreAlive(grid.getGrid()).size() == 0
    }

    def "User input sets cells correctly to alive in Grid"(){
        when:
        Grid grid = new Grid()
        grid.changeCellStatusToAlive(activatedCells)

        then:
        findAllIndexOfCellsThatAreAlive(grid.getGrid()) == expectedIndexAfterActivation

        where:
        activatedCells      || expectedIndexAfterActivation
        new Position(1,1)   || [[1,1]]
        new Position(2,5)   || [[2,5]]
        new Position(9,9)   || [[9,9]]
    }

    def "Test to ensure that all activated cells are correctly identified"() {
        when:
        Grid grid = new Grid()
        setCellsAlive(grid, 3)
        def listOfIndex = findAllIndexOfCellsThatAreAlive(grid.getGrid())

        then:
        listOfIndex.size() == 3
        listOfIndex == [[0, 0], [1, 1], [2, 2]]
    }

    def "Identifying all possible neighbors of an activated cells"() {
        when:
        Grid grid = new Grid()
        Position position = new Position(1,1)
        grid.changeCellStatusToAlive(position)

        then:
        position.findNeighbours(new Position(5, 5)).size() == 8
    }
    def "Identifying all possible neighbors at edge of an activated cells"() {
        when:
        Grid grid = new Grid()

        grid.changeCellStatusToAlive(new Position(0, 3))
        grid.changeCellStatusToAlive(new Position(0, 2))
        grid.changeCellStatusToAlive(new Position(0, 4))
        grid.changeCellStatusToAlive(new Position(1, 2))
        grid.changeCellStatusToAlive(new Position(1, 3))
        grid.changeCellStatusToAlive(new Position(1, 4))

        then:
        grid.findNumberOfAliveNeighbours(new Position(0, 3))== 5
    }

    def "All alive neighbours are identified correctly"() {
        when:
        Grid grid = new Grid()
        grid.changeCellStatusToAlive(new Position(1, 1))
        grid.changeCellStatusToAlive(new Position(1, 2))

        then:
        grid.findNumberOfAliveNeighbours(new Position(1, 1)) == 1
    }

    def "Live cell that doesnt have neighbors gets killed"() {
        when:
        Grid grid = new Grid()
        grid.changeCellStatusToAlive(new Position(1, 1))
        Grid nextGeneration = grid.getNextGeneration()

        then:
        nextGeneration.getPositionOfCell(new Position(1, 1)) == 0
        nextGeneration.findNumberOfAliveNeighbours(new Position(1, 2)) == 0
        findAllIndexOfCellsThatAreAlive(nextGeneration.getGrid()).size() == 0
    }

    def "Live cell that have more than 3 neighbors gets killed"() {
        when:
        Grid grid = new Grid().initializeGridWithDeadCellsOnly()
        grid.changeCellStatusToAlive(new Position(1, 1))
        grid.changeCellStatusToAlive(new Position(1, 2))
        grid.changeCellStatusToAlive(new Position(0, 3))
        grid.changeCellStatusToAlive(new Position(0, 2))
        grid.changeCellStatusToAlive(new Position(0, 1))

        Grid nextGeneration = grid.getNextGeneration()

        then:

        nextGeneration.getPositionOfCell(new Position(1, 2)) == 0
        nextGeneration.getPositionOfCell(new Position(0, 2)) == 0
        nextGeneration.findNumberOfAliveNeighbours(new Position(1, 1)) == 1
        nextGeneration.findNumberOfAliveNeighbours(new Position(0, 1)) == 1
        findAllIndexOfCellsThatAreAlive(nextGeneration.getGrid()).size() == 4
    }

    def "Dead cells that have exactly 3 neighbors gets born"() {
        when:
        Grid grid = new Grid()
        grid.changeCellStatusToAlive(new Position(0, 3))
        grid.changeCellStatusToAlive(new Position(0, 2))
        grid.changeCellStatusToAlive(new Position(0, 1))

        Grid nextGeneration = grid.getNextGeneration()

        then:
        nextGeneration.getPositionOfCell(new Position(1, 2)) == 1
        nextGeneration.findNumberOfAliveNeighbours(new Position(1, 2)) == 1
        findAllIndexOfCellsThatAreAlive(nextGeneration.getGrid()).size() == 2
    }

    def "Any live cell with two or three live neighbors lives on to the next generation"() {
        when:
        Grid grid = new Grid()
        grid.changeCellStatusToAlive(new Position(1, 2))
        grid.changeCellStatusToAlive(new Position(1, 3))
        grid.changeCellStatusToAlive(new Position(1, 4))
        grid.changeCellStatusToAlive(new Position(1, 6))

        grid.changeCellStatusToAlive(new Position(2, 5))
        grid.changeCellStatusToAlive(new Position(2, 6))
        grid.changeCellStatusToAlive(new Position(2, 7))

        Grid nextGeneration = grid.getNextGeneration()

        then:
        nextGeneration.getPositionOfCell(new Position(1, 3)) == 1
        nextGeneration.getPositionOfCell(new Position(1, 6)) == 1
        nextGeneration.getPositionOfCell(new Position(2, 6)) == 1
        nextGeneration.findNumberOfAliveNeighbours(new Position(3, 3)) == 2
        nextGeneration.findNumberOfAliveNeighbours(new Position(0, 3)) == 2
        findAllIndexOfCellsThatAreAlive(nextGeneration.getGrid()).size() == 11
    }

    def "Board initializes randomly"() {
        when:
        Grid grid = new Grid()
        grid.initializeGridRandom()

        then:
        findAllIndexOfCellsThatAreAlive(grid.getGrid()).size() > 0
    }

    def "Next Generation will be calculated from previous generations"() {
        when:
        Grid grid = new Grid()
        grid.initializeGridRandom()

        then:
        grid != grid.insertNewGeneration(grid.getNextGeneration())
    }

    private void setCellsAlive(Grid grid, int amountOfCellsToSetAlive) {
        for (int i = 0; i < amountOfCellsToSetAlive; i++) {
            UserInput userInput = new UserInput(i, i)
            grid.changeCellStatusToAlive(new Position(userInput.indexOfRow(), userInput.indexOfColumn()))
        }
    }

    private List<int[]> findAllIndexOfCellsThatAreAlive(int [][] grid) {
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