package se.mueller

import spock.lang.Specification

class GameOfLifeTest extends Specification {


    def "Test if grid is initialized and has the expected length"() {
        when:
        Board grid = new Board()
        then:
        grid.getGrid().length == 10
    }

    def "Test if cells (dead or alive) have the right value based on their status"() {
        expect:
        statusOfCell == expectedIntegerValueOFCell
        where:
        statusOfCell| expectedIntegerValueOFCell
        Board.CELL_IS_ALIVE || 1
        Board.CELL_IS_DEAD  || 0

    }
    def "The first grid is just filled with Dead cells (with value 0 before some cells are set to Life by the user"() {
        when:
        Board grid = new Board()
        then:
        grid.findAllIndexOfCellsThatAreAlive().size() == 0
    }

    def "User input sets cells correctly to alive in Grid"(){
        when:
        Board grid = new Board()
        grid.changeCellStatusToAlive(activatedCells)
        then:
        grid.findAllIndexOfCellsThatAreAlive() == expectedIndexAfterActivation
        where:
        activatedCells      || expectedIndexAfterActivation
        new Position(1,1)   || [[1,1]]
        new Position(2,5)   || [[2,5]]
        new Position(9,9)   || [[9,9]]
    }

    def "Test to ensure that all activated cells are correctly identified"() {
        when:
        Board grid = new Board()
        setCellsAlive(grid, 3)
        def listOfIndex = grid.findAllIndexOfCellsThatAreAlive()
        then:

        listOfIndex.size() == 3
        listOfIndex == [[0, 0], [1, 1], [2, 2]]
    }

    def "Identifying all possible neighbors of an activated cells"() {
        when:
        Board grid = new Board()
        grid.changeCellStatusToAlive(new Position(1, 1))
        then:
        grid.findNeighbours(new Position(5, 5)).size() == 8
    }
    def "Identifying all possible neighbors at edge of an activated cells"() {
        when:
        Board grid = new Board()
        grid.changeCellStatusToAlive(new Position(0, 3))

        grid.changeCellStatusToAlive(new Position(0, 2))
        grid.changeCellStatusToAlive(new Position(0, 4))
        grid.changeCellStatusToAlive(new Position(1, 2))
        grid.changeCellStatusToAlive(new Position(1, 3))
        grid.changeCellStatusToAlive(new Position(1, 4))

        then:
        grid.findNumberOfAliveNeighbours(new Position(0, 3))== 5
    }


    private void setCellsAlive(Board grid, int amountOfCellsToSetAlive) {
        for (int i = 0; i < amountOfCellsToSetAlive; i++) {
            UserInput userInput = new UserInput(i, i)
            grid.changeCellStatusToAlive(new Position(userInput.indexOfRow(), userInput.indexOfColumn()))
        }
    }

    def "All alive neighbours are identified correctly"() {
        when:
        Board grid = new Board()
        grid.changeCellStatusToAlive(new Position(1, 1))
        grid.changeCellStatusToAlive(new Position(1, 2))

        then:
        grid.findNumberOfAliveNeighbours(new Position(1, 1)) == 1
    }



    def "Live cell that doesnt have neighbors gets killed"() {
        when:
        Board grid = new Board()
        grid.changeCellStatusToAlive(new Position(1, 1))
        grid.printGrid()
        Board nextGeneration = grid.getNextGeneration()
        nextGeneration.printGrid()
        then:
        nextGeneration.findNumberOfAliveNeighbours(new Position(1, 2)) == 0
        nextGeneration.findAllIndexOfCellsThatAreAlive().size() == 0
    }

    def "Live cell that have more than 3 neighbors gets killed"() {
        when:
        Board grid = new Board().initializeGridWithDeadCellsOnly()
        grid.changeCellStatusToAlive(new Position(1, 1))
        grid.changeCellStatusToAlive(new Position(1, 2))
        grid.changeCellStatusToAlive(new Position(0, 3))
        grid.changeCellStatusToAlive(new Position(0, 2))
        grid.changeCellStatusToAlive(new Position(0, 1))

        grid.printGrid()
        Board nextGeneration = grid.getNextGeneration()
        nextGeneration.printGrid()
        then:
        nextGeneration.findNumberOfAliveNeighbours(new Position(1, 1)) == 1
        nextGeneration.findNumberOfAliveNeighbours(new Position(0, 1)) == 1
        nextGeneration.findAllIndexOfCellsThatAreAlive().size() == 4
    }

    def "Dead cells that have exactly 3 neighbors gets born"() {
        when:
        Board grid = new Board()
        grid.changeCellStatusToAlive(new Position(0, 3))
        grid.changeCellStatusToAlive(new Position(0, 2))
        grid.changeCellStatusToAlive(new Position(0, 1))
        grid.printGrid()
        Board nextGeneration = grid.getNextGeneration()
        nextGeneration.printGrid()
        then:
        nextGeneration.findNumberOfAliveNeighbours(new Position(1, 2)) == 1
        nextGeneration.findAllIndexOfCellsThatAreAlive().size() == 2
    }

    def "Any live cell with two or three live neighbors lives on to the next generation"() {
        when:
        Board grid = new Board()
        grid.changeCellStatusToAlive(new Position(1, 2))
        grid.changeCellStatusToAlive(new Position(1, 3))
        grid.changeCellStatusToAlive(new Position(1, 4))

        grid.printGrid()
        Board nextGeneration = grid.getNextGeneration()
        nextGeneration.printGrid()
        then:
        nextGeneration.findNumberOfAliveNeighbours(new Position(3, 3)) == 1
        nextGeneration.findNumberOfAliveNeighbours(new Position(0, 3)) == 1
        nextGeneration.findAllIndexOfCellsThatAreAlive().size() == 3
    }

    def "Board initializes randomly"() {
        when:
        Board grid = new Board()
        grid.initializeGridRandom()
        grid.printGrid()
        then:
        grid.findAllIndexOfCellsThatAreAlive().size() > 0
    }

    def "Next Generation will be calculated from previous generations"() {
        when:
        Board grid = new Board()
        grid.initializeGridRandom()
        then:
        grid != grid.insertNewGeneration(grid.getNextGeneration())
    }
}