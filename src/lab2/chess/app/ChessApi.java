package lab2.chess.app;

import lab2.chess.models.Board;
import lab2.chess.models.Position;
import lab2.chess.models.pieces.*;

public class ChessApi {
    private Board board;
    private PieceColor turn = PieceColor.WHITE;
    private Position curPtr = new Position(0, 0);

    public ChessApi(Board board) {
        this(board, PieceColor.WHITE);
    }

    public ChessApi(Board board, PieceColor turn) {
        this.board = board;
        this.turn = turn;
    }

    public boolean makeTurn(Position p1, Position p2) {
        Piece piece1 = board.getPiece(p1);

        if (piece1 == null) {
            return false;
        }

        if (piece1.getColor() != turn) {
            return false;
        }

        if (!piece1.isLegalMove(p2)) {
            return false;
        }

        board.hardPlacePiece(piece1, p2);
        if (piece1.getColor() == PieceColor.WHITE) {
            turn = PieceColor.BLACK;
        } else {
            turn = PieceColor.WHITE;
        }

        return true;
    }

    private static String getPieceChar(Piece piece) {
        if (piece == null) {
            return "  ";
        } else if (piece.getColor() == PieceColor.WHITE) {
            if (piece instanceof Pawn) {
                return "wP";
            } else if (piece instanceof King) {
                return "wK";
            } else if (piece instanceof Queen) {
                return "wQ";
            } else if (piece instanceof Bishop) {
                return "wB";
            } else if (piece instanceof Knight) {
                return "wN";
            } else if (piece instanceof Rook) {
                return "wR";
            }
        } else {
            if (piece instanceof Pawn) {
                return "bP";
            } else if (piece instanceof King) {
                return "bK";
            } else if (piece instanceof Queen) {
                return "bQ";
            } else if (piece instanceof Bishop) {
                return "bB";
            } else if (piece instanceof Knight) {
                return "bN";
            } else if (piece instanceof Rook) {
                return "bR";
            }
        }
        return "na";
    }

    public void printBoard() {
        StringBuilder sb = new StringBuilder();
        int strCols = board.MAX_COLS * 4 + 1;
        for (int i = board.MAX_ROWS - 1; i >= 0; i--) {
            for (int j = 0; j < board.MAX_COLS; j++) {
                sb.append("    ");
            }
            sb.append("\n");

            for (int j = 0; j < board.MAX_COLS; j++) {
                sb.append(" ");
                sb.append(getPieceChar(board.getPiece(new Position(i, j))));
                sb.append(" ");
            }
            sb.append("\n");
            System.out.println();
        }
        for (int j = 0; j < board.MAX_COLS; j++) {
            sb.append("   ");
        }
        sb.append("\n");

        int cursorStrRow = (board.MAX_ROWS - curPtr.getX() - 1) * 2 + 1;
        int cursorStrCol = curPtr.getY() * 4 + 1;

        sb.setCharAt(cursorStrRow * strCols + cursorStrCol - 1, '>');
        sb.setCharAt(cursorStrRow * strCols + cursorStrCol + 2, '<');

        System.out.print(sb.toString());
    }

    public PieceColor getTurn() {
        return turn;
    }

    public void pushCursor(int dx, int dy) {
        if (!board.isOnField(curPtr.getX() + dx, curPtr.getY() + dy)) {
            return;
        }
        curPtr.addX(dx);
        curPtr.addY(dy);
    }

    public void setCursor(int x, int y) {
        curPtr.setX(x);
        curPtr.setY(y);
    }

    public void setCursor(Position p) {
        curPtr.setX(p.getX());
        curPtr.setY(p.getY());
    }

    public Position getCursor() {
        return curPtr;
    }
}
