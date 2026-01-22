package io.edhlii.chess.pieces;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import io.edhlii.chess.Board;
import io.edhlii.chess.ChessGame;
import io.edhlii.chess.Position;

public class Bishop extends Piece {
    public Bishop(SpriteBatch batch, ShapeRenderer shapeRenderer, Texture texture, Board board, PieceColor color, Position pos) {
        super(batch, shapeRenderer, texture, board, color, pos);
    }

    @Override
    public void calculateValidMove() {
        validMove.clear();
        for (int row = currentPos.row + 1, col = currentPos.col + 1; ; ++row, ++col) {
            Position pos = new Position(row, col);
            if (pos.isOutOfBoard()) break;
            if (validPosition(pos)) validMove.add(pos);
        }
        for (int row = currentPos.row - 1, col = currentPos.col - 1; ; --row, --col) {
            Position pos = new Position(row, col);
            if (pos.isOutOfBoard()) break;
            if (validPosition(pos)) validMove.add(pos);
        }
        for (int row = currentPos.row - 1, col = currentPos.col + 1; ; --row, ++col) {
            Position pos = new Position(row, col);
            if (pos.isOutOfBoard()) break;
            if (validPosition(pos)) validMove.add(pos);
        }
        for (int row = currentPos.row + 1, col = currentPos.col - 1; ; ++row, --col) {
            Position pos = new Position(row, col);
            if (pos.isOutOfBoard()) break;
            if (validPosition(pos)) validMove.add(pos);
        }
    }
}
