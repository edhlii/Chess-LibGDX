package io.edhlii.chess;

import java.util.Objects;

public class Position {
    public int row, col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public boolean isOutOfBoard() {
        return row < 0 || row >= ChessGame.BOARD_SIZE || col < 0 || col >= ChessGame.BOARD_SIZE;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row && col == position.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
