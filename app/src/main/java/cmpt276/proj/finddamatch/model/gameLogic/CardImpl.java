package cmpt276.proj.finddamatch.model.gameLogic;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import cmpt276.proj.finddamatch.model.Card;
import cmpt276.proj.finddamatch.model.Image;

import static java.lang.Math.sqrt;

/**
 * Typical card implementation of the card interface
 * For more information, refer to the card interface
 */
public class CardImpl implements Card {
    public static final ArrayList<Card> PRE_GENERATED_CARDS = new ArrayList<>();
    public static final CardImpl CARD1 = new CardImpl(
            new ImageImpl(0),
            new ImageImpl(1),
            new ImageImpl(2));
    public static final CardImpl CARD2 = new CardImpl(
            new ImageImpl(2),
            new ImageImpl(3),
            new ImageImpl(4));
    public static final CardImpl CARD3 = new CardImpl(
            new ImageImpl(0),
            new ImageImpl(4),
            new ImageImpl(5));
    public static final CardImpl CARD4 = new CardImpl(
            new ImageImpl(0),
            new ImageImpl(3),
            new ImageImpl(6));
    public static final CardImpl CARD5 = new CardImpl(
            new ImageImpl(1),
            new ImageImpl(4),
            new ImageImpl(6));
    public static final CardImpl CARD6 = new CardImpl(
            new ImageImpl(1),
            new ImageImpl(3),
            new ImageImpl(5));
    public static final CardImpl CARD7 = new CardImpl(
            new ImageImpl(2),
            new ImageImpl(5),
            new ImageImpl(6));
    private static final float LOWER_POSITION_BOUND = -1F;
    private static final float UPPER_POSITION_BOUND = 1F;
    private static final float LOWER_RADIUS_BOUND = 0.2F;
    private static final float UPPER_RADIUS_BOUND = 1.0F;
    private static final float CARD_BASE_RADIUS = 1.0f;

    static {
        PRE_GENERATED_CARDS.add(CARD1);
        PRE_GENERATED_CARDS.add(CARD2);
        PRE_GENERATED_CARDS.add(CARD3);
        PRE_GENERATED_CARDS.add(CARD4);
        PRE_GENERATED_CARDS.add(CARD5);
        PRE_GENERATED_CARDS.add(CARD6);
        PRE_GENERATED_CARDS.add(CARD7);
    }

    private ArrayList<Image> images;

    public CardImpl(Image... images) {
        this.images = new ArrayList<>();
        this.images.addAll(Arrays.asList(images));
    }

    public CardImpl(Card card) {
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


    @Override
    public void randomize() {
        Queue<Image> validatedImages = new LinkedList<>();
        for (Image image : images) {
            randomize(image);
            while (!isBounded(image) ||
                    !doesNotOverlap(image, validatedImages)) {
                randomize(image);
            }
            validatedImages.add(image);
        }
    }

    @NonNull
    @Override
    public Iterator<Image> iterator() {
        return images.iterator();
    }

    private boolean isBounded(Image image) {
        double distanceBetweenCircles = sqrt(image.getX() * image.getX()
                + image.getY() * image.getY());
        double imageRadius = image.getRadius();
        return !(distanceBetweenCircles + imageRadius > CARD_BASE_RADIUS);
    }

    private boolean doesNotOverlap(Image image, Queue<Image> validatedImages) {
        for (Image validImage : validatedImages) {
            double sumOf2Radius = image.getRadius() + validImage.getRadius();
            double deltaX = image.getX() - validImage.getX();
            double deltaY = image.getY() - validImage.getY();
            double distanceBetweenCircles =
                    sqrt(deltaX * deltaX + deltaY * deltaY);
            if (sumOf2Radius > distanceBetweenCircles) return false;
        }
        return true;
    }

    private void randomize(Image image) {
        Random random = new Random();
        image.setX(random.nextFloat() * (UPPER_POSITION_BOUND -
                LOWER_POSITION_BOUND) + LOWER_POSITION_BOUND);
        image.setY(random.nextFloat() * (UPPER_POSITION_BOUND -
                LOWER_POSITION_BOUND) + LOWER_POSITION_BOUND);
        image.setOrientation(random.nextFloat());
        image.setRadius(random.nextFloat() * (UPPER_RADIUS_BOUND -
                LOWER_RADIUS_BOUND) + LOWER_RADIUS_BOUND);
    }

}
