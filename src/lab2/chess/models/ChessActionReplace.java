package lab2.chess.models;

import lab2.chess.models.pieces.Piece;

public class ChessActionReplace extends ChessAction {
    public final Position fromPos, toPos;
    public final Piece pieceBefore, pieceAfter;
    ChessActionReplace(Position fromPos, Position toPos, Piece pieceBefore, Piece pieceAfter) {
        this.fromPos = new Position(fromPos);
        this.toPos = new Position(toPos);
        this.pieceBefore = pieceBefore;
        this.pieceAfter = pieceAfter;

    }
}
