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

    @Override
    public void randomize() {
        Random random = new Random();
        float leftBoundedPosition = -1F;
        float rightBoundedPosition = 1F;
        float leftBoundedScale = 0.5F;
        float rightBoundedScale = 2F;
        float newX1 = random.nextFloat() * (rightBoundedPosition - leftBoundedPosition) + leftBoundedPosition;
        this.get(0).setX(newX1);
        float newX2 = random.nextFloat() * (rightBoundedPosition - leftBoundedPosition) + leftBoundedPosition;
        this.get(1).setX(newX2);
        float newX3 = random.nextFloat() * (rightBoundedPosition - leftBoundedPosition) + leftBoundedPosition;
        this.get(2).setX(newX3);
        float newY1 = random.nextFloat() * (rightBoundedPosition - leftBoundedPosition) + leftBoundedPosition;
        this.get(0).setX(newY1);
        float newY2 = random.nextFloat() * (rightBoundedPosition - leftBoundedPosition) + leftBoundedPosition;
        this.get(1).setX(newY2);
        float newY3 = random.nextFloat() * (rightBoundedPosition - leftBoundedPosition) + leftBoundedPosition;
        this.get(2).setX(newY3);
        float newOrientation1 = random.nextFloat();
        this.get(0).setOrientation(newOrientation1);
        float newOrientation2 = random.nextFloat();
        this.get(1).setOrientation(newOrientation2);
        float newOrientation3 = random.nextFloat();
        this.get(2).setOrientation(newOrientation3);
        float scale1 = random.nextFloat() * (rightBoundedScale - leftBoundedScale) + leftBoundedScale;
        this.get(0).setScale(scale1);
        float scale2 = random.nextFloat() * (rightBoundedScale - leftBoundedScale) + leftBoundedScale;
        this.get(0).setScale(scale2);
        float scale3 = random.nextFloat() * (rightBoundedScale - leftBoundedScale) + leftBoundedScale;
        this.get(0).setScale(scale3);
        while ((sqrt(this.get(0).getX() * this.get(0).getX() + this.get(0).getY() * this.get(0).getY()) + this.get(0).getRadius() * this.get(0).getScale() > 1F)
                && (sqrt(this.get(1).getX() * this.get(1).getX() + this.get(1).getY() * this.get(1).getY()) + this.get(1).getRadius() * this.get(1).getScale() > 1F)
                && (sqrt(this.get(2).getX() * this.get(2).getX() + this.get(2).getY() * this.get(2).getY()) + this.get(2).getRadius() * this.get(2).getScale() > 1F)
                && (this.get(0).getRadius() * this.get(0).getScale() + this.get(1).getRadius() * this.get(1).getScale()) >
                sqrt((this.get(0).getX() - this.get(1).getX()) * (this.get(0).getX() - this.get(1).getX())) + (this.get(0).getY() - this.get(1).getY()) * (this.get(0).getY() - this.get(1).getY())
                && (this.get(0).getRadius() * this.get(0).getScale() + this.get(2).getRadius() * this.get(2).getScale()) >
                sqrt((this.get(0).getX() - this.get(2).getX()) * (this.get(0).getX() - this.get(2).getX())) + (this.get(0).getY() - this.get(2).getY()) * (this.get(0).getY() - this.get(2).getY())
                && (this.get(2).getRadius() * this.get(2).getScale() + this.get(1).getRadius() * this.get(1).getScale()) >
                sqrt((this.get(2).getX() - this.get(1).getX()) * (this.get(2).getX() - this.get(1).getX())) + (this.get(2).getY() - this.get(1).getY()) * (this.get(2).getY() - this.get(1).getY())
        ) {
            this.randomize();
        }
    }

    @NonNull
    @Override
    public Iterator<Image> iterator() {
        return images.iterator();
    }
}
