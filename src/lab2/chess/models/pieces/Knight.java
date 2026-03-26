package lab2.chess.models.pieces;

import lab2.chess.models.Board;
import lab2.chess.models.Position;

public class Knight extends FixedMovePieces {
        public Knight(PieceColor color, Position p, Board board) {
            super(color, p, board);
            this.allowedDirs = new int[][]{{1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}};
        }
    }
