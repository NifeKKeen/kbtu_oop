package lab2.chess.app;

import lab2.chess.models.Board;
import lab2.chess.models.ChooseMenu;
import lab2.chess.models.Position;
import lab2.chess.models.pieces.*;

enum ActionNeededEnum {
    NONE,
    PAWN_PROMOTION,
    MOVE,
    RESTART,
}

public class ChessApi {
    private final Board board;
    private PieceColor turn;
    private final Position curPtr = new Position(0, 0);
    private Piece selectedPiece = null;

    final int PRINT_STR_ROWS, PRINT_STR_COLS;
    static final int CELL_WIDTH = 3;

    private Piece pieceToTransform = null;
    private ChooseMenu pawnPromotionMenu = null;

    public ChessApi(Board board) {
        this(board, PieceColor.WHITE);
    }

    public ChessApi(Board board, PieceColor turn) {
        this.board = board;
        this.turn = turn;
        PRINT_STR_ROWS = board.MAX_ROWS * 2 + 1;
        PRINT_STR_COLS = board.MAX_COLS * CELL_WIDTH + 15 + 1;
    }

    public PieceColor getWinner() {
        King whiteKing = board.getAnyKing(PieceColor.WHITE);
        King blackKing = board.getAnyKing(PieceColor.BLACK);
        if (whiteKing == null) {
            return PieceColor.BLACK;
        }
        if (blackKing == null) {
            return PieceColor.WHITE;
        }

        boolean haveMove = false;
        if (turn == PieceColor.WHITE) {
            for (int i = 0; i < board.MAX_ROWS; i++) {
                for (int j = 0; j < board.MAX_COLS; j++) {
                    Position p = new Position(i, j);
                    Piece piece = board.getPiece(p);
                    if (piece == null || piece.getColor() != PieceColor.WHITE) {
                        continue;
                    }

                    for (int i1 = 0; i1 < board.MAX_ROWS; i1++) {
                        for (int j1 = 0; j1 < board.MAX_COLS; j1++) {
                            Position p2 = new Position(i1, j1);
                            if (piece.isLegalMove(p2) && !board.isKingAttackedAfterMove(whiteKing, piece.getP(), p2)) {
                                haveMove = true;
                                break;
                            }
                        }
                        if (haveMove) break;
                    }
                    if (haveMove) break;
                }
                if (haveMove) break;
            }
            if (!haveMove && board.isAttacked(whiteKing.getP(), PieceColor.WHITE)) {
                return PieceColor.BLACK;
            }
            if (haveMove) {
                return null;
            } else {
                return null; // stalemate
            }
        } else {
            for (int i = 0; i < board.MAX_ROWS; i++) {
                for (int j = 0; j < board.MAX_COLS; j++) {
                    Position p = new Position(i, j);
                    Piece piece = board.getPiece(p);
                    if (piece == null || piece.getColor() != PieceColor.BLACK) {
                        continue;
                    }

                    for (int i1 = 0; i1 < board.MAX_ROWS; i1++) {
                        for (int j1 = 0; j1 < board.MAX_COLS; j1++) {
                            Position p2 = new Position(i1, j1);
                            if (piece.isLegalMove(p2) && !board.isKingAttackedAfterMove(blackKing, piece.getP(), p2)) {
                                haveMove = true;
                                break;
                            }
                        }
                        if (haveMove) break;
                    }
                    if (haveMove) break;
                }
                if (haveMove) break;
            }
            if (!haveMove && board.isAttacked(blackKing.getP(), PieceColor.BLACK)) {
                return PieceColor.WHITE;
            }
            if (haveMove) {
                return null;
            } else {
                return null; // stalemate
            }
        }
    }
    
    public void setSelectedPiece(Piece piece) {
        selectedPiece = piece;
    }

    public void setSelectedPieceAt(Position p) {
        selectedPiece = board.getPiece(p);
    }

    public ActionNeededEnum getNeededAction() {
        if (getWinner() != null) {
            return ActionNeededEnum.RESTART;
        } else if (pieceToTransform != null) {
            return ActionNeededEnum.PAWN_PROMOTION;
        } else {
            return ActionNeededEnum.MOVE;
        }
    }

