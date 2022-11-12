package ru.vsu.cs.baturin_v_a;

import java.util.Scanner;

public class Main {

    static Scanner user_input = new Scanner(System.in);


    public static void main(String[] args) {
        Chessboard myChessboard = new Chessboard();


        while (myChessboard.getGameRunning()) {
            myChessboard.printBoard();
            myChessboard.move();
        }
    }
}
