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
        if (!hasMoved) {
            Position targetPos = new Position(currentPos.row + offset, currentPos.col);
//            Position tempPos = new Position(targetPos.row, targetPos.col);
            Piece targetPiece = board.getPieceAt(targetPos);
            if (targetPiece == null) {
                validMove.add(new Position(targetPos.row, targetPos.col));
                targetPos.row += offset;
                targetPiece = board.getPieceAt(targetPos);
                if (targetPiece == null || targetPiece.getColor() == color.opponent()) {
                    validMove.add(new Position(targetPos.row, targetPos.col));
                }
            } else if (targetPiece.getColor() == color.opponent()) {
                validMove.add(targetPos);
            }
        } else {
            Position targetPos = new Position(currentPos.row + offset, currentPos.col);
            Piece targetPiece = board.getPieceAt(targetPos);
            if (targetPiece == null) {
                validMove.add(new Position(targetPos.row, targetPos.col));
            } else if (targetPiece.getColor() == color.opponent()) {
                validMove.add(targetPos);
            }

        }
    }
}
