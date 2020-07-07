package cmpt276.proj.finddamatch.model;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

import static java.lang.Math.sqrt;

public class CardMockImpl implements Card {
    ArrayList<Image> images;

    public CardMockImpl(Image... images) {
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
            if (savedImage.getID() == image.getID()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return images.size();
    }

    public boolean isBounded() {
        for (int i = 0; i < this.size(); ++i)
            if (sqrt(this.get(i).getX() * this.get(i).getX() + this.get(i).getY() * this.get(i).getY()) + this.get(i).getRadius() * this.get(i).getScale() > 1f)
                return false;
        return true;
    }

    public boolean isNotOverlap() {
        for (int i = 0; i < this.size() - 1; ++i)
            for (int j = i + 1; j < this.size(); ++j)
                if ((this.get(i).getRadius() * this.get(i).getScale() + this.get(j).getRadius() * this.get(j).getScale()) >
                        sqrt((this.get(i).getX() - this.get(j).getX()) * (this.get(i).getX() - this.get(j).getX())) +
                                (this.get(i).getY() - this.get(j).getY()) * (this.get(i).getY() - this.get(j).getY()))
                    return false;
        return true;
    }

    public void randomizeScale() {
        Random random = new Random();
        float leftBoundedScale = 0.5F;
        float rightBoundedScale = 2F;
        for (int i = 0; i < this.size(); ++i)
            this.get(i).setScale(random.nextFloat() * (rightBoundedScale - leftBoundedScale) + leftBoundedScale);
    }

    @Override
    public void randomize() {
        Random random = new Random();
        float leftBoundedPosition = -1F;
        float rightBoundedPosition = 1F;

        for (int i = 0; i < this.size(); ++i) {
            this.get(i).setX(random.nextFloat() * (rightBoundedPosition - leftBoundedPosition) + leftBoundedPosition);
            this.get(i).setY(random.nextFloat() * (rightBoundedPosition - leftBoundedPosition) + leftBoundedPosition);
            this.get(i).setOrientation(random.nextFloat());
        }
        while (!isBounded() && !isNotOverlap()) this.randomizeScale();
    }

    @NonNull
    @Override
    public Iterator<Image> iterator() {
        return images.iterator();
    }
}
