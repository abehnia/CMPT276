package cmpt276.proj.finddamatch.model.gameLogic;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import cmpt276.proj.finddamatch.model.Card;
import cmpt276.proj.finddamatch.model.CardGenerator;
import cmpt276.proj.finddamatch.model.Image;
import cmpt276.proj.finddamatch.model.MutableImage;

import static cmpt276.proj.finddamatch.model.gameLogic.VALID_GAME_MODE.GAME1;
import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the public methods of the CardImpl class
 */
public class CardImplTest {

    @Test
    void get() {
        Image image1 = new ImageImpl(0);
        Image image2 = new ImageImpl(1);
        Image image3 = new ImageImpl(2);

        // Test parametrized constructor passing multiple images
        CardImpl card = new CardImpl(image1, image2, image3);
        assertEquals(image1, card.get(0));
        assertEquals(image2, card.get(1));
        assertEquals(image3, card.get(2));

        // Test parametrized constructor passing a list of images
        List<Image> images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        Card card2 = new CardImpl(images);
        assertEquals(image1, card2.get(0));
        assertEquals(image2, card2.get(1));
        assertEquals(image3, card2.get(2));

        // Test copy constructor
        Card copiedCard = new CardImpl(card);
        assertEquals(image1, copiedCard.get(0));
        assertEquals(image2, copiedCard.get(1));
        assertEquals(image3, copiedCard.get(2));
    }

    @Test
    void exists() {
        Image image1 = new ImageImpl(0);
        Image image2 = new ImageImpl(1);
        Image image3 = new ImageImpl(2);
        Image image4 = new ImageImpl(3);

        // Test parametrized constructor passing multiple images
        CardImpl card = new CardImpl(image1, image2, image3);
        assertTrue(card.exists(image1));
        assertTrue(card.exists(image2));
        assertTrue(card.exists(image3));
        assertFalse(card.exists(image4));

        // Test parametrized constructor passing a list of images
        List<Image> images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        Card card2 = new CardImpl(images);
        assertTrue(card2.exists(image1));
        assertTrue(card2.exists(image2));
        assertTrue(card2.exists(image3));
        assertFalse(card2.exists(image4));

        // Test copy constructor
        Card copiedCard = new CardImpl(card);
        assertTrue(copiedCard.exists(image1));
        assertTrue(copiedCard.exists(image2));
        assertTrue(copiedCard.exists(image3));
        assertFalse(copiedCard.exists(image4));

//        Image img = new ImageImpl(7);
//        for (Card card : PRE_GENERATED_CARDS.values()) {
//            assertFalse(card.exists(img));
//            assertEquals(3, card.size());
//        }
//        // Test parametrized constructor
//        ParameterTuner parameterTuner = new ParameterTuner(GAME1);
//        CardGenerator cardGenerator = new CardGeneratorImpl(parameterTuner);
//        DeckGenerator generator = new DeckGeneratorImpl(cardGenerator);
//        Stack<Card> stack = generator.generate();
//        for (Card card : stack) {
//            assertFalse(card.exists(img));
//            assertEquals(3, card.size());
//        }
//
//
//        Image image1 = new ImageImpl(0);
//        Image image2 = new ImageImpl(1);
//        Image image3 = new ImageImpl(2);
//        Image image4 = new ImageImpl(4);
//        // Test parametrized constructor
//        Card card = new CardImpl(image1, image2, image3);
//        for (Image image : card)
//            assertTrue(card.exists(image));
//        assertFalse(card.exists(image4));
//
//        // Test copy constructor
//        Card copiedCard = new CardImpl(card.get(0), card.get(1), card.get(2));
//        for (Image image : card)
//            assertTrue(copiedCard.exists(image));
//        assertFalse(copiedCard.exists(image4));
//
//        CardImpl card1 = new CardImpl(image1, image2, image3);
//        Card card2 = new CardImpl(card1);
//        for (Image image : card)
//            assertTrue(copiedCard.exists(image));
//        assertFalse(copiedCard.exists(image4));

    }

