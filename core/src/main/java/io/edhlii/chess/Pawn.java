package io.edhlii.chess;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

public class Pawn extends Piece {
    private boolean hasMoved;

    Pawn(SpriteBatch batch, ShapeRenderer shapeRenderer, Texture texture, Board board, PieceColor color, Position pos) {
        super(batch, shapeRenderer, texture, board, color, pos);
        hasMoved = false;
    }

    public boolean isHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    @Override
    public void calculateValidMove() {
        if (!hasMoved) {
            Position targetPos = new Position(currentPos.row + 1, currentPos.col);
            Position tempPos = new Position(targetPos.row, targetPos.col);
            Piece targetPiece = board.getPieceAt(targetPos);
            if (targetPiece == null) {
                validMove.add(new Position(targetPos.row, targetPos.col));
                targetPos.row++;
                targetPiece = board.getPieceAt(targetPos);
                if (targetPiece == null || targetPiece.getColor() == color.opponent()) {
                    validMove.add(new Position(targetPos.row, targetPos.col));
                }
            } else if (targetPiece.getColor() == color.opponent()) {
                validMove.add(targetPos);
            }
        }
    }
}
