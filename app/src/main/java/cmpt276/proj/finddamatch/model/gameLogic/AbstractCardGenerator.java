package cmpt276.proj.finddamatch.model.gameLogic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import cmpt276.proj.finddamatch.model.Card;
import cmpt276.proj.finddamatch.model.CardGenerator;
import cmpt276.proj.finddamatch.model.Image;
import cmpt276.proj.finddamatch.model.MutableImage;

import static cmpt276.proj.finddamatch.model.Card.CARD_BASE_RADIUS;
import static java.lang.Math.sqrt;

/**
 * Abstract Implementation for the Card Generator interface
 * All Easy/Medium/Hard generators extend this class
 */
public abstract class AbstractCardGenerator implements CardGenerator {
    protected static final float LOWER_POSITION_BOUND = -1F;
    protected static final float UPPER_POSITION_BOUND = 1F;
    protected static final int MAX_NUMBER_OF_ITERATIONS = 1000000;
    protected static final int MAX_NUMBER_OF_ITERATIONS_PER_ELEMENT = 1000;
    protected static final int IS_TEXT_RAND_UPPER_BOUND = 100;
    protected ParameterTuner parameterTuner;
    protected boolean hasText;

    public AbstractCardGenerator(ParameterTuner parameterTuner,
                                 boolean hasText) {
        this.parameterTuner = parameterTuner;
        this.hasText = hasText;
    }

    @Override
    public Card generate(List<MutableImage> images) {
        randomize(images);
        ArrayList<Image> returnImages = new ArrayList<>(images);
        return new CardImpl(returnImages);
    }

    private void randomize(List<MutableImage> images) {
        int totalIterations = 0;
        Queue<MutableImage> validatedImages = new LinkedList<>();

        for (int i = 0; i < images.size(); ++i) {
            MutableImage image = images.get(i);
            randomize(image);
            int elementIterations = 0;
            while (!isBounded(image) ||
                    !doesNotOverlap(image, validatedImages)) {
                randomize(image);
                totalIterations += 1;
                if (totalIterations >= MAX_NUMBER_OF_ITERATIONS) {
                    throw new RuntimeException
                            ("Too many iterations in randomize");
                }
                elementIterations += 1;
                if (elementIterations >= MAX_NUMBER_OF_ITERATIONS_PER_ELEMENT) {
                    validatedImages.clear();
                    i = -1;
                    break;
                }
            }
            if (i != -1) {
                validatedImages.add(image);
            }
        }
        if (hasText) {
            randomizeText(images);
        }
    }

    private boolean isBounded(Image image) {
        double distanceBetweenCircles = sqrt(image.getX() * image.getX()
                + image.getY() * image.getY());
        double imageRadius = image.getRadius();
        return (distanceBetweenCircles + imageRadius) <= CARD_BASE_RADIUS;
    }

    private boolean doesNotOverlap(Image image, Queue<MutableImage> validatedImages) {
        for (Image validImage : validatedImages) {
            double sumOf2Radius = image.getRadius() + validImage.getRadius();
            double deltaX = image.getX() - validImage.getX();
            double deltaY = image.getY() - validImage.getY();
            double distanceBetweenCircles =
                    sqrt(deltaX * deltaX + deltaY * deltaY);
            if (sumOf2Radius * 0.8 > distanceBetweenCircles) return false;
        }
        return true;
    }

    private void randomize(MutableImage image) {
        Random random = new Random();
        image.setX(random.nextFloat() * (UPPER_POSITION_BOUND -
                LOWER_POSITION_BOUND) + LOWER_POSITION_BOUND);
        image.setY(random.nextFloat() * (UPPER_POSITION_BOUND -
                LOWER_POSITION_BOUND) + LOWER_POSITION_BOUND);
        randomizeOrientation(image, random);
        randomizeRadius(image, random);
        if (hasText) {
            image.setHasText((random.nextInt(IS_TEXT_RAND_UPPER_BOUND) % 2 == 0));
        }
    }

    private void randomizeText(List<MutableImage> images) {
        Random random = new Random();
        int randHasTextImg = random.nextInt(images.size());
        images.get(randHasTextImg).setHasText(true);
        int randNotHaveTextImg = randHasTextImg;
        while (randNotHaveTextImg == randHasTextImg) {
            randNotHaveTextImg = random.nextInt(images.size());
        }
        images.get(randNotHaveTextImg).setHasText(false);
    }

    protected abstract void randomizeOrientation(MutableImage image, Random random);
    protected abstract void randomizeRadius(MutableImage image, Random random);
}
