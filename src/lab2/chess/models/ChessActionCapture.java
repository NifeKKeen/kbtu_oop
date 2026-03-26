package lab2.chess.models;

import lab2.chess.models.pieces.Piece;

public class ChessActionCapture extends ChessAction {
    public final Position fromPos, toPos;
    public final Piece fromPiece, toPiece;
    public ChessActionCapture(Position fromPos, Position toPos, Piece fromPiece, Piece toPiece) {
        this.fromPos = new Position(fromPos);
        this.toPos = new Position(toPos);
        this.fromPiece = fromPiece;
        this.toPiece = toPiece;
    }

    public int getCaptureDx() {
        return toPos.getX() - fromPos.getX();
    }
}
