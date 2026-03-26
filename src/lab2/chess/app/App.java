package lab2.chess.app;

import lab2.chess.models.Board;
import lab2.chess.models.Position;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class App extends JFrame {
    ChessApi chessApi;
    Position prevSelectedP = null;

    public App() {
        // Set up the invisible/blank window strictly for capturing input
        setTitle("Chess Game Input Window - Keep in focus!");
        setSize(0, 0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFocusable(true);
        requestFocusInWindow();

        ChessApi chessApi = new ChessApi(new Board());

        // Register the event listener
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("Turn: " + chessApi.getTurn());
                int inputCode = e.getKeyCode();
                int dx = 0, dy = 0;
                boolean cursorRelated = false;
                boolean selected = false;

                if (inputCode == KeyEvent.VK_W) {
                    dx = 1;
                    cursorRelated = true;
                } else if (inputCode == KeyEvent.VK_S) {
                    dx = -1;
                    cursorRelated = true;
                } else if (inputCode == KeyEvent.VK_A) {
                    dy = -1;
                    cursorRelated = true;
                } else if (inputCode == KeyEvent.VK_D) {
                    dy = 1;
                    cursorRelated = true;
                } else if (inputCode == KeyEvent.VK_X || inputCode == KeyEvent.VK_SPACE) {
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

                chessApi.printBoard();
            }
        });
    }
}
