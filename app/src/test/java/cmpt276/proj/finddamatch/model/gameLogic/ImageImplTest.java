package cmpt276.proj.finddamatch.model.gameLogic;

import org.junit.jupiter.api.Test;

import cmpt276.proj.finddamatch.model.Image;

import static org.junit.jupiter.api.Assertions.*;

class ImageImplTest {

    @Test
    void getOrientation() {
        // Test default constructor
        Image image = new ImageImpl(0);
        assertEquals(0.0, image.getOrientation());

        // Test parametrized constructor
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
        Image image = new ImageImpl(0);
        image.setOrientation(1.0f);
        assertEquals(1.0f, image.getOrientation());

        // Test parametrized constructor
        Image nextImage = new ImageImpl(0.5f, 0, 1, 0, 0);
        nextImage.setOrientation(1.0f);
        assertEquals(1.0f, nextImage.getOrientation());
    }

    @Test
    void setX() {
        // Test default constructor
        Image image = new ImageImpl(0);
        image.setX(1.0f);
        assertEquals(1.0f, image.getX());

        // Test parametrized constructor
        Image nextImage = new ImageImpl(0.5f, 0, 1, 0, 0);
        nextImage.setX(1.0f);
        assertEquals(1.0f, nextImage.getX());
    }

    @Test
    void setY() {
        // Test default constructor
        Image image = new ImageImpl(0);
        image.setY(1.0f);
        assertEquals(1.0f, image.getY());

        // Test parametrized constructor
        Image nextImage = new ImageImpl(0.5f, 0, 1, 0, 0);
        nextImage.setY(1.0f);
        assertEquals(1.0f, nextImage.getY());
    }

    @Test
    void setRadius() {
        // Test default constructor
        Image image = new ImageImpl(0);
        image.setRadius(1.0f);
        assertEquals(1.0f, image.getRadius());

        // Test parametrized constructor
        Image nextImage = new ImageImpl(0.5f, 0, 1, 0, 0);
        nextImage.setRadius(1.0f);
        assertEquals(1.0f, nextImage.getRadius());
    }

    @Test
    void isEquivalent() {
        Image image = new ImageImpl(0);
        Image nextImage = new ImageImpl(0.5f, 0, 1, 0, 0);
        Image falseImage = new ImageImpl(1);

        // Test true
        assertTrue(image.isEquivalent(nextImage));
        // Test false
        assertFalse(image.isEquivalent(falseImage));
    }
}