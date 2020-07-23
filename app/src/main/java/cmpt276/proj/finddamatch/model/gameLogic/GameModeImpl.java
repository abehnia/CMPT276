package cmpt276.proj.finddamatch.model.gameLogic;

import cmpt276.proj.finddamatch.model.GameMode;

public class GameModeImpl implements GameMode {
    private int order;
    private int size;
    private boolean hasText;

    public GameModeImpl(int order, int size, boolean hasText) {
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

    @Override
    public boolean isEquivalent(GameMode gameMode) {
        return gameMode.getOrder() == order &&
                gameMode.getSize() == size &&
                gameMode.hasText() == hasText;
    }
}
