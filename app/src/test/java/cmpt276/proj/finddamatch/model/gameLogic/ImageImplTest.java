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

        // Test parametrized constructor passing all member objects
        Image nextImage = new ImageImpl(0.5f, 0, 1, 0, 0);
        assertEquals(0.0, nextImage.getOrientation());
    }

    @Test
    void getX() {
        // Test parametrized constructor passing image's ID
        Image image = new ImageImpl(0);
        assertEquals(0.0, image.getX());

        // Test parametrized constructor passing all member objects
        Image nextImage = new ImageImpl(0.5f, 0, 1, 0, 0);
        assertEquals(0.5f, nextImage.getX());

    }

    @Test
    void getY() {
        // Test parametrized constructor passing image's ID
        Image image = new ImageImpl(0);
        assertEquals(0.0, image.getY());

        // Test parametrized constructor passing all member objects
        Image nextImage = new ImageImpl(0.5f, 0, 1, 0, 0);
        assertEquals(0.0, nextImage.getY());
    }

    @Test
    void getRadius() {
        // Test parametrized constructor passing image's ID
        Image image = new ImageImpl(0);
        assertEquals(0.2f, image.getRadius());

        // Test parametrized constructor passing all member objects
        Image nextImage = new ImageImpl(0.5f, 0, 1, 0, 0);
        assertEquals(1, nextImage.getRadius());
    }

    @Test
    void getID() {
        // Test parametrized constructor passing image's ID
        Image image = new ImageImpl(0);
        assertEquals(0, image.getID());

        // Test parametrized constructor passing all member objects
        Image nextImage = new ImageImpl(0.5f, 0, 1, 0, 0);
        assertEquals(0, nextImage.getID());
    }

    @Test
    void setOrientation() {
        // Test parametrized constructor passing image's ID
        MutableImage image = new ImageImpl(0);
        image.setOrientation(1.0f);
        assertEquals(1.0f, image.getOrientation());

        // Test parametrized constructor passing all member objects
        MutableImage nextImage = new ImageImpl(0.5f, 0, 1, 0, 0);
        nextImage.setOrientation(1.0f);
        assertEquals(1.0f, nextImage.getOrientation());
    }

    @Test
    void setX() {
        // Test parametrized constructor passing image's ID
        MutableImage image = new ImageImpl(0);
        image.setX(1.0f);
        assertEquals(1.0f, image.getX());

        // Test parametrized constructor passing all member objects
        MutableImage nextImage = new ImageImpl(0.5f, 0, 1, 0, 0);
        nextImage.setX(1.0f);
        assertEquals(1.0f, nextImage.getX());
    }

    @Test
    void setY() {
        // Test parametrized constructor passing image's ID
        MutableImage image = new ImageImpl(0);
        image.setY(1.0f);
        assertEquals(1.0f, image.getY());

        // Test parametrized constructor passing all member objects
        MutableImage nextImage = new ImageImpl(0.5f, 0, 1, 0, 0);
        nextImage.setY(1.0f);
        assertEquals(1.0f, nextImage.getY());
    }

    @Test
    void setRadius() {
        // Test parametrized constructor passing image's ID
        MutableImage image = new ImageImpl(0);
        image.setRadius(1.0f);
        assertEquals(1.0f, image.getRadius());

        // Test parametrized constructor passing all member objects
        MutableImage nextImage = new ImageImpl(0.5f, 0, 1, 0, 0);
        nextImage.setRadius(1.0f);
        assertEquals(1.0f, nextImage.getRadius());
    }

    @Test
    void isEquivalent() {
        // Test parametrized constructor passing image's ID
        MutableImage image = new ImageImpl(0);
        MutableImage falseImage = new ImageImpl(1);

        // Test parametrized constructor passing all member objects
        MutableImage nextImage = new ImageImpl(0.5f, 0, 1, 0, 0);

        // Test true
        assertTrue(image.isEquivalent(nextImage));
        assertTrue(nextImage.isEquivalent(image));

        // Test false
        assertFalse(image.isEquivalent(falseImage));
        assertFalse(nextImage.isEquivalent(falseImage));
    }

    @Test
    void hasText() {
        // Test parametrized constructor passing image's ID
        MutableImage image = new ImageImpl(0);

        // Test parametrized constructor passing all member objects
        MutableImage nextImage = new ImageImpl(0.5f, 0, 1, 0, 0);

        // Test false
        assertFalse(image.hasText());
        assertFalse(nextImage.hasText());

        // Test true
        image.setHasText(true);
        nextImage.setHasText(true);
        assertTrue(image.hasText());
        assertTrue(nextImage.hasText());
    }

    @Test
    void setHasText() {
        // Test parametrized constructor passing image's ID
        MutableImage image = new ImageImpl(0);
        image.setHasText(true);
        assertTrue(image.hasText());

        // Test parametrized constructor passing all member objects
        MutableImage nextImage = new ImageImpl(0.5f, 0, 1, 0, 0);
        nextImage.setHasText(true);
        assertTrue(nextImage.hasText());
    }
}
