package io.edhlii.chess;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

public class Piece {
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private Texture texture;

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
        validMove = new ArrayList<>();
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
        shapeRenderer.rect(currentPos.col, currentPos.row, 1, 1);
        shapeRenderer.setColor(Color.ROYAL);
        for (Position pos : validMove) {
            shapeRenderer.rect(pos.col, pos.row, 1, 1);
        }
    }

    public void calculateValidMove() {
    }

}
