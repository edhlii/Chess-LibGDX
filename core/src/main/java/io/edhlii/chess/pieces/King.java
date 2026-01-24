package io.edhlii.chess.pieces;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import io.edhlii.chess.Board;
import io.edhlii.chess.Position;

public class King extends Piece {
    public King(SpriteBatch batch, ShapeRenderer shapeRenderer, Texture texture, Board board, PieceColor color, Position pos) {
        super(batch, shapeRenderer, texture, board, color, pos);
        this.type = PieceType.KING;
    }

    @Override
    public void calculateValidMove() {
        validMove.clear();
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {1, 0, -1, 1, -1, 1, 0, -1};
        for (int i = 0; i < 8; ++i) {
            Position pos = new Position(currentPos.row + dx[i], currentPos.col + dy[i]);
            if (validPosition(pos)) validMove.add(pos);
        }
    }
}
