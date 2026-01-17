package io.edhlii.chess;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

public class Board {
    private ArrayList<Piece> pieces;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;

    Board(ShapeRenderer shapeRenderer, SpriteBatch batch) {
        this.shapeRenderer = shapeRenderer;
        this.batch = batch;
        this.pieces = new ArrayList<>();
    }

    public void addPiece(Piece piece) {
        this.pieces.add(piece);
    }

    public Piece getPieceAt(Position pos) {
        for (Piece piece : pieces) {
            if (piece.getCurrentPos().equals(pos)) return piece;
        }
        return null;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(ArrayList<Piece> pieces) {
        this.pieces = pieces;
    }

    public void drawBoard() {
        for (int row = 0; row < 8; ++row) {
            for (int col = 0; col < 8; ++col) {
                if ((row + col) % 2 == 0) {
                    shapeRenderer.setColor(Color.WHITE);
                } else {
                    shapeRenderer.setColor(Color.BLACK);
                }
                shapeRenderer.rect(row, col, 1, 1);
            }
        }
    }

    public void drawPieces() {
        for (Piece piece : pieces) {
            batch.draw(piece.getTexture(), piece.getCurrentPos().col, piece.getCurrentPos().row, 1, 1);
        }
    }
}
