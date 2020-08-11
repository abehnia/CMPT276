package cmpt276.proj.finddamatch.model.gameLogic;

import cmpt276.proj.finddamatch.model.CardGenerator;
import cmpt276.proj.finddamatch.model.DeckGenerator;
import cmpt276.proj.finddamatch.model.Game;
import cmpt276.proj.finddamatch.model.GameGenerator;
import cmpt276.proj.finddamatch.model.GameMode;

import static cmpt276.proj.finddamatch.model.gameLogic.GameDifficulty.EASY;
import static cmpt276.proj.finddamatch.model.gameLogic.GameDifficulty.MEDIUM;

/**
 * Typical implementation for a game generator
 */
public class GameGeneratorImpl implements GameGenerator {
    GameMode gameMode;
    GameDifficulty gameDifficulty;

    public GameGeneratorImpl(GameMode gameMode, GameDifficulty gameDifficulty) {
        this.gameMode = gameMode;
        this.gameDifficulty = gameDifficulty;
    }

    @Override
    public Game generate(long time) {
        DeckGenerator deckGenerator = generateDeck();
        return new GameImpl(deckGenerator, time);
    }

    @Override
    public DeckGenerator generateDeck() {
        ParameterTuner parameterTuner = new ParameterTuner(gameMode);
        CardGenerator cardGenerator;
        if (gameDifficulty == EASY) {
            cardGenerator = new EasyCardGenerator(parameterTuner,
                    gameMode.hasText());
        } else if (gameDifficulty == MEDIUM) {
            cardGenerator = new MediumCardGenerator(parameterTuner,
                    gameMode.hasText());
        } else {
            cardGenerator = new HardCardGenerator(parameterTuner,
                    gameMode.hasText());
        }
        return new DeckGeneratorImpl(cardGenerator,
                gameMode);
    }
}
