package io.edhlii.chess.pieces;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import io.edhlii.chess.Board;
import io.edhlii.chess.ChessGame;
import io.edhlii.chess.Position;

public class Rook extends Piece {
    public Rook(SpriteBatch batch, ShapeRenderer shapeRenderer, Texture texture, Board board, PieceColor color, Position pos) {
        super(batch, shapeRenderer, texture, board, color, pos);
    }

    @Override
    public void calculateValidMove() {
        validMove.clear();
        for (int row = currentPos.row + 1; row < ChessGame.BOARD_SIZE; ++row) {
            Position pos = new Position(row, currentPos.col);
            Piece piece = board.getPieceAt(pos);
            if (piece != null) {
                if (piece.getColor() == this.color.opponent()) validMove.add(pos);
                break;
            }
            validMove.add(pos);
        }
        for (int row = currentPos.row - 1; row >= 0; --row) {
            Position pos = new Position(row, currentPos.col);
            Piece piece = board.getPieceAt(pos);
            if (piece != null) {
                if (piece.getColor() == this.color.opponent()) validMove.add(pos);
                break;
            }
            validMove.add(pos);
        }
        for (int col = currentPos.col - 1; col >= 0; --col) {
            Position pos = new Position(currentPos.row, col);
            Piece piece = board.getPieceAt(pos);
            if (piece != null) {
                if (piece.getColor() == this.color.opponent()) validMove.add(pos);
                break;
            }
            validMove.add(pos);
        }
        for (int col = currentPos.col + 1; col < ChessGame.BOARD_SIZE; ++col) {
            Position pos = new Position(currentPos.row, col);
            Piece piece = board.getPieceAt(pos);
            if (piece != null) {
                if (piece.getColor() == this.color.opponent()) validMove.add(pos);
                break;
            }
            validMove.add(pos);
        }
    }
}
