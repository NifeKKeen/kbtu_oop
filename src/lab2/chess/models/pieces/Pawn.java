package lab2.chess.models.pieces;

import lab2.chess.models.Board;
import lab2.chess.models.Position;

public class Pawn extends Piece {
    public Pawn(PieceColor color, Position p, Board board) {
        super(color, p, board);
    }

    @Override
    public boolean isLegalMove(Position p2) {
        if (!isOnBoard()) {
            return false;
        }

        if (!(!isSamePosition(p2) && board.isOnField(p2))) {
            return false;
        }

        Piece piece2 = board.getPiece(p2);
        if (piece2 != null && this.color == piece2.getColor()) {
            return false;
        }

        int dx = p2.getX() - p.getX();
        int dy = p2.getY() - p.getY();

        if (this.color == PieceColor.WHITE) {
            int mxPush = 1;
            if (this.p.getY() == 1) {
                mxPush = 2;
            }

            if (this.p.getX() == p2.getX()) { // same column
                if (!(1 <= dy && dy <= mxPush)) {
                    return false;
                }

                for (int i = this.p.getX(); i < this.p.getX() + mxPush; i++) {
                    if (board.getPiece(new Position(i, p2.getY())) != null) {
                        return false;
                    }
                }

                return true;
            } else { // different columns
                if (!(dy == 1 && (dx == -1 || dx == 1))) {
                    return false;
                }

                if (board.getPiece(p2) == null
                        // || piece2.getColor() == this.color // already checked
                ) {
                    return false;
                }

                return true;
            }
        } else {
            int mxPush = 1;
            if (this.p.getY() == 6) {
                mxPush = 2;
            }

            if (this.p.getX() == p2.getX()) { // same column
                if (!(-mxPush <= dy && dy <= -1)) {
                    return false;
                }

                for (int i = this.p.getX() - 1; i >= this.p.getX() - mxPush; i--) {
                    if (board.getPiece(new Position(i, p2.getY())) != null) {
                        return false;
                    }
                }

                return true;
            } else { // different columns
                if (!(dy == -1 && (dx == -1 || dx == 1))) {
                    return false;
                }

                if (board.getPiece(p2) == null
                    // || piece2.getColor() == this.color // already checked
                ) {
                    return false;
                }

                return true;
            }
        }
    }
}
