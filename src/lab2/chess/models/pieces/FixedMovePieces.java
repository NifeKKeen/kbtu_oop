package lab2.chess.models.pieces;

import lab2.chess.models.Board;
import lab2.chess.models.Position;

public class FixedMovePieces extends Piece {
    public int[][] allowedDirs;
    public FixedMovePieces(PieceColor color, Position p, Board board) {
        super(color, p, board);
    }

    @Override
    public boolean canCapturePiece(Position p2) {
        if (!isOnBoard()) {
            return false;
        }

        if (isSamePosition(p2) || !board.isOnField(p2)) {
            return false;
        }

        for (int[] dirDelta : allowedDirs) {
            if (p.getX() + dirDelta[0] == p2.getX() && p.getY() + dirDelta[1] == p2.getY()) {
                return true;
            }
        }
        return false;
    }
}
