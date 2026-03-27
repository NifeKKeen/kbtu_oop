package lab2.chess.models.actions;

import lab2.chess.models.pieces.King;
import lab2.chess.models.pieces.PieceColor;
import lab2.chess.models.pieces.Rook;

import java.util.ArrayList;

public class Turn {
    private final ArrayList<ChessAction> actions;
    public final PieceColor side;
    private boolean isComplete = false;
    public boolean _whiteKingMoved = false, _blackKingMoved = false;
    public ArrayList<Rook> _whiteRooksMoved = new ArrayList<>(),
                            _blackRooksMoved = new ArrayList<>();
    public final Turn prevTurn;

    public Turn(Turn prevTurn, PieceColor side) {
        this.side = side;
        actions = new ArrayList<>();

        this.prevTurn = prevTurn;
        if (prevTurn != null) {
            _whiteKingMoved = prevTurn._whiteKingMoved;
            _blackKingMoved = prevTurn._blackKingMoved;
            _whiteRooksMoved = prevTurn._whiteRooksMoved;
            _blackRooksMoved = prevTurn._blackRooksMoved;
        }
    }

    public void addAction(ChessAction action) {
        actions.add(action);
        if (action instanceof ChessActionCapture) {
            ChessActionCapture actionCapture = (ChessActionCapture) action;
            if (actionCapture.fromPiece.getColor() == PieceColor.WHITE) {
                if (actionCapture.fromPiece instanceof Rook) {
                    ArrayList<Rook> whiteRooksMovedCopy = new ArrayList<>(_whiteRooksMoved);
                    whiteRooksMovedCopy.add((Rook) actionCapture.fromPiece);
                    _whiteRooksMoved = whiteRooksMovedCopy;
                } else if (actionCapture.fromPiece instanceof King) {
                    _whiteKingMoved = true;
                }
            } else {
                if (actionCapture.fromPiece instanceof Rook) {
                    ArrayList<Rook> blackRooksMovedCopy = new ArrayList<>(_blackRooksMoved);
                    blackRooksMovedCopy.add((Rook) actionCapture.fromPiece);
                    _blackRooksMoved = blackRooksMovedCopy;
                } else if (actionCapture.fromPiece instanceof King) {
                    _blackKingMoved = true;
                }
            }
        }
    }

    public ArrayList<ChessAction> getActions() {
        return actions;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete() {
        isComplete = true;
    }
}
