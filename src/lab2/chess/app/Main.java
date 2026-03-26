package lab2.chess.app;

import javax.swing.*;

public class Main {
    /*
    Windows powershell commands:
    chcp 65001
    [Console]::OutputEncoding = [System.Text.Encoding]::UTF8
    java Main.java
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            App window = new App();
            window.setVisible(true);
        });
    }
}
