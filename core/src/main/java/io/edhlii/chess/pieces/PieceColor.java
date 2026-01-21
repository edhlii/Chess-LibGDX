package io.edhlii.chess.pieces;

public enum PieceColor {
    WHITE, BLACK;

    public PieceColor opponent() {
        if (this == WHITE) return BLACK;
        return WHITE;
    }
}