    @Test
    void size() {
        Image image1 = new ImageImpl(0);
        Image image2 = new ImageImpl(1);
        Image image3 = new ImageImpl(2);

        // Test parametrized constructor passing multiple images
        CardImpl card = new CardImpl(image1, image2, image3);
        assertEquals(3, card.size());

        // Test parametrized constructor passing a list of images
        List<Image> images = new ArrayList<>();
        images.add(image1);
        images.add(image2);
        images.add(image3);
        Card card2 = new CardImpl(images);
        assertEquals(3, card2.size());

        // Test copy constructor
        Card copiedCard = new CardImpl(card);
        assertEquals(3, copiedCard.size());


//        MutableImage image1 = new ImageImpl(7);
//        MutableImage image2 = new ImageImpl(1);
//        MutableImage image3 = new ImageImpl(2);
//
//        // Test parametrized constructor
//        ParameterTuner parameterTuner = new ParameterTuner(GAME1);
//        CardGenerator cardGenerator = new CardGeneratorImpl(parameterTuner);
//        DeckGenerator generator = new DeckGeneratorImpl(cardGenerator);
//        Stack<Card> stack = generator.generate();
//        assertEquals(7, stack.size());

    }

//    @Test
//    void randomize() {
//        MutableImage image1 = new ImageImpl(0);
//        MutableImage image2 = new ImageImpl(1);
//        MutableImage image3 = new ImageImpl(2);
//
//        // Test parametrized constructor
//        ParameterTuner parameterTuner = new ParameterTuner(GAME1);
//        CardGenerator cardGenerator = new CardGeneratorImpl(parameterTuner);
//        Card card = cardGenerator.generate(Arrays.asList(image1, image2, image3));
//        boolean actual = true;
//        // Test bounded
//        for (Image image : card) {
//            double distanceBetweenCircles = sqrt(image.getX() * image.getX()
//                    + image.getY() * image.getY());
//            double imageRadius = image.getRadius();
//            if (distanceBetweenCircles + imageRadius > 1f) actual = false;
//        }
//        assertTrue(actual);
//
//        // Test overlap
//        boolean doesNotOverlap = true;
//        for (int i = 0; i < card.size() - 1; ++i) {
//            for (int j = i + 1; j < card.size(); ++j) {
//                double sumOf2Radius = card.get(i).getRadius()
//                        + card.get(j).getRadius();
//                double deltaX = card.get(i).getX() - card.get(j).getX();
//                double deltaY = card.get(i).getY() - card.get(j).getY();
//                double distanceBetweenCircles =
//                        sqrt(deltaX * deltaX + deltaY * deltaY);
//                if (sumOf2Radius / 2.0f > distanceBetweenCircles) doesNotOverlap = false;
//            }
//        }
//        assertTrue(doesNotOverlap);
//    }

    @Test
    void iterator() {
        Image image1 = new ImageImpl(0);

        // Test parametrized constructor passing multiple images
        CardImpl card = new CardImpl(image1);
        Iterator<Image> it = card.iterator();
        int i = 0;
        while (it.hasNext()) {
            Image image = it.next();
            it.remove();
        }
        while (it.hasNext()) {
            ++i;
            it.next();
        }
        assertEquals(0, i);

        // Test parametrized constructor passing a list of images
        List<Image> images = new ArrayList<>();
        images.add(image1);
        Card card2 = new CardImpl(images);
        Iterator<Image> it2 = card.iterator();
        int j = 0;
        while (it2.hasNext()) {
            Image image = it2.next();
            it2.remove();
        }
        while (it2.hasNext()) {
            ++j;
            it2.next();
        }
        assertEquals(0, j);

        // Test copy constructor
        Card copiedCard = new CardImpl(card);
        Iterator<Image> it3 = card.iterator();
        int k = 0;
        while (it3.hasNext()) {
            Image image = it3.next();
            it3.remove();
        }
        while (it3.hasNext()) {
            ++k;
            it3.next();
        }
        assertEquals(0, k);
    }
}
