package se.mueller;

import java.util.Scanner;


public class GameOfLife {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        PlayGameOfLife.playGameOfLife(SCANNER);
    }
}
