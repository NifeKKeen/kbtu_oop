package lab2.chess.models.pieces;

import lab2.chess.models.Board;
import lab2.chess.models.Position;

public class Queen extends InfiniteLenMovePieces {
    public Queen(PieceColor color, Position p, Board board) {
        super(color, p, board);
        this.allowedDirs = new int[][]{{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};
    }

    public boolean canReachFormula(Position p1, Position p2) {
        return Bishop._canReachFormula(p1, p2) || Rook._canReachFormula(p1, p2);
    }

    public static boolean _canReachFormula(Position p1, Position p2) {
        return Bishop._canReachFormula(p1, p2) || Rook._canReachFormula(p1, p2);
    }
}
