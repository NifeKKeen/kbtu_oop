package lab2.chess.models;

import lab2.chess.models.actions.ChessAction;
import lab2.chess.models.actions.ChessActionCapture;
import lab2.chess.models.actions.ChessActionReplace;
import lab2.chess.models.pieces.*;

import java.util.ArrayList;

public class Board {
    // 0 indexed
    private final Piece[][] field;
    public final int MAX_ROWS, MAX_COLS;
    private final ArrayList<ChessAction> moveHistory = new ArrayList<>();

    public Board() {
        this(8, 8);

        // Pawns
        for (int col = 0; col < 8; col++) {
            field[1][col] = new Pawn(PieceColor.WHITE, new Position(1, col), this);
            field[6][col] = new Pawn(PieceColor.BLACK, new Position(6, col), this);
        }

        // Rooks
        field[0][0] = new Rook(PieceColor.WHITE, new Position(0, 0), this);
        field[0][7] = new Rook(PieceColor.WHITE, new Position(0, 7), this);
        field[7][0] = new Rook(PieceColor.BLACK, new Position(7, 0), this);
        field[7][7] = new Rook(PieceColor.BLACK, new Position(7, 7), this);

        // Knights
        field[0][1] = new Knight(PieceColor.WHITE, new Position(0, 1), this);
        field[0][6] = new Knight(PieceColor.WHITE, new Position(0, 6), this);
        field[7][1] = new Knight(PieceColor.BLACK, new Position(7, 1), this);
        field[7][6] = new Knight(PieceColor.BLACK, new Position(7, 6), this);

        // Bishops
        // field[0][2] = new Bishop(PieceColor.WHITE, new Position(0, 2), this);
        field[0][5] = new Bishop(PieceColor.WHITE, new Position(0, 5), this);
        field[7][2] = new Bishop(PieceColor.BLACK, new Position(7, 2), this);
        field[7][5] = new Bishop(PieceColor.BLACK, new Position(7, 5), this);

        // Queens
        // field[0][3] = new Queen(PieceColor.WHITE, new Position(0, 3), this);
        field[7][3] = new Queen(PieceColor.BLACK, new Position(7, 3), this);

        // Kings
        field[0][4] = new King(PieceColor.WHITE, new Position(0, 4), this);
        field[7][4] = new King(PieceColor.BLACK, new Position(7, 4), this);

        // // Kanichs
        // field[2][4] = new Kanich(PieceColor.WHITE, new Position(2, 4), this);
        // field[5][4] = new Kanich(PieceColor.BLACK, new Position(5, 4), this);
    }

    public Board(int mxRows, int mxCols) {
        this.MAX_ROWS = mxRows;
        this.MAX_COLS = mxRows;
        field = new Piece[mxRows][mxCols];
    }

    public ChessActionCapture hardCapture(Piece piece, Position p2) {
        if (piece.getBoard() != this) {
            throw new IllegalArgumentException("Piece is not on this board");
        }

        if (!isOnField(p2)) {
            throw new IllegalArgumentException("Position is out of bounds");
        }

        ChessActionCapture action = new ChessActionCapture(new Position(piece.getP()), new Position(p2), piece, field[p2.getX()][p2.getY()]);
        moveHistory.add(action);

        if (field[p2.getX()][p2.getY()] != null) {
            Piece piece2 = field[p2.getX()][p2.getY()];
            piece2.removeFromBoard();
        }

        field[piece.getP().getX()][piece.getP().getY()] = null;
        field[p2.getX()][p2.getY()] = piece;
        piece.setP(p2);
        return action;
    }

    public ChessActionCapture undoHardCapture() {
        if (moveHistory.isEmpty()) {
            return null;
        }

        if (!(moveHistory.getLast() instanceof ChessActionCapture)) {
            return null;
        }

        ChessActionCapture lastMove = (ChessActionCapture)moveHistory.getLast();

        Piece fromPiece = lastMove.fromPiece,
                toPiece = lastMove.toPiece;

        field[lastMove.fromPos.getX()][lastMove.fromPos.getY()] = fromPiece;
        fromPiece.setP(lastMove.fromPos);

        field[lastMove.toPos.getX()][lastMove.toPos.getY()] = toPiece;
        if (toPiece != null) {
            toPiece.placeToBoard(this, lastMove.toPos);
        }

        moveHistory.removeLast();
        return lastMove;
    }

    public Piece getPiece(Position p) {
        return field[p.getX()][p.getY()];
    }

