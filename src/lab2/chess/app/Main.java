package lab2.chess.app;

import javax.swing.*;

public class Main {
    /*
    Windows powershell commands:
tel     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            App window = new App();
            window.setVisible(true);
        });
    }
}
