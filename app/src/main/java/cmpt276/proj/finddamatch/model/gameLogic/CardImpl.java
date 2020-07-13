package cmpt276.proj.finddamatch.model.gameLogic;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import cmpt276.proj.finddamatch.model.Card;
import cmpt276.proj.finddamatch.model.Image;

/**
 * Typical card implementation of the card interface
 * For more information, refer to the card interface
 */
public class CardImpl implements Card {
    private ArrayList<Image> images;

    public CardImpl(Image... images) {
        this.images = new ArrayList<>();
        this.images.addAll(Arrays.asList(images));
    }

    public CardImpl(List<Image> images) {
        this.images = new ArrayList<>();
        this.images.addAll(images);
    }

    public CardImpl(CardImpl card) {
        this.images = new ArrayList<>();
        for (Image image : card) {
            this.images.add(image);
        }
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
