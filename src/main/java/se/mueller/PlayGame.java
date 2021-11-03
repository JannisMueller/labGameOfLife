package se.mueller;

import java.util.Scanner;
import static se.mueller.GameOfLife.playGameOfLife;


public class PlayGame {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        playGameOfLife(SCANNER);
    }
}
