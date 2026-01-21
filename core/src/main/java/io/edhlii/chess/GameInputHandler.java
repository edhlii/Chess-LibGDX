package io.edhlii.chess;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class GameInputHandler implements InputProcessor {
    private FitViewport viewport;
    private Board board;
    private Position selectedPos = null;

    public Position getSelectedPos() {
        return selectedPos;
    }

    public void setSelectedPos(Position selectedPos) {
        this.selectedPos = selectedPos;
    }

    public GameInputHandler(FitViewport viewport, Board board) {
        this.viewport = viewport;
        this.board = board;
    }

    public void handleClick(int row, int col) {
        if (selectedPos == null) {
            System.out.println(board.getPieces());
            if (board.getPieceAt(new Position(row, col)) == null) return;
            selectedPos = new Position(row, col);
            System.out.println(selectedPos.row);
        } else {
            if (selectedPos.equals(new Position(row, col))) return;
            board.movePiece(selectedPos, new Position(row, col));
            selectedPos = null;
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 worldCoords = viewport.unproject(new Vector3(screenX, screenY, 0));
//        System.out.println(worldCoords);
        int row = (int) worldCoords.y;
        int col = (int) worldCoords.x;
        handleClick(row, col);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        System.out.println("dragging");
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
