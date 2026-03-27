package lab2.chess.app;

import lab2.chess.models.Board;
import lab2.chess.models.ChooseMenu;
import lab2.chess.models.pieces.PieceColor;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class App extends JFrame {
    ChessApi chessApi;
    AppState appState = AppState.MENU;
    ChooseMenu menu = new ChooseMenu(new String[]{"Play against bot", "Play 1v1", "Spectate bot play", "Exit"});
    private boolean whiteIsBot = false;
    private boolean blackIsBot = false;

    public App() {
        // Set up the invisible/blank window strictly for capturing input
        setTitle("Chess Game Input Window - Keep in focus!");
        setSize(0, 0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFocusable(true);
        requestFocusInWindow();
        printMenu();

        // Register the event listener
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int inputCode = e.getKeyCode();

                if (appState == AppState.MENU) {
                    handleMenuInput(inputCode);
                    printMenu();
                } else if (appState == AppState.GAMING) {
                    handleGameInput(inputCode);
                    chessApi.printWindow();
                }
            }
        });
    }

    private void handleMenuInput(int inputCode) {
        if (inputCode == KeyEvent.VK_ENTER) {
            String option = menu.getCurrentOption();
            if (option.equals("Play against bot")) {
                whiteIsBot = false;
                blackIsBot = true;
                appState = AppState.GAMING;
                chessApi = new ChessApi(new Board());
                chessApi.printWindow();
            } else if (option.equals("Play 1v1")) {
                whiteIsBot = false;
                blackIsBot = false;
                appState = AppState.GAMING;
                chessApi = new ChessApi(new Board());
                chessApi.printWindow();
            } else if (option.equals("Spectate bot play")) {
                whiteIsBot = true;
                blackIsBot = true;
                appState = AppState.GAMING;
                chessApi = new ChessApi(new Board());
                chessApi.printWindow();
            }
        } else if (inputCode == KeyEvent.VK_W) {
            menu.movePrevOption();
        } else if (inputCode == KeyEvent.VK_S) {
            menu.moveNextOption();
        }
    }

    private void handleGameInput(int inputCode) {
        int dx = 0, dy = 0;
        boolean cursorRelated = false;
        boolean selected = false;
        boolean pressedBackspace = false;
        boolean pressedBackslash = false;
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
        } else if (inputCode == KeyEvent.VK_BACK_SLASH) {
            pressedBackslash = true;
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
            } else if (pressedBackslash) {
                chessApi.undoRollbackTurn();
            }

            if (!pressedBackspace && !pressedBackslash) {
                chessApi.printWindow();
                if (whiteIsBot && chessApi.getTurnSide() == PieceColor.WHITE) {
                    chessApi.doBotTurn();
                } else if (blackIsBot && chessApi.getTurnSide() == PieceColor.BLACK) {
                    chessApi.doBotTurn();
                }
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
    }

    public void printMenu() {
        String[] toPrint = menu.toStrings(20);
        System.out.println("\n".repeat(toPrint.length));
        for (int i = 0; i < toPrint.length; i++) {
            System.out.println(toPrint[i]);
        }
    }
}
