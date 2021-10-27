package se.mueller

import spock.lang.Specification

import java.util.function.Function
import java.util.stream.IntStream


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
    def "The first grid is just filled with Dead cells before some cells are set to Life by the user"() {
        when:
        Grid grid = new Grid()
        grid.initializeGridWithDeadCellsOnly()
        int sumOfValueOfCells = grid.sum()

        then:
        sumOfValueOfCells == 0;
    }

}