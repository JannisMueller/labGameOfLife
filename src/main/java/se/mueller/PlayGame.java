package se.mueller;

import java.util.Scanner;

public class PlayGame {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        GameOfLife.playGameOfLife(SCANNER);
    }
}
