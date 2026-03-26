package lab2.chess.models.engine;

import lab2.chess.app.ChessApi;
import lab2.chess.models.Board;
import lab2.chess.models.Position;
import lab2.chess.models.pieces.Piece;
import lab2.chess.models.pieces.PieceColor;

import java.util.ArrayList;
import java.util.List;

public class ChessEngine {

    public static class Move {
        public final Position from;
        public final Position to;

        public Move(Position from, Position to) {
            this.from = from;
            this.to = to;
        }
    }

    /**
     * Finds the best move using a basic Minimax algorithm.
     */
    public static Move findBestMove(ChessApi chessApi, Board board, int depth) {
        PieceColor turnColor = chessApi.getTurnSide();
        List<Move> pseudoLegalMoves = getAllPseudoLegalMoves(board, turnColor);

        Move bestMove = null;
        int bestScore = Integer.MIN_VALUE;

        for (Move move : pseudoLegalMoves) {
            // Passing 'true' to auto-promote to Queen during traversal
            if (chessApi.tryTurn(move.from, move.to, true)) {
                int score = minimax(chessApi, board, depth - 1, false, turnColor);

                chessApi.rollbackTurn();

                if (score > bestScore || bestMove == null) {
                    bestScore = score;
                    bestMove = move;
                }
            }
        }
        return bestMove;
    }

    /**
     * Minimax recursive tree evaluation.
     */
    private static int minimax(ChessApi chessApi, Board board, int depth, boolean isMaximizing, PieceColor botColor) {
        if (depth == 0 || chessApi.getWinner() != null) {
            return evaluateBoard(board, botColor);
        }

        PieceColor currentTurnColor = chessApi.getTurnSide();
        List<Move> pseudoLegalMoves = getAllPseudoLegalMoves(board, currentTurnColor);

        if (isMaximizing) {
            int maxEval = Integer.MIN_VALUE;
            for (Move move : pseudoLegalMoves) {
                if (chessApi.tryTurn(move.from, move.to, true)) {
                    int eval = minimax(chessApi, board, depth - 1, false, botColor);
                    chessApi.rollbackTurn();
                    maxEval = Math.max(maxEval, eval);
                }
            }
            return maxEval == Integer.MIN_VALUE ? evaluateBoard(board, botColor) : maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (Move move : pseudoLegalMoves) {
                if (chessApi.tryTurn(move.from, move.to, true)) {
                    int eval = minimax(chessApi, board, depth - 1, true, botColor);
                    chessApi.rollbackTurn();
                    minEval = Math.min(minEval, eval);
                }
            }
            return minEval == Integer.MAX_VALUE ? evaluateBoard(board, botColor) : minEval;
        }
    }

    /**
     * Generates all pseudo-legal moves for a given color.
     */
    private static List<Move> getAllPseudoLegalMoves(Board board, PieceColor color) {
        List<Move> moves = new ArrayList<>();
        for (int r = 0; r < board.MAX_ROWS; r++) {
            for (int c = 0; c < board.MAX_COLS; c++) {
                Position from = new Position(r, c);
                Piece piece = board.getPiece(from);

                if (piece != null && piece.getColor() == color) {
                    for (int tr = 0; tr < board.MAX_ROWS; tr++) {
                        for (int tc = 0; tc < board.MAX_COLS; tc++) {
                            Position to = new Position(tr, tc);
                            if (piece.isLegalMove(to)) {
                                moves.add(new Move(from, to));
                            }
                        }
                    }
                }
            }
        }
        return moves;
    }

    /**
     * Simple material evaluation.
     * Positive score implies advantage for the botColor.
     */
    private static int evaluateBoard(Board board, PieceColor botColor) {
        int score = 0;
        for (int r = 0; r < board.MAX_ROWS; r++) {
            for (int c = 0; c < board.MAX_COLS; c++) {
                Piece piece = board.getPiece(new Position(r, c));
                if (piece != null) {
                    int value = getPieceValue(piece);
                    if (piece.getColor() == botColor) {
                        score += value;
                    } else {
                        score -= value;
                    }
                }
            }
        }
        return score;
    }

    private static int getPieceValue(Piece piece) {
        String name = piece.getClass().getSimpleName();
        return switch (name) {
            case "Pawn" -> 10;
            case "Knight", "Bishop" -> 30;
            case "Kanich" -> 40;
            case "Rook" -> 50;
            case "Queen" -> 90;
            case "King" -> 900;
            default -> 0;
        };
    }
}