    public void selectAt(Position p) {
        if (!board.isOnField(p)) {
            return;
        }
        if (selectedPiece == null) {
            Piece piece = board.getPiece(p);
            if (piece != null && piece.getColor() == turn) {
                selectedPiece = piece;
            }
        } else {
            this.makeTurn(selectedPiece.getP(), p);
            selectedPiece = null;

            Piece fromPiece = board.getPiece(p);
            if (fromPiece instanceof Pawn && ((Pawn) fromPiece).shouldPromote()) {
                pieceToTransform = fromPiece;
                pawnPromotionMenu = new ChooseMenu(new String[]{"Queen", "Knight", "Rook", "Bishop", "Back"});
            }
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

        Pawn oppositePawn = null;
        if (piece1 instanceof Pawn) {
            oppositePawn = ((Pawn) piece1).getEnPassantCapturedPawn(p2);
        }
        board.hardCapture(piece1, p2);
        if (piece1 instanceof Pawn) {
            if (oppositePawn != null) {
                board.hardReplace(oppositePawn.getP(), null);
            }
        }

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

    public void setStrAtSideBar(StringBuilder sb, int strRow, String str) {
        int remainingStrCols = PRINT_STR_COLS - board.MAX_COLS * CELL_WIDTH;
        for (int j = 0; j < Math.min(str.length(), remainingStrCols); j++) {
            sb.setCharAt(strRow * PRINT_STR_COLS + board.MAX_COLS * CELL_WIDTH +
                    j + 1, str.charAt(j)
            );
        }
    }

    public int getCellStartingPositionInSB(Position p) {
        int strRow = (board.MAX_ROWS - p.getX() - 1) * 2 + 1;
        return strRow * PRINT_STR_COLS + p.getY() * CELL_WIDTH + 1;
    }

    public void printWindow() {
        StringBuilder sb = new StringBuilder();

        sb.append((" ".repeat(PRINT_STR_COLS - 1) + "\n").repeat(PRINT_STR_ROWS));

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

        ActionNeededEnum neededAction = getNeededAction();

        if (neededAction == ActionNeededEnum.PAWN_PROMOTION) {
            if (pawnPromotionMenu != null) {
                String[] rowStrs = pawnPromotionMenu.toStrings(10);
                for (int i = 0; i < rowStrs.length; i++) {
                    setStrAtSideBar(sb, 9 + i, rowStrs[i]);
                }
            }
        } else if (neededAction == ActionNeededEnum.MOVE) {
            String turnDisplayStr = "Turn for " + (turn == PieceColor.WHITE ? "White" : "Black");
            setStrAtSideBar(sb, 8, turnDisplayStr);

            excelAt(sb, curPtr, '>', '<');
            if (selectedPiece != null) {
                excelAt(sb, selectedPiece.getP(), '|', '|');
            }
        } else if (neededAction == ActionNeededEnum.RESTART) {
            PieceColor winner = getWinner();
            String winnerDisplayStr = winner == null ? "Draw" : "Winner: " + (winner == PieceColor.WHITE ? "White" : "Black");
            setStrAtSideBar(sb, 8, winnerDisplayStr);
        }

        System.out.print("\n".repeat(PRINT_STR_ROWS));
        System.out.print(sb);
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

    public void rollbackTurn() {
        selectedPiece = null;
        // if pawn promotion was selected, undo the promotion
        board.undoHardReplace();

        if (!board.undoHardCapture()) {
            return;
        }
        if (turn == PieceColor.WHITE) {
            turn = PieceColor.BLACK;
        } else {
            turn = PieceColor.WHITE;
        }
    }

    public void pushPromMenuCursor(int dx) {
        if (pawnPromotionMenu == null) {
            return;
        }

        if (dx == 1) {
            pawnPromotionMenu.movePrevOption();
        } else if (dx == -1) {
            pawnPromotionMenu.moveNextOption();
        }
    }

    public int getPromMenuCursor() {
        return pawnPromotionMenu.getCurrentPtr();
    }

    public void selectPromMenu(int i) {
        if (pawnPromotionMenu == null) {
            return;
        }

        String option = pawnPromotionMenu.getCurrentOption();
        if (option.equals("Back")) {
            rollbackTurn();
        } else if (option.equals("Queen")) {
            board.hardReplace(
                    pieceToTransform.getP(),
                    new Queen(pieceToTransform.getColor(), pieceToTransform.getP(), pieceToTransform.getBoard())
            );
        } else if (option.equals("Knight")) {
            board.hardReplace(
                    pieceToTransform.getP(),
                    new Knight(pieceToTransform.getColor(), pieceToTransform.getP(), pieceToTransform.getBoard())
            );
        } else if (option.equals("Rook")) {
            board.hardReplace(
                    pieceToTransform.getP(),
                    new Rook(pieceToTransform.getColor(), pieceToTransform.getP(), pieceToTransform.getBoard())
            );
        } else if (option.equals("Bishop")) {
            board.hardReplace(
                    pieceToTransform.getP(),
                    new Bishop(pieceToTransform.getColor(), pieceToTransform.getP(), pieceToTransform.getBoard())
            );
        }
        pieceToTransform = null;
        pawnPromotionMenu = null;
    }
}
