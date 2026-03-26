package lab2.chess.models.pieces;

import lab2.chess.models.Board;
import lab2.chess.models.Position;

public class Bishop extends Piece {
    public Bishop(PieceColor color, Position p, Board board) {
        super(color, p, board);
    }

    public static boolean canReachFormula(Position p1, Position p2) {
        return Math.abs(p1.getX() - p2.getX()) == Math.abs(p1.getY() - p2.getY());
    }

    @Override
    public boolean isLegalMove(Position p2) {
        if (!isOnBoard()) {
            return false;
        }

        if (isSamePosition(p2) || !board.isOnField(p2)) {
            return false;
        }

        if (!(Bishop.canReachFormula(p, p2))) {
            return false;
        }

        Piece piece2 = board.getPiece(p2);
        if (piece2 != null && this.color == piece2.getColor()) {
            return false;
        }

        int dx = p2.getX() - p.getX();
        int dy = p2.getY() - p.getY();

        dx = Math.clamp(dx, -1, 1);
        dy = Math.clamp(dy, -1, 1);

        Position cur = new Position(p.getX(), p.getY());
        while (!(cur.getX() == p2.getX() && cur.getY() == p2.getY())) {
            cur.addX(dx);
            cur.addY(dy);
            if (board.getPiece(cur) != null) {
                break;
            }
        }

        return cur.getX() == p2.getX() && cur.getY() == p2.getY();
    }
}
