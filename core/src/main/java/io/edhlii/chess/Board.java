package io.edhlii.chess;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

public class Board {
    private ArrayList<Piece> pieces;
    private ShapeRenderer shapeRenderer;

    Board(ShapeRenderer shapeRenderer) {
        this.shapeRenderer = shapeRenderer;
        this.pieces = new ArrayList<>();
    }

    void addPiece(Piece piece) {
        this.pieces.add(piece);
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(ArrayList<Piece> pieces) {
        this.pieces = pieces;
    }

    void drawBoard() {
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
}
