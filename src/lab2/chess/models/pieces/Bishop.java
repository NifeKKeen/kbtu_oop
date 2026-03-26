package lab2.chess.models.pieces;

import lab2.chess.models.Board;
import lab2.chess.models.Position;

public class Bishop extends InfiniteLenMovePieces {
    public Bishop(PieceColor color, Position p, Board board) {
        super(color, p, board);
        this.allowedDirs = new int[][]{{1, 1}, {-1, 1}, {-1, -1}, {1, -1}};
    }

    @Override
    public boolean canReachFormula(Position p1, Position p2) {
        return Math.abs(p1.getX() - p2.getX()) == Math.abs(p1.getY() - p2.getY());
    }

    public static boolean _canReachFormula(Position p1, Position p2) {
        return Math.abs(p1.getX() - p2.getX()) == Math.abs(p1.getY() - p2.getY());
    }
}
