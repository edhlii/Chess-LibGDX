package io.edhlii.chess.pieces;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import io.edhlii.chess.Board;
import io.edhlii.chess.Position;

public class Knight extends Piece {
    public Knight(SpriteBatch batch, ShapeRenderer shapeRenderer, Texture texture, Board board, PieceColor color, Position pos) {
        super(batch, shapeRenderer, texture, board, color, pos);
        this.type = PieceType.KNIGHT;
    }

    @Override
    public void calculateValidMove() {
        validMove.clear();
        int[] dx = {2, 1, -1, -2, -2, -1, 1, 2};
        int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};
        for (int i = 0; i < 8; ++i) {
            Position pos = new Position(currentPos.row + dx[i], currentPos.col + dy[i]);
            if (validPosition(pos)) validMove.add(pos);
        }
    }
}
