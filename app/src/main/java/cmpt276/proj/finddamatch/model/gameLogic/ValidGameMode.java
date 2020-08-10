package cmpt276.proj.finddamatch.model.gameLogic;

import cmpt276.proj.finddamatch.model.GameMode;

/**
 * Enum that contains all the different game modes
 */
public enum ValidGameMode implements GameMode {
    GAME1(2, 7, false),
    GAME2(2, 7, true),
    GAME3(2, 5, false),
    GAME4(2, 5, true),
    GAME5(3, 5, false),
    GAME6(3, 5, true),
    GAME7(3, 10, false),
    GAME8(3, 10, true),
    GAME9(3, 13, false),
    GAME10(3, 13, true),
    GAME11(5, 5, false),
    GAME12(5, 5, true),
    GAME13(5, 10, false),
    GAME14(5, 10, true),
    GAME15(5, 15, false),
    GAME16(5, 15, true),
    GAME17(5, 20, false),
    GAME18(5, 20, true),
    GAME19(5, 31, false),
    GAME20(5, 31, true),
    ;

    private int order;
    private int size;
    private boolean hasText;

    ValidGameMode(int order, int size, boolean hasText) {
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
        return (gameMode.getOrder() == this.getOrder() &&
                gameMode.getSize() == this.getSize() &&
                gameMode.hasText() == this.hasText);
    }

    @Override
    public String toString() {
        String contains = hasText ? "with text" : "without text";
        return "Images per card: " + (order + 1) +
                " size: " + size +
                " " + contains;
    }
}
