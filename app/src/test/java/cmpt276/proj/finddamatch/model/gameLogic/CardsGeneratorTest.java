package cmpt276.proj.finddamatch.model.gameLogic;

import org.junit.jupiter.api.Test;

import java.util.Stack;

import cmpt276.proj.finddamatch.model.Card;
import cmpt276.proj.finddamatch.model.CardGenerator;
import cmpt276.proj.finddamatch.model.Image;

import static org.junit.jupiter.api.Assertions.*;

class CardsGeneratorTest {

    @Test
    void size() {
        CardGenerator generator = new CardGeneratorImpl();
        Stack<Card> stack = generator.generate();
        assertEquals(7, stack.size());
    }

    @Test
    void isEmpty() {
        CardGenerator generator = new CardGeneratorImpl();
        Stack<Card> stack = generator.generate();
        assertFalse(stack.isEmpty());
    }

    @Test
    void push() {
        CardGenerator generator = new CardGeneratorImpl();
        Stack<Card> stack = generator.generate();
        Image image1 = new ImageImpl(0);
        Image image2 = new ImageImpl(1);
        Image image3 = new ImageImpl(2);
        Card card = new CardImpl(image1, image2, image3);
        stack.push(card);
        assertEquals(8, stack.size());
        assertEquals(card, stack.peek());
    }

    @Test
    void pop() {
        CardGenerator generator = new CardGeneratorImpl();
        Stack<Card> stack = generator.generate();
        stack.pop();
        assertEquals(6, stack.size());
    }

    @Test
    void peek() {
        CardGenerator generator = new CardGeneratorImpl();
        Stack<Card> stack = generator.generate();
        Image image1 = new ImageImpl(0);
        Image image2 = new ImageImpl(1);
        Image image3 = new ImageImpl(2);
        Card card = new CardImpl(image1, image2, image3);
        stack.push(card);
        assertEquals(card, stack.peek());
    }
}
