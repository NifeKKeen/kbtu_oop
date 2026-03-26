package lab2.chess.models;

import lab2.chess.models.pieces.Piece;

public class ChessActionCapture extends ChessAction {
    public final Position fromPos, toPos;
    public final Piece fromPiece, toPiece;
    public ChessActionCapture(Position fromPos, Position toPos, Piece fromPiece, Piece toPiece) {
        this.fromPos = fromPos;
        this.toPos = toPos;
        this.fromPiece = fromPiece;
        this.toPiece = toPiece;
    }
}
