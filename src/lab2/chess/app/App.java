package lab2.chess.app;

import lab2.chess.models.Board;
import lab2.chess.models.Position;

import java.util.Scanner;

public class App {
    public AppState state = AppState.GAMING;

    public void start() {
        Scanner sc = new Scanner(System.in);
        ChessApi chessApi = new ChessApi(new Board());
        while (true) {
            if (state == AppState.GAMING) {
                chessApi.printBoard();

                System.out.println("Turn: " + chessApi.getTurn());
                System.out.println("Enter starting and ending position: ");
                Position p1 = new Position(sc.nextInt(), sc.nextInt());
                Position p2 = new Position(sc.nextInt(), sc.nextInt());

                if (chessApi.makeTurn(p1, p2)) {
                    System.out.println("Turn is over");
                } else {
                    System.out.println("Invalid move");
                }
            }
        }
    }
}
