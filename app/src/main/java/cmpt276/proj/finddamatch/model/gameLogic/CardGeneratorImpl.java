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
 * Typical implementation for card generator
 * For more information refer to the Card Generator Interface
 */
public class CardGeneratorImpl implements CardGenerator {
    private static final float LOWER_POSITION_BOUND = -1F;
    private static final float UPPER_POSITION_BOUND = 1F;
    private static final int MAX_NUMBER_OF_ITERATIONS = 1000000;
    private static final int MAX_NUMBER_OF_ITERATIONS_PER_ELEMENT = 1000;
    private static final int IS_TEXT_RAND_UPPER_BOUND = 100;
    private ParameterTuner parameterTuner;

    public CardGeneratorImpl(ParameterTuner parameterTuner) {
        this.parameterTuner = parameterTuner;
    }

    @Override
    public Card generate(List<MutableImage> images) {
        randomize(images);
        ArrayList<Image> returnImages = new ArrayList<>(images);
        return new CardImpl(returnImages);
    }

    public void randomize(List<MutableImage> images) {
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
            if (sumOf2Radius / 2.0 > distanceBetweenCircles) return false;
        }
        return true;
    }

    private void randomize(MutableImage image) {
        parameterTuner.setLowerRadiusBound();
        parameterTuner.setUpperRadiusBound();
        Random random = new Random();
        image.setX(random.nextFloat() * (UPPER_POSITION_BOUND -
                LOWER_POSITION_BOUND) + LOWER_POSITION_BOUND);
        image.setY(random.nextFloat() * (UPPER_POSITION_BOUND -
                LOWER_POSITION_BOUND) + LOWER_POSITION_BOUND);
        image.setOrientation((float) (random.nextFloat() * 2 * Math.PI));
        image.setRadius(random.nextFloat() * (parameterTuner.getUpperRadiusBound() -
                parameterTuner.getLowerRadiusBound()) + parameterTuner.getLowerRadiusBound());
        image.setHasText((random.nextInt(IS_TEXT_RAND_UPPER_BOUND) % 2 == 0));
    }
}
