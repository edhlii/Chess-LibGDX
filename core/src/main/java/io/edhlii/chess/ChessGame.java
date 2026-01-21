package io.edhlii.chess;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import io.edhlii.chess.pieces.*;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class ChessGame extends ApplicationAdapter {
    public static int WINDOW_WIDTH = 1200;
    public static int WINDOW_HEIGHT = 800;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private FitViewport viewport;
    private Texture image;
    private Board board;
    private GameInputHandler gameInputHandler;

    private Piece whitePawn;
    private Texture whitePawnTexture;

    @Override
    public void create() {
        // Initialize renderer
        image = new Texture("libgdx.png");
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        viewport = new FitViewport(12, 8);
        board = new Board(shapeRenderer, batch);

        // Initialize input handler
        gameInputHandler = new GameInputHandler(viewport, board);
        Gdx.input.setInputProcessor(gameInputHandler);
        board.setGameInputHandler(gameInputHandler);

        // Initialize texture
        whitePawnTexture = new Texture("white/Pawn.png");
        whitePawn = new Pawn(batch, shapeRenderer, whitePawnTexture, board, PieceColor.WHITE, new Position(0, 1));
        board.addPiece(whitePawn);
        board.calculateBoard();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render() {
        board.calculateBoard();

        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        viewport.apply();
        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        board.drawBoard();
        whitePawn.drawValidMove();
        shapeRenderer.end();

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        batch.draw(image, 9, 7, 2, 1);
        board.drawPieces();
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
        image.dispose();
    }
}
