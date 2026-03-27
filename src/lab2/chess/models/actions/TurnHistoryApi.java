package lab2.chess.models.actions;

import lab2.chess.models.Board;

import java.util.ArrayList;

public class TurnHistoryApi {
    private final ArrayList<Turn> turns = new ArrayList<>();
    private final Board board;
    private int it = 0;
    public TurnHistoryApi(Board board) {
        this.board = board;
    }

    public void addTurn(Turn turn) {
        if (it >= turns.size()) {
            turns.add(turn);
        } else {
            turns.set(it, turn);
        }
        it++;
    }

    public Turn undoTurn() {
        Turn lastTurn = getLastTurn();
        if (lastTurn == null) {
            return null;
        }

        for (int i = lastTurn.getActions().size() - 1; i >= 0; i--) {
            ChessAction action = lastTurn.getActions().get(i);
            board.undoAction();
        }

        it--;
        return lastTurn;
    }

    public Turn redoTurn() {
        if (it >= turns.size()) {
            return null;
        }

        Turn nxtTurn = turns.get(it);
        Turn copyNxtTurn = new Turn(nxtTurn.prevTurn, nxtTurn.side);
        for (int i = 0; i < nxtTurn.getActions().size(); i++) {
            ChessAction action = nxtTurn.getActions().get(i);
            copyNxtTurn.addAction(board.doAction(action));
        }

        turns.set(it, copyNxtTurn);
        it++;
        return copyNxtTurn;
    }

    public Turn getLastTurn() {
        if (it == 0) {
            return null;
        } else {
            return turns.get(it - 1);
        }
    }
}
