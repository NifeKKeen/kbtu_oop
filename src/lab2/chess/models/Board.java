package lab2.chess.models;

import lab2.chess.models.pieces.*;

public class Board {
    // 0 indexed
    private Piece[][] field = new Piece[8][8];

    public Board() {
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

    public void hardPlacePiece(Piece piece, Position p2) {
        if (field[p2.getX()][p2.getY()] != null) {
            Piece piece2 = field[p2.getX()][p2.getY()];
            piece2.removeFromBoard();
        }

        field[p2.getX()][p2.getY()] = piece;
    }

    public Piece getPiece(Position p) {
        return field[p.getX()][p.getY()];
    }

    public boolean isOnField(Position p) {
        return 0 <= p.getX() && p.getX() < 8 && 0 <= p.getY() && p.getY() < 8;
    }
}
