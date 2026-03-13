package lab2.problem2.models;

public class Pawn extends Piece {
    public Pawn(Position p, PieceColor color) {
        super(p, color);
    }

    @Override
    boolean isLegalMove(Position p2) {
        if (isSamePosition(p2))
            return false;

        if (p2.getX() != p.getX())
            return false;

        if (this.color == PieceColor.WHITE) {
            if (this.p.getY() == 1) {
                return p2.getY() == 2 || p2.getY() == 3;
            } else {
                return p.getY() + 1 == p2.getY();
            }
        } else {
            if (this.p.getY() == 6) {
                return p2.getY() == 5 || p2.getY() == 4;
            } else {
                return p.getY() - 1 == p2.getY();
            }
        }
    }
}
