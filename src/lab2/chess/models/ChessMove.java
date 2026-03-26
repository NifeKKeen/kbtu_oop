package lab2.chess.models;

import lab2.chess.models.pieces.Piece;

public class ChessMove {
    public final Position fromPos, toPos;
    public final Piece fromPiece, toPiece;
    public ChessMove(Position fromPos, Position toPos, Piece fromPiece, Piece toPiece) {
        this.fromPos = fromPos;
        this.toPos = toPos;
        this.fromPiece = fromPiece;
        this.toPiece = toPiece;
    }
}
