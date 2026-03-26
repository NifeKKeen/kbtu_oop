package lab2.chess.app;

import lab2.chess.models.Board;
import lab2.chess.models.Position;
import lab2.chess.models.pieces.*;

public class ChessApi {
    private Board board;
    private PieceColor turn = PieceColor.WHITE;
    private Position curPtr = new Position(0, 0);
    private Piece selectedPiece = null;
    
    public ChessApi(Board board) {
        this(board, PieceColor.WHITE);
    }

    public ChessApi(Board board, PieceColor turn) {
        this.board = board;
        this.turn = turn;
    }

    public PieceColor getWinner() {
        boolean whiteKingExists = false;
        boolean blackKingExists = false;
        for (int i = 0; i < board.MAX_ROWS; i++) {
            for (int j = 0; j < board.MAX_COLS; j++) {
                Position p = new Position(i, j);
                Piece piece = board.getPiece(p);
                if (piece instanceof King) {
                    if (piece.getColor() == PieceColor.WHITE) {
                        whiteKingExists = true;
                    } else if (piece.getColor() == PieceColor.BLACK) {
                        blackKingExists = true;
                    }
                }
            }
        }
        if (whiteKingExists && blackKingExists) {
            return null;
        } else if (!whiteKingExists) {
            return PieceColor.BLACK;
        } else {
            return PieceColor.WHITE;
        }       
    }
    
    public void setSelectedPiece(Piece piece) {
        selectedPiece = piece;
    }

    public void setSelectedPieceAt(Position p) {
        selectedPiece = board.getPiece(p);
    }

    public void selectAt(Position p) {
        if (!board.isOnField(p)) {
            return;
        }
        Piece piece = board.getPiece(p);
        if (selectedPiece == null) {
            if (piece != null && piece.getColor() == turn) {
                selectedPiece = piece;
            }
        } else {
            this.makeTurn(selectedPiece.getP(), p);
            selectedPiece = null;
        }
    }

    public boolean makeTurn(Position p1, Position p2) {
        if (getWinner() != null) {
            return false;
        }
        
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
        
        King turnSideKing = board.getAnyKing(turn);
        if (board.isKingAttackedAfterMove(turnSideKing, p1, p2)) {
            return false;
        }

        board.hardPlacePiece(piece1, p2);
        if (turn == PieceColor.WHITE) {
            turn = PieceColor.BLACK;
        } else {
            turn = PieceColor.WHITE;
        }

        return true;
    }

    private static String getPieceChar(Piece piece) {
        if (piece == null) {
            return ".";
        } else if (piece.getColor() == PieceColor.WHITE) {
            if (piece instanceof Pawn) {
                return "♟";
            } else if (piece instanceof King) {
                return "♚";
            } else if (piece instanceof Queen) {
                return "♛";
            } else if (piece instanceof Bishop) {
                return "♝";
            } else if (piece instanceof Knight) {
                return "♞";
            } else if (piece instanceof Rook) {
                return "♜";
            }
        } else {
            if (piece instanceof Pawn) {
                return "♙";
            } else if (piece instanceof King) {
                return "♔";
            } else if (piece instanceof Queen) {
                return "♕";
            } else if (piece instanceof Bishop) {
                return "♗";
            } else if (piece instanceof Knight) {
                return "♘";
            } else if (piece instanceof Rook) {
                return "♖";
            }
        }
        return "n";
    }

    public void excelAt(StringBuilder sb, Position p, char left, char right) {
        int pos = getCellStartingPositionInSB(p);
        sb.setCharAt(pos - 1, left);
        sb.setCharAt(pos + 1, right);
    }

    public int getCellStartingPositionInSB(Position p) {
        int strCols = board.MAX_COLS * 3 + 1;
        int strRow = (board.MAX_ROWS - p.getX() - 1) * 2 + 1;
        return strRow * strCols + p.getY() * 3 + 1;
    }

    public void printBoard() {
        StringBuilder sb = new StringBuilder();

        sb.append(("   ".repeat(board.MAX_COLS) + "\n").repeat(board.MAX_ROWS * 2 + 1));

        King selectedSideKing = null;
        if (selectedPiece != null) {
            selectedSideKing = board.getAnyKing(selectedPiece.getColor());
        }

        for (int i = board.MAX_ROWS - 1; i >= 0; i--) {
            for (int j = 0; j < board.MAX_COLS; j++) {
                Position p = new Position(i, j);
                int strPos = getCellStartingPositionInSB(p);
                Piece piece = board.getPiece(p);
                if (piece != null) {
                    sb.setCharAt(strPos, getPieceChar(piece).charAt(0));
                    if (
                            selectedPiece != null &&
                            selectedPiece.isLegalMove(p)&&
                            !board.isKingAttackedAfterMove(selectedSideKing, selectedPiece.getP(), p)
                    ) {
                        excelAt(sb, p, '#', '#');
                    }
                } else {
                    boolean canSelectedMove = false;
                    if (selectedPiece != null &&
                            selectedPiece.isLegalMove(p) &&
                            !board.isKingAttackedAfterMove(selectedSideKing, selectedPiece.getP(), p)
                    ) {
                        canSelectedMove = true;
                    }

                    if (canSelectedMove) {
                        sb.setCharAt(strPos, "0".charAt(0));
                    } else if ((i + j) % 2 == 0) {
                        sb.setCharAt(strPos, "+".charAt(0));
                    } else {
                        sb.setCharAt(strPos, "-".charAt(0));
                    }
                }
            }
        }

        excelAt(sb, curPtr, '>', '<');

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
