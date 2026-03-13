package lab2.problem2.models;

public class Queen extends Piece {
    public Queen(Position p, PieceColor color) {
        super(p, color);
    }

    @Override
    boolean isLegalMove(Position p2) {
        if (isSamePosition(p2))
            return false;

        return Math.abs(p.getX() - p2.getX()) == Math.abs(p.getY() - p2.getY()) ||
                p.getX() == p2.getX() || p.getY() == p2.getY();
    }
}
