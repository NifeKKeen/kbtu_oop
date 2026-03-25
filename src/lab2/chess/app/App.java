package lab2.chess.app;

import lab2.chess.models.Board;
import lab2.chess.models.Position;

import java.util.Scanner;

public class App {
    public AppState state = AppState.GAMING;

    public void start() {
        Scanner sc = new Scanner(System.in);
        ChessApi chessApi = new ChessApi(new Board());
        Position prevSelectedP = null;
        while (true) {
            if (state == AppState.GAMING) {
                chessApi.printBoard();

                System.out.println("Turn: " + chessApi.getTurn());
                System.out.println("Enter direction");
                String input = sc.next();
                int dx = 0, dy = 0;
                boolean cursorRelated = false;
                boolean selected = false;
                if (input.equals("w")) {
                    dx = 1;
                    cursorRelated = true;
                } else if (input.equals("s")) {
                    dx = -1;
                    cursorRelated = true;
                } else if (input.equals("a")) {
                    dy = -1;
                    cursorRelated = true;
                } else if (input.equals("d")) {
                    dy = 1;
                    cursorRelated = true;
                } else if (input.equals("x")) {
                    selected = true;
                }

                if (cursorRelated) {
                    chessApi.pushCursor(dx, dy);
                } else if (selected) {
                    if (prevSelectedP == null) {
                        prevSelectedP = new Position(chessApi.getCursor());
                    } else {
                        chessApi.makeTurn(prevSelectedP, chessApi.getCursor());
                        prevSelectedP = null;
                    }
                }
            }
        }
    }
}
