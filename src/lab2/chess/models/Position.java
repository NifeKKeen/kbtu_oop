package lab2.chess.models;

public class Position {
    private int x, y;
    public Position(Position p) {
        this.x = p.getX();
        this.y = p.getY();
    }
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void addX(int x) {
        this.x += x;
    }
    public void addY(int y) {
        this.y += y;
    }
}
