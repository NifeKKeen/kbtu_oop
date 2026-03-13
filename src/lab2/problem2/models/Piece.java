package lab2.problem2.models;

public abstract class Piece {
    protected Position p; // 0 indexed
    protected PieceColor color;

    public Piece(Position p, PieceColor color) {
        this.p = p;
        this.color = color;
    }

    boolean isSamePosition(Position p2) {
        return p.getX() == p2.getX() && p.getY() == p2.getY();
    }

    abstract boolean isLegalMove(Position p2); // look from white side
}
