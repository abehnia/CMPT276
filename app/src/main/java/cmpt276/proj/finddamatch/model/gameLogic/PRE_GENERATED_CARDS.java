package cmpt276.proj.finddamatch.model.gameLogic;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;

import cmpt276.proj.finddamatch.model.Card;
import cmpt276.proj.finddamatch.model.Image;

/**
 * Pre Generated cards that satisfy the constraints for
 * the game Spot-It
 */
public enum PRE_GENERATED_CARDS implements Card {
    CARD1(new ImageImpl(0),
            new ImageImpl(1),
            new ImageImpl(2)),
    CARD2(new ImageImpl(2),
            new ImageImpl(3),
            new ImageImpl(4)),
    CARD3(new ImageImpl(0),
            new ImageImpl(4),
            new ImageImpl(5)),
    CARD4(new ImageImpl(0),
            new ImageImpl(3),
            new ImageImpl(6)),
    CARD5(new ImageImpl(1),
            new ImageImpl(4),
            new ImageImpl(6)),
    CARD6(new ImageImpl(1),
            new ImageImpl(3),
            new ImageImpl(5)),
    CARD7(new ImageImpl(2),
            new ImageImpl(5),
            new ImageImpl(6));

    private ArrayList<Image> images;

    private PRE_GENERATED_CARDS(Image... images) {
        this.images = new ArrayList<>();
        this.images.addAll(Arrays.asList(images));
    }

    @Override
    public Image get(int index) {
        return images.get(index);
    }

    @Override
    public boolean exists(Image image) {
        for (Image savedImage : images) {
            if (savedImage.isEquivalent(image)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return images.size();
    }

    @NonNull
    @Override
    public Iterator<Image> iterator() {
        return images.iterator();
    }
}