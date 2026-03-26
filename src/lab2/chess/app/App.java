package lab2.chess.app;

import lab2.chess.models.Board;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class App extends JFrame {
    ChessApi chessApi;
    AppState appState = AppState.GAMING;

    public App() {
        // Set up the invisible/blank window strictly for capturing input
        setTitle("Chess Game Input Window - Keep in focus!");
        setSize(0, 0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFocusable(true);
        requestFocusInWindow();

        ChessApi chessApi = new ChessApi(new Board());
        System.out.println("Turn: " + chessApi.getTurnSide());
        chessApi.printWindow();

        // Register the event listener
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int inputCode = e.getKeyCode();
                int dx = 0, dy = 0;
                boolean cursorRelated = false;
                boolean selected = false;
                boolean pressedBackspace = false;
                ActionNeededEnum actionNeeded = chessApi.getNeededAction();

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
                } else if (inputCode == KeyEvent.VK_BACK_SPACE) {
                    pressedBackspace = true;
                }

                if (actionNeeded == ActionNeededEnum.PAWN_PROMOTION) {
                    if (cursorRelated) {
                        chessApi.pushPromMenuCursor(dx);
                    } else if (selected) {
                        chessApi.selectPromMenu();
                    }
                } else if (actionNeeded == ActionNeededEnum.MOVE) {
                    if (cursorRelated) {
                        chessApi.pushCursor(dx, dy);
                    } else if (selected) {
                        chessApi.selectAt(chessApi.getCursor());
                    } else if (pressedBackspace) {
                        chessApi.rollbackTurn();
                    }
                }  else if (actionNeeded == ActionNeededEnum.RESTART) {
                    if (pressedBackspace) {
                        chessApi.rollbackTurn();
                    }
                    // else if (cursorRelated) {
                    //     chessApi.pushRestartMenuCursor(dx);
                    // } else if (selected) {
                    //     chessApi.selectRestartMenu(chessApi.getRestartMenuCursor());
                    // }
                }

                chessApi.printWindow();
            }
        });
    }
}
