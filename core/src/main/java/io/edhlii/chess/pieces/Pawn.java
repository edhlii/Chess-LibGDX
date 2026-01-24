package io.edhlii.chess.pieces;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import io.edhlii.chess.Board;
import io.edhlii.chess.Position;

public class Pawn extends Piece {
    private final int offset;

    public Pawn(SpriteBatch batch, ShapeRenderer shapeRenderer, Texture texture, Board board, PieceColor color, Position pos) {
        super(batch, shapeRenderer, texture, board, color, pos);
        this.type = PieceType.PAWN;
        offset = (this.color == PieceColor.WHITE) ? 1 : -1;
    }

    public int getOffset() {
        return offset;
    }

    @Override
    public void calculateValidMove() {
        validMove.clear();
        Position pos;
        if (!hasMoved) {
            pos = new Position(currentPos.row + offset, currentPos.col);
            if (validPosition(pos) && board.getPieceAt(pos) == null) {
                validMove.add(pos);
                pos = new Position(currentPos.row + offset * 2, currentPos.col);
                if (validPosition(pos) && board.getPieceAt(pos) == null) validMove.add(pos);
            }
        } else {
            pos = new Position(currentPos.row + offset, currentPos.col);
            if (validPosition(pos) && board.getPieceAt(pos) == null) {
                validMove.add(pos);
            }
        }
        pos = new Position(currentPos.row + offset, currentPos.col + 1);
        if (checkCaptureMove(pos)) validMove.add(pos);
        pos = new Position(currentPos.row + offset, currentPos.col - 1);
        if (checkCaptureMove(pos)) validMove.add(pos);

        // Check En Passant
        if (board.pawnVulnerableToEnPassant != null) {
            pos = board.pawnVulnerableToEnPassant.getCurrentPos();
            if (pos.row == this.currentPos.row && Math.abs(pos.col - this.currentPos.col) == 1) {
                validMove.add(new Position(pos.row + offset, pos.col));
            }
        }
    }

    private boolean checkCaptureMove(Position pos) {
        if (pos.isOutOfBoard()) return false;
        Piece piece = board.getPieceAt(pos);
        if (piece == null) return false;
        if (piece.getColor() != color.opponent()) return false;
        return true;
    }
}
