package lab2.chess.models.pieces;

import lab2.chess.models.Board;
import lab2.chess.models.Position;

public class Knight extends Piece {
    static int[] moveDx = {1, 2, 2, 1, -1, -2, -2, -1}, modeDy = {2, 1, -1, -2, -2, -1, 1, 2};

    public Knight(PieceColor color, Position p, Board board) {
        super(color, p, board);
    }

    @Override
    public boolean isLegalMove(Position p2) {
        if (!isOnBoard()) {
            return false;
        }

        if (isSamePosition(p2) || !board.isOnField(p2)) {
            return false;
        }

        Piece piece2 = board.getPiece(p2);
        if (piece2 != null && this.color == piece2.getColor()) {
            return false;
        }

        for (int i = 0; i < 8; i++) {
            if (p.getX() + moveDx[i] == p2.getX() && p.getY() + modeDy[i] == p2.getY()) {
                return true;
            }
        }
        return false;
    }
}
