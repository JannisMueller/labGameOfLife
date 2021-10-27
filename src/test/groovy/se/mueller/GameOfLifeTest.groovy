package se.mueller

import spock.lang.Specification

import javax.swing.JLabel

class GameOfLifeTest extends Specification {
    def "Test if grid is initialized and has the expected length"() {
        when:
        Grid grid = new Grid()
        grid.initializeGrid()
        then:
        grid.getGrid().length == 10
    }

    def "Test state of cells (dead or alive)"() {
        when: StatusOfCell statusCell = aliveOrDead
        then:statusCell.label == expectedLabel
        where:
        aliveOrDead | expectedLabel
        StatusOfCell.ALIVE || "1"
        StatusOfCell.DEAD  || "0"


    }
}
