package lab2.chess.models.pieces;

import lab2.chess.models.Board;
import lab2.chess.models.Position;

public class Rook extends InfiniteLenMovePieces {
    public Rook(PieceColor color, Position p, Board board) {
        super(color, p, board);
        this.allowedDirs = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    }

    public boolean canReachFormula(Position p1, Position p2) {
        return p1.getX() == p2.getX() || p1.getY() == p2.getY();
    }

    public static boolean _canReachFormula(Position p1, Position p2) {
        return p1.getX() == p2.getX() || p1.getY() == p2.getY();
    }
}
