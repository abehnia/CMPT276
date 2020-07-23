package cmpt276.proj.finddamatch.model.gameLogic;

import cmpt276.proj.finddamatch.model.GameMode;

public class GameModeMatcher implements GameMode {
    private int order;
    private int size;
    private boolean hasText;

    public GameModeMatcher() {
        this.order = 0;
        this.size = 0;
        this.hasText = false;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setHasText(boolean hasText) {
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
