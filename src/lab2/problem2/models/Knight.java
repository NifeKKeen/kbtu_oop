package lab2.problem2.models;

public class Knight extends Piece {
    static int[] dx = {1, 2, 2, 1, -1, -2, -2, -1}, dy = {2, 1, -1, -2, -2, -1, 1, 2};
    public Knight(Position p, PieceColor color) {
        super(p, color);
    }

    @Override
    boolean isLegalMove(Position p2) {
        if (isSamePosition(p2))
            return false;

        for (int i = 0; i < 8; i++) {
            if (p.getX() + dx[i] == p2.getX() && p.getY() + dy[i] == p2.getY()) {
                return true;
            }
        }
        return false;
    }
}
