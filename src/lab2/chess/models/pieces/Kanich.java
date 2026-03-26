package lab2.chess.models.pieces;

import lab2.chess.models.Board;
import lab2.chess.models.Position;

public class Kanich extends FixedMovePieces {
    public Kanich(PieceColor color, Position p, Board board) {
        super(color, p, board);
        if (color == PieceColor.WHITE) {
            this.allowedDirs = new int[][]{
                    {2, 0}, {2, 2},
                    {1, 0}, {1, 1},
                    {-1, 0}, {-1, 1},
                    {-2, 0}, {-2, 2}
            };
        } else {
            this.allowedDirs = new int[][]{
                    {2, 0}, {2, -2},
                    {1, 0}, {1, -1},
                    {-1, 0}, {-1, -1},
                    {-2, 0}, {-2, -2}
            };
        }
    }
}
