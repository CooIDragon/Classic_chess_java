package ru.vsu.cs.baturin_v_a;

public class Rook extends AbstractPiece {

    public Rook(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public void draw() {
        if (isWhite){
            System.out.print("wR");
        }
        else{
            System.out.print("bR");
        }
    }

    private static Boolean straightPath(int srcRow, int srcCol,
                                        int destRow, int destCol) {
        return !((srcRow != destRow) && (srcCol != destCol));
    }

    @Override
    public boolean isMoveValid(int srcRow, int srcCol, int destRow, int destCol) {
        return straightPath(srcRow, srcCol, destRow, destCol);
    }
}