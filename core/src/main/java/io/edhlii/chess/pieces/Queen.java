package io.edhlii.chess.pieces;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import io.edhlii.chess.Board;
import io.edhlii.chess.ChessGame;
import io.edhlii.chess.Position;

public class Queen extends Piece {
    public Queen(SpriteBatch batch, ShapeRenderer shapeRenderer, Texture texture, Board board, PieceColor color, Position pos) {
        super(batch, shapeRenderer, texture, board, color, pos);
        this.type = PieceType.QUEEN;
    }

    @Override
    public void calculateValidMove() {
        validMove.clear();
        calculateRookValidMove();
        calculateBishopValidMove();
    }
}
