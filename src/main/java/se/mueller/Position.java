package se.mueller;


import java.util.Arrays;
import java.util.List;

record Position (int row, int column){

    public List<Position> findNeighbours(Position position) {

        int overCell = position.row() - 1;
        int underCell = position.row() + 1;
        int leftFromCell = position.column() - 1;
        int rightFromCell = position.column() + 1;

        return Arrays.asList(
                new Position(overCell, position.column()),
                new Position(overCell, leftFromCell),
                new Position(overCell, rightFromCell),

                new Position(underCell, position.column()),
                new Position(underCell, leftFromCell),
                new Position(underCell, rightFromCell),

                new Position(position.row(), leftFromCell),
                new Position(position.row(), rightFromCell)
        );

    }


}
