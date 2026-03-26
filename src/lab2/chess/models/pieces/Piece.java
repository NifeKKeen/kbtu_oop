package lab2.chess.models.pieces;

import lab2.chess.models.Board;
import lab2.chess.models.Position;

public abstract class Piece {
    protected PieceColor color;
    protected Position p;
    protected Board board;

    public Piece(PieceColor color, Position p, Board board) {
        if (board == null) {
            throw new IllegalArgumentException("Board cannot be null");
        }

        this.color = color;
        this.p = p;
        this.board = board;
    }

    public boolean isSamePosition(Position p2) {
        return p.getX() == p2.getX() && p.getY() == p2.getY();
    }

    public boolean isOnBoard() {
        return board != null;
    }

    abstract public  boolean isLegalMove(Position p2); // look from white side

    public Position getP() {
        return p;
    }

    public void setP(Position p) {
        this.p.setX(p.getX());
        this.p.setY(p.getY());
    }

    public PieceColor getColor() {
        return color;
    }

    public Board getBoard() {
        return board;
    }

    public void removeFromBoard() {
        this.board = null;
        this.p = null;
    }
}
