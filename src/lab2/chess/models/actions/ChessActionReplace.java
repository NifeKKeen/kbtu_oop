package lab2.chess.models.actions;

import lab2.chess.models.Position;
import lab2.chess.models.pieces.Piece;

public class ChessActionReplace extends ChessAction {
    public final Position fromPos, toPos;
    public final Piece pieceBefore, pieceAfter;
    public ChessActionReplace(Position fromPos, Position toPos, Piece pieceBefore, Piece pieceAfter) {
        this.fromPos = new Position(fromPos);
        this.toPos = new Position(toPos);
        this.pieceBefore = pieceBefore;
        this.pieceAfter = pieceAfter;
    }
}
