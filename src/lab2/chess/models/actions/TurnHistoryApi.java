package lab2.chess.models.actions;

import lab2.chess.models.Board;

import java.util.ArrayList;

public class TurnHistoryApi {
    private final ArrayList<Turn> turns = new ArrayList<>();
    private final Board board;
    public TurnHistoryApi(Board board) {
        this.board = board;
    }

    public void addTurn(Turn turn) {
        turns.add(turn);
    }

    public ArrayList<Turn> getTurns() {
        return turns;
    }

    public Turn undoTurn() {
        if (turns.isEmpty()) {
            return null;
        }
        Turn lastTurn = turns.getLast();

        for (int i = lastTurn.getActions().size() - 1; i >= 0; i--) {
            ChessAction action = lastTurn.getActions().get(i);
            board.undoAction();
        }

        turns.removeLast();
        return lastTurn;
    }

    public Turn getLastTurn() {
        return turns.isEmpty() ? null : turns.getLast();
    }
}
