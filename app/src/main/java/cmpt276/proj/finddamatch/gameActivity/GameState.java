package cmpt276.proj.finddamatch.gameActivity;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import cmpt276.proj.finddamatch.Persistable;
import cmpt276.proj.finddamatch.model.Game;
import cmpt276.proj.finddamatch.model.gameLogic.GameImpl;

/**
 * Handles the persistence of the game
 */
public class GameState implements Persistable {
    Game game;
    private static GameState gameState;

    private GameState() {}

    @Override
    public void load(Context context) throws IOException,
            ClassNotFoundException {
        FileInputStream gameStateFileInputStream =
                context.openFileInput("game-state");
        ObjectInput gameStateReader = new
                ObjectInputStream(gameStateFileInputStream);
        this.game = (GameImpl) gameStateReader.readObject();
        gameStateReader.close();
        gameStateFileInputStream.close();
    }

    @Override
    public void save(Context context) throws IOException {
        FileOutputStream gameStateFileOutputStream =
                context.openFileOutput("game-state",
                        Context.MODE_PRIVATE);
        ObjectOutputStream gameStateWriter = new
                ObjectOutputStream(gameStateFileOutputStream);
        gameStateWriter.writeObject(game);
        gameStateWriter.close();
        gameStateFileOutputStream.close();
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return this.game;
    }

    public static GameState get() {
        if (gameState == null) {
            gameState = new GameState();
        }
        return gameState;
    }
}
