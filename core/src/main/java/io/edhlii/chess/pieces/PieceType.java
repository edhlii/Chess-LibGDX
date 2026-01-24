package io.edhlii.chess.pieces;

public enum PieceType {
    PAWN("Pawn", 1),
    ROOK("Rook", 5),
    KNIGHT("Knight", 3),
    BISHOP("Bishop", 3),
    QUEEN("Queen", 9),
    KING("King", Integer.MAX_VALUE);

    private final String displayName;
    private final int value;

    PieceType(String displayName, int value) {
        this.displayName = displayName;
        this.value = value;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
