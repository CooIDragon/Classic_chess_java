package ru.vsu.cs.baturin_v_a;

public abstract class AbstractPiece {

    boolean isWhite;

    public AbstractPiece(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public abstract void draw();

    public abstract boolean isMoveValid(int srcRow, int srcCol, int destRow,
                                        int destCol);
}
