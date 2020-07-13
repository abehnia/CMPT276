package cmpt276.proj.finddamatch.model.gameLogic;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

import cmpt276.proj.finddamatch.model.Card;
import cmpt276.proj.finddamatch.model.Image;
import cmpt276.proj.finddamatch.model.MutableImage;

import static org.junit.jupiter.api.Assertions.*;

/**
* Tests the public methods of ImageImpl class
* Including both Image and MutableImage interfaces
*/

class ImageImplTest {

    @Test
    void getOrientation() {
        // Test parametrized constructors
        MutableImage image = new ImageImpl(0);
        assertEquals(0.0, image.getOrientation());
        image.setOrientation(0.5f);
        assertEquals(0.5f, image.getOrientation());


        Image nextImage = new ImageImpl(0.5f, 0, 1, 0, 0);
        assertEquals(0.0, nextImage.getOrientation());
    }

    @Test
    void getX() {
        // Test default constructor
        Image image = new ImageImpl(0);
        assertEquals(0.0, image.getX());

        // Test parametrized constructor
        Image nextImage = new ImageImpl(0.5f, 0, 1, 0, 0);
        assertEquals(0.5f, nextImage.getX());

    }

    @Test
    void getY() {
        // Test default constructor
        Image image = new ImageImpl(0);
        assertEquals(0.0, image.getY());

        // Test parametrized constructor
        Image nextImage = new ImageImpl(0.5f, 0, 1, 0, 0);
        assertEquals(0.0, nextImage.getY());
    }

    @Test
    void getRadius() {
        // Test default constructor
        Image image = new ImageImpl(0);
        assertEquals(0.2f, image.getRadius());

        // Test parametrized constructor
        Image nextImage = new ImageImpl(0.5f, 0, 1, 0, 0);
        assertEquals(1, nextImage.getRadius());
    }

    @Test
    void getID() {
        // Test default constructor
        Image image = new ImageImpl(0);
        assertEquals(0, image.getID());

        // Test parametrized constructor
        Image nextImage = new ImageImpl(0.5f, 0, 1, 0, 0);
        assertEquals(0, nextImage.getID());
    }

    @Test
    void setOrientation() {
        // Test default constructor
        MutableImage image = new ImageImpl(0);
        image.setOrientation(1.0f);
        assertEquals(1.0f, image.getOrientation());

        // Test parametrized constructor
        MutableImage nextImage = new ImageImpl(0.5f, 0, 1, 0, 0);
        nextImage.setOrientation(1.0f);
        assertEquals(1.0f, nextImage.getOrientation());
    }

    @Test
    void setX() {
        // Test default constructor
        MutableImage image = new ImageImpl(0);
        image.setX(1.0f);
        assertEquals(1.0f, image.getX());

        // Test parametrized constructor
        MutableImage nextImage = new ImageImpl(0.5f, 0, 1, 0, 0);
        nextImage.setX(1.0f);
        assertEquals(1.0f, nextImage.getX());
    }

    @Test
    void setY() {
        // Test default constructor
        MutableImage image = new ImageImpl(0);
        image.setY(1.0f);
        assertEquals(1.0f, image.getY());

        // Test parametrized constructor
        MutableImage nextImage = new ImageImpl(0.5f, 0, 1, 0, 0);
        nextImage.setY(1.0f);
        assertEquals(1.0f, nextImage.getY());
    }

    @Test
    void setRadius() {
        // Test default constructor
        MutableImage image = new ImageImpl(0);
        image.setRadius(1.0f);
        assertEquals(1.0f, image.getRadius());

        // Test parametrized constructor
        MutableImage nextImage = new ImageImpl(0.5f, 0, 1, 0, 0);
        nextImage.setRadius(1.0f);
        assertEquals(1.0f, nextImage.getRadius());
    }

    @Test
    void isEquivalent() {
        MutableImage image = new ImageImpl(0);
        MutableImage nextImage = new ImageImpl(0.5f, 0, 1, 0, 0);
        MutableImage falseImage = new ImageImpl(1);

        // Test true
        assertTrue(image.isEquivalent(nextImage));
        // Test false
        assertFalse(image.isEquivalent(falseImage));
    }

    @Test
    void iterator() {
        Image image1 = new ImageImpl(0);
        Card card = new CardImpl(image1);
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
    }
}