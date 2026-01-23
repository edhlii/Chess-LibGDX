package io.edhlii.chess.pieces;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import io.edhlii.chess.Board;
import io.edhlii.chess.Position;

public class Pawn extends Piece {
    public Pawn(SpriteBatch batch, ShapeRenderer shapeRenderer, Texture texture, Board board, PieceColor color, Position pos) {
        super(batch, shapeRenderer, texture, board, color, pos);
    }

    @Override
    public void calculateValidMove() {
        validMove.clear();
        int offset;
        if (color == PieceColor.WHITE) offset = 1;
        else offset = -1;
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
    }

    private boolean checkCaptureMove(Position pos) {
        if (pos.isOutOfBoard()) return false;
        Piece piece = board.getPieceAt(pos);
        if (piece == null) return false;
        if (piece.getColor() != color.opponent()) return false;
        return true;
    }
}
