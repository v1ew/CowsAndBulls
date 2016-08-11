package v1ew.tests;

import org.junit.Test;
import v1ew.cowsandbulls.Digits;
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

    @Test
    public void arrangerCheck() {
        GuessData guessData = new GuessData("0123", 1);
        assertEquals("000c", guessData.getArrange());
        assertEquals("00c0", guessData.getArrange());
        assertEquals("0c00", guessData.getArrange());
        assertEquals("c000", guessData.getArrange());
        assertEquals("", guessData.getArrange());
        guessData = new GuessData("4567", 10);
        assertEquals("000b", guessData.getArrange());
        guessData.resetArrange();
        assertEquals("000b", guessData.getArrange());
    }

    @Test
    public void digitsCheck() {
        GuessData guessData = new GuessData("8590", 3);
        Digits digits = new Digits();
        digits.getDigit(1).setBull(2);
        guessData.saveDigits(digits);
        digits.freeAll();
        assertTrue(digits.getDigit(1).isFree());
        guessData.restoreDigits(digits);
        assertTrue(digits.getDigit(1).isBull(2));
    }
}