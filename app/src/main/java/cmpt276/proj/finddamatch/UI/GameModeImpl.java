package cmpt276.proj.finddamatch.UI;

public class GameModeImpl implements GameMode {
    private int order;
    private int size;

    public GameModeImpl(int order, int size) {
        this.order = order;
        this.size = size;
    }

    @Override
    public int getOrder() {
        return order;
    }

    @Override
    public int getSize() {
        return size;
    }
}
