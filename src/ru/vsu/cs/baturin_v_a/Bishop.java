package ru.vsu.cs.baturin_v_a;

public class Bishop extends AbstractPiece {

    public Bishop(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public void draw() {
        if (isWhite) {
            System.out.print("wB");
        } else {
            System.out.print("bB");
        }
    }

    private static Boolean diagonalPath(int srcRow, int srcCol,
                                        int destRow, int destCol) {
        return ((Math.abs(srcRow - destRow) == Math.abs(srcCol
                - destCol)));
    }

    @Override
    public boolean isMoveValid(int srcRow, int srcCol, int destRow, int destCol) {
        return diagonalPath(srcRow, srcCol, destRow, destCol);
    }
}
