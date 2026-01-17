package io.edhlii.chess;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Piece {
    private SpriteBatch batch;
    private Texture texture;
    private Position currentPos;
    private ArrayList<Position> validMove;
    private Board board;

    Piece(SpriteBatch batch, Board board) {
        this.batch = batch;
        this.board = board;
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

    void draw() {
    }

    void calculateValidMove() {
    }

}
