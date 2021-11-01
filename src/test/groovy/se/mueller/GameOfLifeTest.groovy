package se.mueller

import spock.lang.Specification

class GameOfLifeTest extends Specification {


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
        grid.findAllIndexOfCellsThatAreAlive().size() == 0
    }

    def "User input sets cells correctly to alive in Grid"(){
        when:
        Grid grid = new Grid()
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
        Grid grid = new Grid()
        setCellsAlive(grid, 3)
        def listOfIndex = grid.findAllIndexOfCellsThatAreAlive()
        then:

        listOfIndex.size() == 3
        listOfIndex == [[0, 0], [1, 1], [2, 2]]

    }



    def "Identifying all possible neighbors of an activated cells"() {
        when:
        Grid grid = new Grid()
        grid.changeCellStatusToAlive(new se.mueller.Position(1, 1))
        then:
        grid.findNeighbours(new se.mueller.Position(5, 5)).size() == 8
    }
    def "Identifying all possible neighbors at edge of an activated cells"() {
        when:
        Grid grid = new Grid()
        grid.changeCellStatusToAlive(new se.mueller.Position(0, 3))

        grid.changeCellStatusToAlive(new se.mueller.Position(0, 2))
        grid.changeCellStatusToAlive(new se.mueller.Position(0, 4))
        grid.changeCellStatusToAlive(new se.mueller.Position(1, 2))
        grid.changeCellStatusToAlive(new se.mueller.Position(1, 3))
        grid.changeCellStatusToAlive(new se.mueller.Position(1, 4))

        then:
        grid.findNumberOfAliveNeighbours(new se.mueller.Position(0, 3))== 5
    }


    private void setCellsAlive(Grid grid,int amountOfCellsToSetAlive) {
        for (int i = 0; i < amountOfCellsToSetAlive; i++) {
            UserInput userInput = new UserInput(i, i)
            grid.changeCellStatusToAlive(new se.mueller.Position(userInput.getIndexOfRow(), userInput.getIndexOfColumn()))
        }
    }

    def "All alive neighbours are identified correctly"() {

        when:
        Grid grid = new Grid()
        grid.changeCellStatusToAlive(new se.mueller.Position(1, 1))
        grid.changeCellStatusToAlive(new se.mueller.Position(1, 2))

        then:
        grid.findNumberOfAliveNeighbours(new se.mueller.Position(1, 1)) == 1
    }



    def "Live cell that doesnt have neighbors gets killed"() {
        when:
        Grid grid = new Grid()
        grid.changeCellStatusToAlive(new se.mueller.Position(1, 1))
        grid.printArray()
        Grid nextGeneration = grid.nextGeneration()
        nextGeneration.printArray()
        then:
        nextGeneration.findNumberOfAliveNeighbours(new se.mueller.Position(1, 2)) == 0
        nextGeneration.findAllIndexOfCellsThatAreAlive().size() == 0

    }

    def "Live cell that have more than 3 neighbors gets killed"() {
        when:
        Grid grid = new Grid().initializeGridWithDeadCellsOnly()
        grid.changeCellStatusToAlive(new se.mueller.Position(1, 1))
        grid.changeCellStatusToAlive(new se.mueller.Position(1, 2))
        grid.changeCellStatusToAlive(new se.mueller.Position(0, 3))
        grid.changeCellStatusToAlive(new se.mueller.Position(0, 2))
        grid.changeCellStatusToAlive(new se.mueller.Position(0, 1))

        grid.printArray()
        Grid nextGeneration = grid.nextGeneration()
        nextGeneration.printArray()
        then:
        nextGeneration.findNumberOfAliveNeighbours(new se.mueller.Position(1, 1)) == 1
        nextGeneration.findNumberOfAliveNeighbours(new se.mueller.Position(0, 1)) == 1
        nextGeneration.findAllIndexOfCellsThatAreAlive().size() == 4

    }

    def "Dead cells that have exactly 3 neighbors gets born"() {
        when:
        Grid grid = new Grid()
        //Neighbors
        grid.changeCellStatusToAlive(new se.mueller.Position(0, 3))
        grid.changeCellStatusToAlive(new se.mueller.Position(0, 2))
        grid.changeCellStatusToAlive(new se.mueller.Position(0, 1))
        grid.printArray()
        Grid nextGeneration = grid.nextGeneration()
        nextGeneration.printArray()
        then:
        nextGeneration.findNumberOfAliveNeighbours(new se.mueller.Position(1, 2)) == 1
        nextGeneration.findAllIndexOfCellsThatAreAlive().size() == 2


    }

    def "Any live cell with two or three live neighbors lives on to the next generation"() {
        when:
        Grid grid = new Grid()
        grid.changeCellStatusToAlive(new se.mueller.Position(1, 2))
        grid.changeCellStatusToAlive(new se.mueller.Position(1, 3))
        grid.changeCellStatusToAlive(new se.mueller.Position(1, 4))

        grid.printArray()
        Grid nextGeneration = grid.nextGeneration()
        nextGeneration.printArray()
        then:
        nextGeneration.findNumberOfAliveNeighbours(new se.mueller.Position(3, 3)) == 1
        nextGeneration.findNumberOfAliveNeighbours(new se.mueller.Position(0, 3)) == 1
        nextGeneration.findAllIndexOfCellsThatAreAlive().size() == 3


    }


}