    public boolean isOnField(Position p) {
        return 0 <= p.getX() && p.getX() < MAX_ROWS && 0 <= p.getY() && p.getY() < MAX_COLS;
    }
    public boolean isOnField(int x, int y) {
        return 0 <= x && x < MAX_ROWS && 0 <= y && y < MAX_COLS;
    }

    public boolean isAttacked(Position p, PieceColor withColor) {
        for (int i = 0; i < MAX_ROWS; i++) {
            for (int j = 0; j < MAX_COLS; j++) {
                Position p2 = new Position(i, j);
                Piece piece = getPiece(p2);
                if (piece == null) {
                    continue;
                }
                if (piece.getColor() != withColor && piece.canCapturePiece(p)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isKingAttackedAfterMove(King king, Position p1, Position p2) {
        // assuming move from p1 to p2 is legal

        hardCapture(getPiece(p1), p2);
        boolean isKingAttacked = false;
        if (king.getColor() == PieceColor.WHITE) {
            if (isAttacked(king.getP(), PieceColor.WHITE)) {
                isKingAttacked = true;
            }
        } else {
            if (isAttacked(king.getP(), PieceColor.BLACK)) {
                isKingAttacked = true;
            }
        }

        undoHardCapture();
        return isKingAttacked;
    }

    public King getAnyKing(PieceColor color) {
        for (int i = 0; i < MAX_ROWS; i++) {
            for (int j = 0; j < MAX_COLS; j++) {
                Position p = new Position(i, j);
                Piece piece = getPiece(p);
                if (piece instanceof King && piece.getColor() == color) {
                    return (King) piece;
                }
            }
        }
        return null;
    }

    public ChessActionReplace hardReplace(Position p, Piece piece) {
        Piece pieceBefore = field[p.getX()][p.getY()];
        ChessActionReplace action = new ChessActionReplace(piece == null ? p : piece.getP(), p, pieceBefore, piece);
        moveHistory.add(action);
        field[p.getX()][p.getY()] = piece;
        if (piece != null) {
            piece.placeToBoard(this, p);
        }
        if (pieceBefore != null) {
            pieceBefore.removeFromBoard();
        }
        return action;
    }

    public ChessActionReplace undoHardReplace() {
        if (moveHistory.isEmpty()) {
            return null;
        }
        if (!(moveHistory.getLast() instanceof ChessActionReplace)) {
            return null;
        }

        ChessActionReplace lastMove = (ChessActionReplace)moveHistory.getLast();
        if (lastMove.pieceAfter != null) {
            field[lastMove.pieceAfter.getP().getX()][lastMove.pieceAfter.getP().getY()] = null;
        }

        if (lastMove.pieceBefore != null) {
            lastMove.pieceBefore.placeToBoard(this, lastMove.fromPos);
            field[lastMove.pieceBefore.getP().getX()][lastMove.pieceBefore.getP().getY()] = lastMove.pieceBefore;
        }

        if (lastMove.pieceAfter != null) {
            lastMove.pieceAfter.removeFromBoard();
        }

        moveHistory.removeLast();
        return lastMove;
    }

    // only repeats what is on action,
    // it will not add the action parameter itself to histroy stack
    public ChessAction doAction(ChessAction action) {
        if (action instanceof ChessActionCapture) {
            ChessActionCapture actionCapture = (ChessActionCapture) action;
            actionCapture.fromPiece.placeToBoard(this, actionCapture.fromPos);
            if (actionCapture.toPiece != null) {
                actionCapture.toPiece.placeToBoard(this, actionCapture.toPos);
            }
            return hardCapture(actionCapture.fromPiece, actionCapture.toPos);
        } else if (action instanceof ChessActionReplace) {
            ChessActionReplace actionReplace = (ChessActionReplace) action;
            if (actionReplace.pieceBefore != null) {
                actionReplace.pieceBefore.placeToBoard(this, actionReplace.fromPos);
            }
            if (actionReplace.pieceAfter != null) {
                actionReplace.pieceAfter.placeToBoard(this, actionReplace.toPos);
            }
            return hardReplace(actionReplace.fromPos, actionReplace.pieceAfter);
        }
        return null;
    }

    public ChessAction undoAction() {
        if (moveHistory.isEmpty()) {
            return null;
        }
        if (moveHistory.getLast() instanceof ChessActionReplace) {
            return undoHardReplace();
        } else if (moveHistory.getLast() instanceof ChessActionCapture) {
            return undoHardCapture();
        }
        return null;
    }

    public ChessAction getLastAction() {
        return moveHistory.isEmpty() ? null : moveHistory.getLast();
    }
}
