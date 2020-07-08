package cmpt276.proj.finddamatch.gameLogic;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

import cmpt276.proj.finddamatch.model.Card;
import cmpt276.proj.finddamatch.model.Image;

import static java.lang.Math.sqrt;

public class CardImpl implements Card {
    ArrayList<Image> images;

    public CardImpl(Image... images) {
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
        for (int i = 0; i < this.size(); ++i) {
            double distanceBetweenCircles = sqrt(this.get(i).getX() * this.get(i).getX()
                    + this.get(i).getY() * this.get(i).getY());
            double imageRadius = this.get(i).getRadius() * this.get(i).getScale();
            if (distanceBetweenCircles + imageRadius > 1f) return false;
        }
        return true;
    }

    public boolean isNotOverlap() {
        for (int i = 0; i < this.size() - 1; ++i) {
            for (int j = i + 1; j < this.size(); ++j) {
                double sumOf2Radius = this.get(i).getRadius() * this.get(i).getScale()
                        + this.get(j).getRadius() * this.get(j).getScale();
                double distanceBetweenCircles = sqrt((this.get(i).getX() - this.get(j).getX())
                        * (this.get(i).getX() - this.get(j).getX()))
                        + (this.get(i).getY() - this.get(j).getY())
                        * (this.get(i).getY() - this.get(j).getY());
                if (sumOf2Radius > distanceBetweenCircles) return false;
            }
        }
        return true;
    }

    public void randomizeEverything() {
        Random random = new Random();
        float leftBoundedPosition = -1F;
        float rightBoundedPosition = 1F;
        float leftBoundedScale = 0.5F;
        float rightBoundedScale = 2F;
        for (int i = 0; i < this.size(); ++i) {
            this.get(i).setX(random.nextFloat() * (rightBoundedPosition - leftBoundedPosition) + leftBoundedPosition);
            this.get(i).setY(random.nextFloat() * (rightBoundedPosition - leftBoundedPosition) + leftBoundedPosition);
            this.get(i).setOrientation(random.nextFloat());
            this.get(i).setScale(random.nextFloat() * (rightBoundedScale - leftBoundedScale) + leftBoundedScale);
        }
    }

    @Override
    public void randomize() {
        while (!this.isBounded() && !this.isNotOverlap()) this.randomizeEverything();
    }

    @NonNull
    @Override
    public Iterator<Image> iterator() {
        return images.iterator();
    }
}
