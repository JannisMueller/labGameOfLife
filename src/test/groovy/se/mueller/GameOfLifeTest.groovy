package se.mueller

import spock.lang.Specification

class GameOfLifeTest extends Specification {
    def "Test if grid is initialized and has the expected length"() {
        when:
        Grid grid = new Grid()
        grid.initializeGrid()
        then:
        grid.getGrid().length == 10


    }
}
