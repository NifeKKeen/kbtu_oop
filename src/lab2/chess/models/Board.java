package lab2.chess.models;

import lab2.chess.models.pieces.*;

public class Board {
    // 0 indexed
    private Piece[][] field;
    public final int MAX_ROWS, MAX_COLS;

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
        field[0][2] = new Bishop(PieceColor.WHITE, new Position(0, 2), this);
        field[0][5] = new Bishop(PieceColor.WHITE, new Position(0, 5), this);
        field[7][2] = new Bishop(PieceColor.BLACK, new Position(7, 2), this);
        field[7][5] = new Bishop(PieceColor.BLACK, new Position(7, 5), this);

        // Queens
        field[0][3] = new Queen(PieceColor.WHITE, new Position(0, 3), this);
        field[7][3] = new Queen(PieceColor.BLACK, new Position(7, 3), this);

        // Kings
        field[0][4] = new King(PieceColor.WHITE, new Position(0, 4), this);
        field[7][4] = new King(PieceColor.BLACK, new Position(7, 4), this);
    }

    public Board(int mxRows, int mxCols) {
        this.MAX_ROWS = mxRows;
        this.MAX_COLS = mxRows;
        field = new Piece[mxRows][mxCols];
    }

    public void hardPlacePiece(Piece piece, Position p2) {
        if (piece.getBoard() != this) {
            throw new IllegalArgumentException("Piece is not on this board");
        }

        if (!isOnField(p2)) {
            throw new IllegalArgumentException("Position is out of bounds");
        }

        if (field[p2.getX()][p2.getY()] != null) {
            Piece piece2 = field[p2.getX()][p2.getY()];
            piece2.removeFromBoard();
        }

        field[piece.getP().getX()][piece.getP().getY()] = null;
        field[p2.getX()][p2.getY()] = piece;
        piece.setP(p2);
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
}
