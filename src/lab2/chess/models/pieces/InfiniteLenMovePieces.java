package lab2.chess.models.pieces;

import lab2.chess.models.Board;
import lab2.chess.models.Position;

abstract public class InfiniteLenMovePieces extends Piece {
    public int[][] allowedDirs;
    public InfiniteLenMovePieces(PieceColor color, Position p, Board board) {
        super(color, p, board);
    }

    abstract public boolean canReachFormula(Position p1, Position p2);

    @Override
    public boolean canCapturePiece(Position p2) {
        if (!isOnBoard()) {
            return false;
        }

        if (isSamePosition(p2) || !board.isOnField(p2)) {
            return false;
        }

        if (!(this.canReachFormula(p, p2))) {
            return false;
        }

        int dx = p2.getX() - p.getX();
        int dy = p2.getY() - p.getY();

        dx = Math.clamp(dx, -1, 1);
        dy = Math.clamp(dy, -1, 1);

        boolean canMoveOnAllowedDirection = false;
        for (int[] dir : allowedDirs) {
            if (dx == dir[0] && dy == dir[1]) {
                canMoveOnAllowedDirection = true;
                break;
            }
        }

        if (!canMoveOnAllowedDirection) {
            return false;
        }

        Position maxProceeded = proceed(board, p, p2, dx, dy);
        return maxProceeded.getX() == p2.getX() && maxProceeded.getY() == p2.getY();
    }

    static public Position proceed(Board board, Position p1, Position p2, int dx, int dy) {
        Position cur = new Position(p1);
        while (!(cur.getX() == p2.getX() && cur.getY() == p2.getY())) {
            cur.addX(dx);
            cur.addY(dy);
            if (board.getPiece(cur) != null) {
                break;
            }
        }
        return cur;
    }
}
