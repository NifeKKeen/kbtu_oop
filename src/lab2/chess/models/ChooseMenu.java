package lab2.chess.models;

public class ChooseMenu {
    String[] options;
    int currentOptionIdx = 0;
    int leftPadding = 2;
    public ChooseMenu(String[] options) {
        this.options = options;
    }

    public void moveNextOption() {
        currentOptionIdx = (currentOptionIdx + 1) % options.length;
    }

    public void movePrevOption() {
        currentOptionIdx = (currentOptionIdx + options.length - 1) % options.length;
    }

    public String getCurrentOption() {
        return options[currentOptionIdx];
    }

    public String[] toStrings(int strCols) {
        int strRows = options.length;
        StringBuilder[] sbs = new StringBuilder[strRows];

        for (int i = 0; i < options.length; i++) {
            sbs[i] = new StringBuilder();
            sbs[i].append(" ".repeat(strCols));
            for (int j = 0; j < Math.min(options[i].length(), strCols - leftPadding); j++) {
                sbs[i].setCharAt(j + leftPadding, options[i].charAt(j));
            }
        }

        sbs[currentOptionIdx].setCharAt(0, '>');

        String[] res = new String[strRows];
        for (int i = 0; i < strRows; i++) {
            res[i] = sbs[i].toString();
        }
        return res;
    }

    public int getCurrentPtr() {
        return currentOptionIdx;
    }
}
