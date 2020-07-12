package cmpt276.proj.finddamatch.model.gameLogic;

import org.junit.jupiter.api.Test;

import cmpt276.proj.finddamatch.model.Card;
import cmpt276.proj.finddamatch.model.Image;

import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.*;

public class CardImplTest {

    @Test
    void get() {
        Image image1 = new ImageImpl(0);
        Image image2 = new ImageImpl(1);
        Image image3 = new ImageImpl(2);
        // Test parametrized constructor
        Card card = new CardImpl(image1, image2, image3);
        assertEquals(image1, card.get(0));

        // Test copy constructor
        Card copiedCard = new CardImpl(card);
        assertEquals(image1, copiedCard.get(0));
    }

    @Test
    void exists() {
        Image image1 = new ImageImpl(0);
        Image image2 = new ImageImpl(1);
        Image image3 = new ImageImpl(2);
        Image image4 = new ImageImpl(4);
        // Test parametrized constructor
        Card card = new CardImpl(image1, image2, image3);
        for (Image image : card)
            assertTrue(card.exists(image));
        assertFalse(card.exists(image4));

        // Test copy constructor
        Card copiedCard = new CardImpl(card);
        for (Image image : card)
            assertTrue(copiedCard.exists(image));
        assertFalse(copiedCard.exists(image4));
    }

    @Test
    void size() {
        Image image1 = new ImageImpl(0);
        Image image2 = new ImageImpl(1);
        Image image3 = new ImageImpl(2);
        // Test parametrized constructor
        Card card = new CardImpl(image1, image2, image3);
        assertEquals(3, card.size());


        // Test default constructor
        Card copiedCard = new CardImpl(card);
        assertEquals(3, copiedCard.size());
    }

    @Test
    void randomize() {
        Image image1 = new ImageImpl(0);
        Image image2 = new ImageImpl(1);
        Image image3 = new ImageImpl(2);

        // Test parametrized constructor
        Card card = new CardImpl(image1, image2, image3);
        card.randomize();
        boolean actual = true;
        // Test bounded
        for (Image image : card) {
            double distanceBetweenCircles = sqrt(image.getX() * image.getX()
                    + image.getY() * image.getY());
            double imageRadius = image.getRadius();
            if (distanceBetweenCircles + imageRadius > 1f) actual = false;
        }
        assertTrue(actual);

        // Test overlap
        boolean doesNotOverlap = true;
        for (int i = 0; i < card.size() - 1; ++i) {
            for (int j = i + 1; j < card.size(); ++j) {
                double sumOf2Radius = card.get(i).getRadius()
                        + card.get(j).getRadius();
                double deltaX = card.get(i).getX() - card.get(j).getX();
                double deltaY = card.get(i).getY() - card.get(j).getY();
                double distanceBetweenCircles =
                        sqrt(deltaX * deltaX + deltaY * deltaY);
                if (sumOf2Radius > distanceBetweenCircles) doesNotOverlap = false;
            }
        }
        assertTrue(doesNotOverlap);
    }
}