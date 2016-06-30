package v1ew.tests;

import org.junit.Test;
import v1ew.cowsandbulls.GuessData;

import static org.junit.Assert.*;

/**
 * Created by Shakhov on 30.06.2016.
 */
public class GuessDataTest {

    @Test
    public void getterCheck() {
        GuessData guessData = new GuessData("0123", 10);
        assertEquals("0123", guessData.getGuess());
        assertEquals(10, guessData.getAnswer());
    }
}