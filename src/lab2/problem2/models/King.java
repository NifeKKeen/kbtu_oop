package lab2.problem2.models;

public class King extends Piece {
    static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1}, dy = {1, 1, 0, -1, -1, -1, 0, 1};
    public King(Position p, PieceColor color) {
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
