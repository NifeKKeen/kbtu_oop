package lab2.chess.models;

import lab2.chess.models.pieces.Piece;

public class ChessActionReplace extends ChessAction {
    public final Piece pieceBefore, pieceAfter;
    ChessActionReplace(Piece pieceBefore, Piece pieceAfter) {
        this.pieceBefore = pieceBefore;
        this.pieceAfter = pieceAfter;
    }
}
