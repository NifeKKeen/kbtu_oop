package lab2.chess.models.pieces;

import lab2.chess.models.Board;
import lab2.chess.models.Position;

public class King extends FixedMovePieces {
    public King(PieceColor color, Position p, Board board) {
        super(color, p, board);
        this.allowedDirs = new int[][]{{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};
    }
}
