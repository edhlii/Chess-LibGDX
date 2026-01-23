package io.edhlii.chess.pieces;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import io.edhlii.chess.Board;
import io.edhlii.chess.ChessGame;
import io.edhlii.chess.Position;

import java.util.ArrayList;

public class Piece {
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private Texture texture;
    private FitViewport viewport;

    protected boolean hasMoved;
    protected PieceColor color;
    protected Board board;
    protected Position currentPos;
    protected ArrayList<Position> validMove;

    Piece(SpriteBatch batch, ShapeRenderer shapeRenderer, Texture texture, Board board, PieceColor color, Position pos) {
        this.batch = batch;
        this.shapeRenderer = shapeRenderer;
        this.board = board;
        this.color = color;
        this.texture = texture;
        this.currentPos = pos;
        hasMoved = false;
        validMove = new ArrayList<>();
    }

    public boolean isHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public PieceColor getColor() {
        return color;
    }

    public void setColor(PieceColor color) {
        this.color = color;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Position getCurrentPos() {
        return currentPos;
    }

    public void setCurrentPos(Position currentPos) {
        this.currentPos = currentPos;
    }

    public ArrayList<Position> getValidMove() {
        return validMove;
    }

    public void setValidMove(ArrayList<Position> validMove) {
        this.validMove = validMove;
    }

    public void draw() {
        batch.draw(texture, currentPos.row, currentPos.col);
    }

    public void drawValidMove() {
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect((float) (currentPos.col + 0.25), (float) (currentPos.row + 0.25), 0.5f, 0.5f);
        shapeRenderer.setColor(Color.ROYAL);
        for (Position pos : validMove) {
            shapeRenderer.rect((float) (pos.col + 0.25), (float) (pos.row + 0.25), 0.5f, 0.5f);
        }
    }

    public boolean validPosition(Position pos) {
        if (pos.isOutOfBoard()) return false;
        Piece piece = board.getPieceAt(pos);
        if (piece == null) return true;
        if (piece.getColor() == color.opponent()) return true;
        return false;
    }

    public void calculateValidMove() {
    }

    protected void calculateRookValidMove() {
        for (int row = currentPos.row + 1; row < ChessGame.BOARD_SIZE; ++row) {
            Position pos = new Position(row, currentPos.col);
            if (!validPosition(pos)) break;
            validMove.add(pos);
            Piece piece = board.getPieceAt(pos);
            if (piece != null) break;
        }
        for (int row = currentPos.row - 1; row >= 0; --row) {
            Position pos = new Position(row, currentPos.col);
            if (!validPosition(pos)) break;
            validMove.add(pos);
            Piece piece = board.getPieceAt(pos);
            if (piece != null) break;
        }
        for (int col = currentPos.col - 1; col >= 0; --col) {
            Position pos = new Position(currentPos.row, col);
            if (!validPosition(pos)) break;
            validMove.add(pos);
            Piece piece = board.getPieceAt(pos);
            if (piece != null) break;
        }
        for (int col = currentPos.col + 1; col < ChessGame.BOARD_SIZE; ++col) {
            Position pos = new Position(currentPos.row, col);
            if (!validPosition(pos)) break;
            validMove.add(pos);
            Piece piece = board.getPieceAt(pos);
            if (piece != null) break;
        }
    }

    protected void calculateBishopValidMove() {
        for (int row = currentPos.row + 1, col = currentPos.col + 1; ; ++row, ++col) {
            Position pos = new Position(row, col);
            if (pos.isOutOfBoard() || !validPosition(pos)) break;
            validMove.add(pos);
            Piece piece = board.getPieceAt(pos);
            if (piece != null) break;
        }
        for (int row = currentPos.row - 1, col = currentPos.col - 1; ; --row, --col) {
            Position pos = new Position(row, col);
            if (pos.isOutOfBoard() || !validPosition(pos)) break;
            validMove.add(pos);
            Piece piece = board.getPieceAt(pos);
            if (piece != null) break;
        }
        for (int row = currentPos.row - 1, col = currentPos.col + 1; ; --row, ++col) {
            Position pos = new Position(row, col);
            if (pos.isOutOfBoard() || !validPosition(pos)) break;
            validMove.add(pos);
            Piece piece = board.getPieceAt(pos);
            if (piece != null) break;
        }
        for (int row = currentPos.row + 1, col = currentPos.col - 1; ; ++row, --col) {
            Position pos = new Position(row, col);
            if (pos.isOutOfBoard() || !validPosition(pos)) break;
            validMove.add(pos);
            Piece piece = board.getPieceAt(pos);
            if (piece != null) break;
        }
    }

}
