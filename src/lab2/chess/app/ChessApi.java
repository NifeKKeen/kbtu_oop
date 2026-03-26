package lab2.chess.app;

import lab2.chess.models.*;
import lab2.chess.models.actions.Turn;
import lab2.chess.models.actions.TurnHistoryApi;
import lab2.chess.models.pieces.*;

enum ActionNeededEnum {
    NONE,
    PAWN_PROMOTION,
    MOVE,
    RESTART,
}

public class ChessApi {
    private final Board board;
    private PieceColor turnSide;
    private final Position curPtr = new Position(0, 0);
    private Piece selectedPiece = null;
    private TurnHistoryApi turnHistoryApi;

    final int PRINT_STR_ROWS, PRINT_STR_COLS;
    static final int CELL_WIDTH = 3;

    private Piece pieceToTransform = null;
    private ChooseMenu pawnPromotionMenu = null;

    public ChessApi(Board board) {
        this(board, PieceColor.WHITE);
    }

    public ChessApi(Board board, PieceColor turn) {
        turnHistoryApi = new TurnHistoryApi(board);
        this.board = board;
        this.turnSide = turn;
        PRINT_STR_ROWS = board.MAX_ROWS * 2 + 1;
        PRINT_STR_COLS = board.MAX_COLS * CELL_WIDTH + 15 + 1;
    }

    private void switchColor() {
        if (turnSide == PieceColor.WHITE) {
            turnSide = PieceColor.BLACK;
        } else {
            turnSide = PieceColor.WHITE;
        }
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
        if (turnSide == PieceColor.WHITE) {
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
            if (piece != null && piece.getColor() == turnSide) {
                selectedPiece = piece;
            }
        } else {
            this.tryTurn(selectedPiece.getP(), p);
            selectedPiece = null;

            Piece fromPiece = board.getPiece(p);
            if (fromPiece instanceof Pawn && ((Pawn) fromPiece).shouldPromote()) {
                pieceToTransform = fromPiece;
                pawnPromotionMenu = new ChooseMenu(new String[]{"Queen", "Knight", "Rook", "Bishop", "Back"});
            }
        }
    }

