package se.mueller;


import java.io.InputStream;
import java.util.Scanner;

public class GameOfLife {

    public static void main(String[] args) {


        Grid grid = new Grid();

        grid.initializeGridWithDeadCellsOnly();
        UserInput input = new UserInput().inputStream();
        grid.changeCellStatusToAlive(input.getIndexOfRow(), input.getIndexOfColumn());
        grid.printArray();


    }




}



