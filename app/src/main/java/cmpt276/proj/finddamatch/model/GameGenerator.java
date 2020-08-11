package cmpt276.proj.finddamatch.model;


/**
 * Interface to generate a game
 */
public interface GameGenerator {
    Game generate(long time);
    DeckGenerator generateDeck();
}
