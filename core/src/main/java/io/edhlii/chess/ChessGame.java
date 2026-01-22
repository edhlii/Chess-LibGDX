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
    public static final int WINDOW_WIDTH = 1200;
    public static final int WINDOW_HEIGHT = 800;
    public static final int BOARD_SIZE = 8;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private FitViewport viewport;
    private Texture image;
    private Board board;
    private GameInputHandler gameInputHandler;


    private Texture whitePawnTexture;
    private Texture whiteRookTexture;
    private Texture whiteKnightTexture;
    private Texture whiteBishopTexture;
    private Texture whiteQueenTexture;
    private Texture whiteKingTexture;

    private Texture blackPawnTexture;
    private Texture blackRookTexture;
    private Texture blackKnightTexture;
    private Texture blackBishopTexture;
    private Texture blackQueenTexture;
    private Texture blackKingTexture;

    public void initializeTexture() {
        whitePawnTexture = new Texture("white/Pawn.png");
        whiteRookTexture = new Texture("white/Rook.png");
        whiteKnightTexture = new Texture("white/Knight.png");
        whiteBishopTexture = new Texture("white/Bishop.png");
        whiteQueenTexture = new Texture("white/Queen.png");
        whiteKingTexture = new Texture("white/King.png");

        blackPawnTexture = new Texture("black/Pawn.png");
        blackRookTexture = new Texture("black/Rook.png");
        blackKnightTexture = new Texture("black/Knight.png");
        blackBishopTexture = new Texture("black/Bishop.png");
        blackQueenTexture = new Texture("black/Queen.png");
        blackKingTexture = new Texture("black/King.png");
    }

    public void initializeNewBoard() {
        King whiteKing = new King(batch, shapeRenderer, whiteKingTexture, board, PieceColor.WHITE, new Position(0, 3));
        Queen whiteQueen = new Queen(batch, shapeRenderer, whiteQueenTexture, board, PieceColor.WHITE, new Position(0, 4));
        King blackKing = new King(batch, shapeRenderer, blackKingTexture, board, PieceColor.BLACK, new Position(7, 4));
        Queen blackQueen = new Queen(batch, shapeRenderer, blackQueenTexture, board, PieceColor.BLACK, new Position(7, 3));

        // Add white pawn piece
        for (int i = 0; i < ChessGame.BOARD_SIZE; ++i) {
            board.addPiece(new Pawn(batch, shapeRenderer, whitePawnTexture, board, PieceColor.WHITE, new Position(1, i)));
        }
        // Add black pawn piece
        for (int i = 0; i < ChessGame.BOARD_SIZE; ++i) {
            board.addPiece(new Pawn(batch, shapeRenderer, blackPawnTexture, board, PieceColor.BLACK, new Position(6, i)));
        }

        board.addPiece(new Rook(batch, shapeRenderer, whiteRookTexture, board, PieceColor.WHITE, new Position(0, 0)));
        board.addPiece(new Rook(batch, shapeRenderer, whiteRookTexture, board, PieceColor.WHITE, new Position(0, 7)));

        board.addPiece(new Knight(batch, shapeRenderer, whiteKnightTexture, board, PieceColor.WHITE, new Position(0, 1)));
        board.addPiece(new Knight(batch, shapeRenderer, whiteKnightTexture, board, PieceColor.WHITE, new Position(0, 6)));

        board.addPiece(new Bishop(batch, shapeRenderer, whiteBishopTexture, board, PieceColor.WHITE, new Position(0, 2)));
        board.addPiece(new Bishop(batch, shapeRenderer, whiteBishopTexture, board, PieceColor.WHITE, new Position(0, 5)));

        board.addPiece(new Rook(batch, shapeRenderer, blackRookTexture, board, PieceColor.BLACK, new Position(7, 0)));
        board.addPiece(new Rook(batch, shapeRenderer, blackRookTexture, board, PieceColor.BLACK, new Position(7, 7)));

        board.addPiece(new Knight(batch, shapeRenderer, blackKnightTexture, board, PieceColor.BLACK, new Position(7, 1)));
        board.addPiece(new Knight(batch, shapeRenderer, blackKnightTexture, board, PieceColor.BLACK, new Position(7, 6)));

        board.addPiece(new Bishop(batch, shapeRenderer, blackBishopTexture, board, PieceColor.BLACK, new Position(7, 2)));
        board.addPiece(new Bishop(batch, shapeRenderer, blackBishopTexture, board, PieceColor.BLACK, new Position(7, 5)));

        board.addPiece(whiteKing);
        board.addPiece(whiteQueen);

        board.addPiece(blackKing);
        board.addPiece(blackQueen);

        board.calculateBoard();
    }

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

        initializeTexture();
        initializeNewBoard();
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
//        whitePawn.drawValidMove();
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
