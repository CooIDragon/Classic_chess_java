package ru.vsu.cs.baturin_v_a;

import java.util.Scanner;

public class Chessboard {

    private Boolean gameRunning;
    private final AbstractPiece[][] chessboard = new AbstractPiece[numOfRowsAndCols][numOfRowsAndCols];
    Scanner user_input = new Scanner(System.in);
    private static final int numOfRowsAndCols = 8;
    private static int srcRow, srcCol, destRow, destCol;
    private static Boolean whitesTurnToMove;
    private static Boolean invalidMove = false;
    private static King wKing = new King(true);
    private static King bKing = new King(false);
    String move;

    public Chessboard() {
        initialiseBoard(chessboard);
        gameRunning = true;
    }

    public Boolean getGameRunning() {
        return this.gameRunning;
    }

    private static void initialiseBoard(AbstractPiece[][] chessboard) {
        for (int row = 0; row < chessboard.length; row++) {
            for (int col = 0; col < chessboard[row].length; col++) {
                if (row == 0) {
                    switch (col) {
                        case 0, 7 -> chessboard[row][col] = new Rook(false);
                        case 1, 6 -> chessboard[row][col] = new Knight(false);
                        case 2, 5 -> chessboard[row][col] = new Bishop(false);
                        case 3 -> chessboard[row][col] = new Queen(false);
                        case 4 -> chessboard[row][col] = bKing;
                    }
                } else if (row == 1) {
                    chessboard[row][col] = new Pawn(false);
                } else if (row == 6) {
                    chessboard[row][col] = new Pawn(true);
                } else if (row == 7) {
                    switch (col) {
                        case 0, 7 -> chessboard[row][col] = new Rook(true);
                        case 1, 6 -> chessboard[row][col] = new Knight(true);
                        case 2, 5 -> chessboard[row][col] = new Bishop(true);
                        case 3 -> chessboard[row][col] = new Queen(true);
                        case 4 -> chessboard[row][col] = wKing;
                    }
                } else {
                    chessboard[row][col] = null;
                }
            }
        }

        whitesTurnToMove = true;

    }

    public void printBoard() {
        System.out.println("\ta\tb\tc\td\te\tf\tg\th");
        for (int row = 0; row < chessboard.length; row++) {
            System.out.print(8 - row + ".\t");
            for (int col = 0; col < chessboard[row].length; col++) {
                if (chessboard[row][col] != null) {
                    chessboard[row][col].draw();
                    System.out.print("\t");
                } else {
                    System.out.print("\t");
                }
            }
            System.out.println();
        }
    }

    private boolean moveValid() {
        if (srcRow < 0 || srcRow > 7 || srcCol < 0 || srcCol > 7 || destRow < 0
                || destRow > 7 || destCol < 0 || destCol > 7) {
            System.out.println("Move is outside the board");
            return false;
        }

        if (chessboard[srcRow][srcCol] == null) {
            System.err.println("Origin is empty");
            return false;
        }

        if ((chessboard[srcRow][srcCol].isWhite && !whitesTurnToMove)
                || (!chessboard[srcRow][srcCol].isWhite && whitesTurnToMove)) {
            System.err.println("It's not your turn");
            return false;
        }

        if (!chessboard[srcRow][srcCol].isMoveValid(srcRow, srcCol, destRow,
                destCol)) {
            System.err.println("This piece doesn't move like that");
            return false;
        }

        if (chessboard[destRow][destCol] == null) {
            return true;
        }

        if (chessboard[srcRow][srcCol].isWhite
                && chessboard[destRow][destCol].isWhite) {
            System.err.println("White cannot land on white");
            return false;
        }

        if (!chessboard[srcRow][srcCol].isWhite
                && !chessboard[destRow][destCol].isWhite) {
            System.err.println("Black cannot land on black");
            return false;
        }

        return true;
    }

    private void updateScore() {
        if (chessboard[destRow][destCol] == null) {
            return;
        } else if (chessboard[destRow][destCol].equals(bKing)) {
            gameRunning = false;
            System.out.println("White wins!!! Thanks for playing.");
        } else if (chessboard[destRow][destCol].equals(wKing)) {
            gameRunning = false;
            System.out.println("Black wins!!! Thanks for playing.");
        }
    }

    public void move() {
        if (invalidMove) {
            System.err.println("Move is invalid. Please try again:");
            invalidMove = false;
        } else if (whitesTurnToMove) {
            System.out
                    .println("""
                            ___________________________________________________
                            White's turn to move
                            ___________________________________________________
                            """);
        } else {
            System.out
                    .println("""
                            ___________________________________________________
                            Black's turn to move
                            ___________________________________________________
                            """);
        }

        move = user_input.nextLine();

        String lowerCase = move.toLowerCase();
        String[] components = lowerCase.split(" ");

        srcRow = 7 - (components[0].charAt(1) - '1');
        srcCol = components[0].charAt(0) - 'a';
        destRow = 7 - (components[2].charAt(1) - '1');
        destCol = components[2].charAt(0) - 'a';

        if (moveValid()) {
            updateScore();
            chessboard[destRow][destCol] = chessboard[srcRow][srcCol];
            chessboard[srcRow][srcCol] = null;
            whitesTurnToMove = !whitesTurnToMove;
        } else {
            invalidMove = true;
            move();
        }
    }

}
