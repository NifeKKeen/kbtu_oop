package lab2.chess.models.pieces;

import lab2.chess.models.Board;
import lab2.chess.models.actions.ChessAction;
import lab2.chess.models.actions.ChessActionCapture;
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

        if (isSamePosition(p2) || !board.isOnField(p2)) {
            return false;
        }

        int dx = p2.getX() - p.getX();
        int dy = p2.getY() - p.getY();

        int mxPush = 1;
        if (this.color == PieceColor.WHITE) {
            if (dy == 0) { // same column
                if (this.p.getX() == 1) {
                    mxPush = 2;
                }

                if (!(1 <= dx && dx <= mxPush)) {
                    return false;
                }

                for (int i = this.p.getX() + 1; i <= this.p.getX() + dx; i++) {
                    if (board.getPiece(new Position(i, p.getY())) != null) {
                        return false;
                    }
                }

                return true;
            } else { // different columns
                if (!(dx == 1 && (dy == -1 || dy == 1))) {
                    return false;
                }

                Piece pieceTo = board.getPiece(p2);
                if (pieceTo == null) {
                    // check for en passant
                    Piece sibling = board.getPiece(new Position(p.getX(), p.getY() + dy));
                    if (sibling == null || sibling.getColor() == this.color || !(sibling instanceof Pawn)) {
                        return false;
                    }

                    ChessAction lastAction = board.getLastAction();
                    if (lastAction == null) {
                        return false;
                    }
                    if (lastAction instanceof ChessActionCapture && ((ChessActionCapture) lastAction).fromPiece == sibling && ((ChessActionCapture) lastAction).getCaptureDx() == -2) {
                        return true;
                    }
                    return false;
                } else {
                    return pieceTo.getColor() != color;
                }
            }
        } else {
            if (this.p.getX() == 6) {
                mxPush = 2;
            }

            if (this.p.getY() == p2.getY()) { // same column
                if (!(-mxPush <= dx && dx <= -1)) {
                    return false;
                }

                for (int i = this.p.getX() - 1; i >= this.p.getX() + dx; i--) {
                    if (board.getPiece(new Position(i, p2.getY())) != null) {
                        return false;
                    }
                }

                return true;
            } else { // different columns
                if (!(dx == -1 && (dy == -1 || dy == 1))) {
                    return false;
                }

                Piece pieceTo = board.getPiece(p2);
                if (pieceTo == null) {
                    // check for en passant
                    Piece sibling = board.getPiece(new Position(p.getX(), p.getY() + dy));
                    if (sibling == null || sibling.getColor() == this.color || !(sibling instanceof Pawn)) {
                        return false;
                    }

                    ChessAction lastAction = board.getLastAction();
                    if (lastAction == null) {
                        return false;
                    }
                    if (lastAction instanceof ChessActionCapture && ((ChessActionCapture) lastAction).fromPiece == sibling && ((ChessActionCapture) lastAction).getCaptureDx() == 2) {
                        return true;
                    }
                    return false;
                } else {
                    return pieceTo.getColor() != color;
                }
            }
        }
    }

    @Override
    public boolean canCapturePiece(Position p2) {
        if (!isOnBoard()) {
            return false;
        }

        if (isSamePosition(p2) || !board.isOnField(p2)) {
            return false;
        }

        int dx = p2.getX() - p.getX();
        int dy = p2.getY() - p.getY();

        if (this.color == PieceColor.WHITE) {
            if (!(dx == 1 && (dy == -1 || dy == 1))) {
                return false;
            }

            return true;
        } else {
            if (!(dx == -1 && (dy == -1 || dy == 1))) {
                return false;
            }

            return true;
        }
    }

    public Pawn getEnPassantCapturedPawn(Position p2) {
        if (!isOnBoard()) {
            return null;
        }

        if (isSamePosition(p2) || !board.isOnField(p2)) {
            return null;
        }

        int dx = p2.getX() - p.getX();
        int dy = p2.getY() - p.getY();

        if (this.color == PieceColor.WHITE) {
            if (dy != 0) { // different columns
                if (!(dx == 1 && (dy == -1 || dy == 1))) {
                    return null;
                }

                Piece pieceTo = board.getPiece(p2);
                if (pieceTo == null) {
                    // check for en passant
                    Piece sibling = board.getPiece(new Position(p.getX(), p.getY() + dy));
                    if (sibling == null || sibling.getColor() == this.color || !(sibling instanceof Pawn)) {
                        return null;
                    }
                    Pawn siblingPawn = (Pawn) sibling;

                    ChessAction lastAction = board.getLastAction();
                    if (lastAction == null) {
                        return null;
                    }
                    if (lastAction instanceof ChessActionCapture && ((ChessActionCapture) lastAction).fromPiece == siblingPawn && ((ChessActionCapture) lastAction).getCaptureDx() == -2) {
                        return siblingPawn;
                    }
                    return null;
                } else {
                    return null;
                }
            }
        } else {
            if (this.p.getY() != p2.getY()) { // different columns
                if (!(dx == -1 && (dy == -1 || dy == 1))) {
                    return null;
                }

                Piece pieceTo = board.getPiece(p2);
                if (pieceTo == null) {
                    // check for en passant
                    Piece sibling = board.getPiece(new Position(p.getX(), p.getY() + dy));
                    if (sibling == null || sibling.getColor() == this.color || !(sibling instanceof Pawn)) {
                        return null;
                    }
                    Pawn siblingPawn = (Pawn) sibling;

                    ChessAction lastAction = board.getLastAction();
                    if (lastAction == null) {
                        return null;
                    }
                    if (lastAction instanceof ChessActionCapture && ((ChessActionCapture) lastAction).fromPiece == siblingPawn && ((ChessActionCapture) lastAction).getCaptureDx() == 2) {
                        return siblingPawn;
                    }
                    return null;
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    public boolean shouldPromote() {
        if (this.color == PieceColor.WHITE) {
            return this.p.getX() == board.MAX_ROWS - 1;
        } else if (this.color == PieceColor.BLACK) {
            return this.p.getX() == 0;
        }
        return false;
    }
}