    private Turn runningTurn = null;
    public boolean tryTurn(Position p1, Position p2) {
        if (runningTurn != null) {
            throw new RuntimeException("Turn already running");
        }

        if (getWinner() != null) {
            return false;
        }
        
        Piece piece1 = board.getPiece(p1);

        if (piece1 == null) {
            return false;
        }

        if (piece1.getColor() != turnSide) {
            return false;
        }

        Piece piece2 = board.getPiece(p2);

        // handle castling
        // WARNING: THERE ARE VERY HARDCODED RULES
        if (
                piece1 instanceof King && piece2 instanceof Rook &&
                piece1.getColor() == piece2.getColor()
        ) {
            King king1 = (King)board.getPiece(p1);
            Rook rook2 = (Rook)board.getPiece(p2);
            Turn lastTurn = turnHistoryApi.getLastTurn();

            if (turnSide == PieceColor.WHITE) {
                if (lastTurn != null && (lastTurn._whiteKingMoved || lastTurn._whiteRooksMoved.contains(rook2))) {
                    return false;
                }
                if (p2.getY() == 0) {
                    for (int j = 1; j < 4; j++) {
                        if (board.getPiece(new Position(p1.getX(), j)) != null) {
                            System.out.println(j);
                            return false;
                        }
                    }
                    for (int j = 2; j <= 4; j++) {
                        if (board.isAttacked(new Position(p1.getX(), j), turnSide)) {
                            System.out.println(j);
                            return false;
                        }
                    }

                    runningTurn = new Turn(turnHistoryApi.getLastTurn(), turnSide);
                    runningTurn.addAction(board.hardCapture(king1, new Position(0, 2)));
                    runningTurn.addAction(board.hardCapture(rook2, new Position(0, 3)));
                    runningTurn.setComplete();
                    turnHistoryApi.addTurn(runningTurn);
                    runningTurn = null;
                    switchColor();
                    return true;
                } else if (p2.getY() == 7) {
                    for (int j = 6; j >= 5; j--) {
                        if (board.getPiece(new Position(p1.getX(), j)) != null) {
                            return false;
                        }
                    }

                    for (int j = 6; j >= 4; j--) {
                        if (board.isAttacked(new Position(p1.getX(), j), turnSide)) {
                            return false;
                        }
                    }
                    runningTurn = new Turn(turnHistoryApi.getLastTurn(), turnSide);
                    runningTurn.addAction(board.hardCapture(king1, new Position(0, 6)));
                    runningTurn.addAction(board.hardCapture(rook2, new Position(0, 5)));
                    runningTurn.setComplete();
                    turnHistoryApi.addTurn(runningTurn);
                    runningTurn = null;
                    switchColor();
                    return true;
                }
            } else {
                if (lastTurn != null && (lastTurn._blackKingMoved || lastTurn._blackRooksMoved.contains(rook2))) {
                    return false;
                }
                if (p2.getY() == 0) {
                    for (int j = 1; j < 4; j++) {
                        if (board.getPiece(new Position(p1.getX(), j)) != null) {
                            return false;
                        }
                    }
                    for (int j = 2; j <= 4; j++) {
                        if (board.isAttacked(new Position(p1.getX(), j), turnSide)) {
                            return false;
                        }
                    }

                    runningTurn = new Turn(turnHistoryApi.getLastTurn(), turnSide);
                    runningTurn.addAction(board.hardCapture(king1, new Position(7, 2)));
                    runningTurn.addAction(board.hardCapture(rook2, new Position(7, 3)));
                    runningTurn.setComplete();
                    turnHistoryApi.addTurn(runningTurn);
                    runningTurn = null;
                    switchColor();
                    return true;
                } else if (p2.getY() == 7) {
                    for (int j = 6; j >= 5; j--) {
                        if (board.getPiece(new Position(p1.getX(), j)) != null) {
                            return false;
                        }
                    }

                    for (int j = 6; j >= 4; j--) {
                        if (board.isAttacked(new Position(p1.getX(), j), turnSide)) {
                            return false;
                        }
                    }
                    runningTurn = new Turn(turnHistoryApi.getLastTurn(), turnSide);
                    runningTurn.addAction(board.hardCapture(king1, new Position(7, 6)));
                    runningTurn.addAction(board.hardCapture(rook2, new Position(7, 5)));
                    runningTurn.setComplete();
                    turnHistoryApi.addTurn(runningTurn);
                    runningTurn = null;
                    switchColor();
                    return true;
                }
            }

            return false;
        }

        if (!piece1.isLegalMove(p2)) {
            return false;
        }
        
        King turnSideKing = board.getAnyKing(turnSide);
        if (board.isKingAttackedAfterMove(turnSideKing, p1, p2)) {
            return false;
        }

        runningTurn = new Turn(turnHistoryApi.getLastTurn(), turnSide);

        // handling en passant
        if (piece1 instanceof Pawn) {
            Pawn siblingOppositePawn = ((Pawn) piece1).getEnPassantCapturedPawn(p2);
            if (siblingOppositePawn != null) {
                runningTurn.addAction(board.hardReplace(siblingOppositePawn.getP(), null));
            }
        }
        runningTurn.addAction(board.hardCapture(piece1, p2));

        System.out.println(piece1 instanceof Pawn && ((Pawn) piece1).shouldPromote());
        if (piece1 instanceof Pawn && ((Pawn) piece1).shouldPromote()) {

        } else {
            runningTurn.setComplete();
            turnHistoryApi.addTurn(runningTurn);
            runningTurn = null;
            switchColor();
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
            String turnDisplayStr = "Turn for " + (turnSide == PieceColor.WHITE ? "White" : "Black");
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

    public PieceColor getTurnSide() {
        return turnSide;
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

    public Turn rollbackTurn() {
        selectedPiece = null;

        Turn lastTurn = turnHistoryApi.undoTurn();
        if (lastTurn == null) {
            return null;
        }

        if (lastTurn.isComplete()) {
            switchColor();
        }
        return lastTurn;
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

    public void selectPromMenu() {
        if (pawnPromotionMenu == null) {
            return;
        }

        String option = pawnPromotionMenu.getCurrentOption();
        if (option.equals("Back")) {
            turnHistoryApi.addTurn(runningTurn);
            rollbackTurn();
            runningTurn = null;
        } else if (option.equals("Queen")) {
            runningTurn.addAction(
                board.hardReplace(
                        pieceToTransform.getP(),
                        new Queen(pieceToTransform.getColor(), pieceToTransform.getP(), pieceToTransform.getBoard())
                )
            );
            runningTurn.setComplete();
            turnHistoryApi.addTurn(runningTurn);
            runningTurn = null;
            switchColor();
        } else if (option.equals("Knight")) {
            runningTurn.addAction(
                board.hardReplace(
                        pieceToTransform.getP(),
                        new Knight(pieceToTransform.getColor(), pieceToTransform.getP(), pieceToTransform.getBoard())
                )
            );
            runningTurn.setComplete();
            turnHistoryApi.addTurn(runningTurn);
            runningTurn = null;
            switchColor();
        } else if (option.equals("Rook")) {
            runningTurn.addAction(
                board.hardReplace(
                        pieceToTransform.getP(),
                        new Rook(pieceToTransform.getColor(), pieceToTransform.getP(), pieceToTransform.getBoard())
                )
            );
            runningTurn.setComplete();
            turnHistoryApi.addTurn(runningTurn);
            runningTurn = null;
            switchColor();
        } else if (option.equals("Bishop")) {
            runningTurn.addAction(
                board.hardReplace(
                        pieceToTransform.getP(),
                        new Bishop(pieceToTransform.getColor(), pieceToTransform.getP(), pieceToTransform.getBoard())
                )
            );
            runningTurn.setComplete();
            turnHistoryApi.addTurn(runningTurn);
            runningTurn = null;
            switchColor();
        }
        pieceToTransform = null;
        pawnPromotionMenu = null;
    }
}
