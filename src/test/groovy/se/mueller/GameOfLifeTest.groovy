package se.mueller

import spock.lang.Specification

class GameOfLifeTest extends Specification {
    def "Test if grid is initialized and has the expected rows and columns"() {
        given:
        final int rowsOfGrid = 10;
        final int columnsOfGrid = 10;
        Grid grid = new Grid(rowsOfGrid, columnsOfGrid)
        when:
        grid.initializeGrid()
        then:
        grid.getColumnsOfGrid() == 10;
        grid.getRowsOfGrid() == 10;

    }
}
