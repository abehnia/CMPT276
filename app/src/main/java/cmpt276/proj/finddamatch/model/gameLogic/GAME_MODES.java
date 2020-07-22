package cmpt276.proj.finddamatch.model.gameLogic;

import cmpt276.proj.finddamatch.model.GameMode;

/**
 * Enum that contains all the different game modes
 */
// TODO: Complete the other enums based on the game modes we're going to have
public enum GAME_MODES implements GameMode {
    GAME1(2, 7, false) // This is our game right now
    ;

    private int order;
    private int size;
    private boolean hasText;

    private GAME_MODES(int order, int size, boolean hasText) {
        this.order = order;
        this.size = size;
        this.hasText = hasText;
    }

    @Override
    public int getOrder() {
        return order;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean hasText() {
        return hasText;
    }
}
