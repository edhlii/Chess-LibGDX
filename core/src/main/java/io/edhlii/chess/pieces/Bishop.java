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
        this.type = PieceType.BISHOP;
    }

    @Override
    public void calculateValidMove() {
        validMove.clear();
        calculateBishopValidMove();
    }
}
