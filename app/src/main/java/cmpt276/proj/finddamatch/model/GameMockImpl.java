package cmpt276.proj.finddamatch.model;

import android.os.SystemClock;

public class GameMockImpl implements Game {
    long time = SystemClock.elapsedRealtime();
    Card card;
    Image image;
    int counter = 0;

    public GameMockImpl() {
        image = new ImageMockImpl(0.5f, 0, 1, 0,  0);
        card =
        new CardMockImpl(
                image,
                new ImageMockImpl(-0.5f, 0, 1, 0, 1),
                new ImageMockImpl(0, 0.5f, 1, 0, 2)
        );
    }

    @Override
    public boolean check(Image image) {
        return image == this.image;
    }

    @Override
    public void update(Image image) {
        counter += 1;
    }

    @Override
    public Card poll() {
        return card;
    }

    @Override
    public void reset() {
        time = SystemClock.elapsedRealtime();
        counter = 0;
    }

    @Override
    public long queryTime() {
        return SystemClock.elapsedRealtime() - time;
    }

    @Override
    public boolean isGameDone() {
        return counter == 7;
    }

    @Override
    public int getScore() {
        return 0;
    }
}
