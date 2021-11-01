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
        findAllIndexOfCellsThatAreAlive(grid).size() == 0
    }

    def "User input sets cells correctly to alive in Grid"(){
        when:
        Grid grid = new Grid()
        grid.changeCellStatusToAlive(activatedCells)
        then:
        findAllIndexOfCellsThatAreAlive(grid) == expectedIndexAfterActivation
        where:
        activatedCells || expectedIndexAfterActivation
        [1,1]          || [[1,1]]
        [2,5]          || [[2,5]]
        [9,9]          || [[9,9]]
    }

    def "Test to ensure that all activated cells are correctly identified"() {
        when:
        Grid grid = new Grid()
        setCellsAlive(grid, 3)
        def listOfIndex = findAllIndexOfCellsThatAreAlive(grid)
        then:

        listOfIndex.size() == 3
        listOfIndex == [[0, 0], [1, 1], [2, 2]]

    }



    def "Identifying all possible neighbors of an activated cells"() {
        when:
        Grid grid = new Grid()
        grid.changeCellStatusToAlive(1,1)
        then:
        grid.findNeighbours(5,5).size() == 8
    }
    def "Identifying all possible neighbors at edge of an activated cells"() {
        when:
        Grid grid = new Grid()
        grid.changeCellStatusToAlive(0,3)

        grid.changeCellStatusToAlive(0,2)
        grid.changeCellStatusToAlive(0,4)
        grid.changeCellStatusToAlive(1,2)
        grid.changeCellStatusToAlive(1,3)
        grid.changeCellStatusToAlive(1,4)

        then:
        grid.findNumberOfAliveNeighbours(0,3)== 5
    }


    private void setCellsAlive(Grid grid,int amountOfCellsToSetAlive) {
        for (int i = 0; i < amountOfCellsToSetAlive; i++) {
            UserInput userInput = new UserInput(i, i)
            grid.changeCellStatusToAlive(userInput.getIndexOfRow(), userInput.getIndexOfColumn())
        }
    }

    def "All alive neighbours are identified correctly"() {

        when:
        Grid grid = new Grid()
        grid.changeCellStatusToAlive(1,1)
        grid.changeCellStatusToAlive(1,2)

        then:
        grid.findNumberOfAliveNeighbours(1,1) == 1
    }



    def "Live cell that doesnt have neighbors gets killed"() {
        when:
        Grid grid = new Grid()
        grid.changeCellStatusToAlive(1,1)
        grid.printArray()
        Grid nextGeneration = grid.nextGeneration()
        nextGeneration.printArray()
        then:
        nextGeneration.findNumberOfAliveNeighbours(1,2) == 0
        findAllIndexOfCellsThatAreAlive(nextGeneration).size() == 0

    }

    def "Live cell that have more than 3 neighbors gets killed"() {
        when:
        Grid grid = new Grid().initializeGridWithDeadCellsOnly()
        grid.changeCellStatusToAlive(1,1)
        grid.changeCellStatusToAlive(1,2)
        grid.changeCellStatusToAlive(0,3)
        grid.changeCellStatusToAlive(0,2)
        grid.changeCellStatusToAlive(0,1)

        grid.printArray()
        Grid nextGeneration = grid.nextGeneration()
        nextGeneration.printArray()
        then:
        nextGeneration.findNumberOfAliveNeighbours(1,1) == 1
        nextGeneration.findNumberOfAliveNeighbours(0,1) == 1
        findAllIndexOfCellsThatAreAlive(nextGeneration).size() == 4

    }

    def "Dead cells that have exactly 3 neighbors gets born"() {
        when:
        Grid grid = new Grid()
        //Neighbors
        grid.changeCellStatusToAlive(0,3)
        grid.changeCellStatusToAlive(0,2)
        grid.changeCellStatusToAlive(0,1)
        grid.printArray()
        Grid nextGeneration = grid.nextGeneration()
        nextGeneration.printArray()
        then:
        nextGeneration.findNumberOfAliveNeighbours(1,2) == 1
        findAllIndexOfCellsThatAreAlive(nextGeneration).size() == 2


    }

    def "Any live cell with two or three live neighbors lives on to the next generation"() {
        when:
        Grid grid = new Grid()
        grid.changeCellStatusToAlive(1,2)
        grid.changeCellStatusToAlive(1,3)
        grid.changeCellStatusToAlive(1,4)

        grid.printArray()
        Grid nextGeneration = grid.nextGeneration()
        nextGeneration.printArray()
        then:
        nextGeneration.findNumberOfAliveNeighbours(3,3) == 1
        nextGeneration.findNumberOfAliveNeighbours(0,3) == 1
        findAllIndexOfCellsThatAreAlive(nextGeneration).size() == 3


    }

    static List<int[]> findAllIndexOfCellsThatAreAlive(Grid grid) {
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