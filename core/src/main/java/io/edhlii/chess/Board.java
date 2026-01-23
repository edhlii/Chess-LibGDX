package io.edhlii.chess;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import io.edhlii.chess.pieces.Piece;

import java.util.ArrayList;

public class Board {
    private ArrayList<Piece> pieces;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    private GameInputHandler gameInputHandler;

    Board(ShapeRenderer shapeRenderer, SpriteBatch batch) {
        this.shapeRenderer = shapeRenderer;
        this.batch = batch;
        this.pieces = new ArrayList<>();
    }

    public void setGameInputHandler(GameInputHandler gameInputHandler) {
        this.gameInputHandler = gameInputHandler;
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

    public void removePieceAt(Position pos) {
        for (Piece piece : pieces) {
            if (piece.getCurrentPos().equals(pos)) {
                pieces.remove(piece);
                return;
            }
        }
    }

    public boolean movePiece(Position src, Position dest) {
        Piece piece = getPieceAt(src);
        if (!piece.getValidMove().contains(dest)) return false;
        removePieceAt(dest);
        piece.setCurrentPos(dest);
        piece.setHasMoved(true);
        return true;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(ArrayList<Piece> pieces) {
        this.pieces = pieces;
    }

    public void calculateBoard() {
        for (Piece piece : pieces) {
            piece.calculateValidMove();
        }
    }

    public void drawBoard() {
        for (int row = 0; row < ChessGame.BOARD_SIZE; ++row) {
            for (int col = 0; col < ChessGame.BOARD_SIZE; ++col) {
                if ((row + col) % 2 == 0) {
                    shapeRenderer.setColor(Color.WHITE);
                } else {
                    shapeRenderer.setColor(Color.BROWN);
                }
                shapeRenderer.rect(row, col, 1, 1);
            }
        }
        if (gameInputHandler.getSelectedPos() == null) return;
        getPieceAt(gameInputHandler.getSelectedPos()).drawValidMove();
    }

    public void drawPieces() {
        for (Piece piece : pieces) {
            batch.draw(piece.getTexture(), piece.getCurrentPos().col, piece.getCurrentPos().row, 1, 1);
        }
    }
}
