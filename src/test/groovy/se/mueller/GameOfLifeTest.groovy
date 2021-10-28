package se.mueller

import spock.lang.Specification

class GameOfLifeTest extends Specification {


    def "Test if grid is initialized and has the expected length"() {
        when:
        Grid grid = new Grid()
        grid.initializeGridWithDeadCellsOnly()
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
        grid.initializeGridWithDeadCellsOnly()
        then:
        grid.sum()== 0;
    }

    def "User input sets cells correctly to alive in Grid"(){
        given:
        Grid grid = new Grid()
        grid.initializeGridWithDeadCellsOnly()
        when:
        grid.changeCellStatusToAlive(activatedCells)
        then:
        grid.findIndex(1) == expectedIndexAfterActivation
        where:
        activatedCells || expectedIndexAfterActivation
        [1,1]          || [1,1]
        [2,5]          || [2,5]
        [9,9]          || [9,9]
    }

    def "Test if user input saves correctly in object and set cells correctly to alive"() {
        given:
        Grid grid = new Grid()
        grid.initializeGridWithDeadCellsOnly()
        UserInput input = new UserInput(2,5);
        when:
        grid.changeCellStatusToAlive(input.getIndexOfRow(), input.getIndexOfColumn());
        then:
        grid.findIndex(1) == [2,5]

    }
}