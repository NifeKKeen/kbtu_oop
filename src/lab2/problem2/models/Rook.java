package lab2.problem2.models;

public class Rook extends Piece {
    public Rook(Position p, PieceColor color) {
        super(p, color);
    }

    @Override
    boolean isLegalMove(Position p2) {
        if (isSamePosition(p2))
            return false;

        return p.getX() == p2.getX() || p.getY() == p2.getY();
    }
}
