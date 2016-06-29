package v1ew.tests;

import org.junit.Test;
import v1ew.cowsandbulls.*;

import static org.junit.Assert.*;

/**
 * Created by Shakhov on 22.06.2016.
 */
public class GuessStoreTest {
    @Test
    public void saveAndGetGuessCheck() {
        GuessStore guessStore = new GuessStore();
        assertEquals(0, guessStore.guessCount());
        guessStore.saveGuess("0123", 10);
        assertEquals(1, guessStore.guessCount());
        guessStore.saveGuess("4567", 21);
        assertEquals(2, guessStore.guessCount());
        assertTrue(guessStore.isGuessNew("6789"));
        assertFalse(guessStore.isGuessNew("4567"));
        GuessAnswer guessAnswer = guessStore.getGuessAnswer();
        assertEquals("0123", guessAnswer.getGuess());
        assertEquals(10, guessAnswer.getAnswer());
        guessAnswer = guessStore.getGuessAnswer();
        assertEquals("4567", guessAnswer.getGuess());
        assertEquals(21, guessAnswer.getAnswer());
        guessAnswer = guessStore.getGuessAnswer();
        assertNull(guessAnswer);
    }

    @Test
    public void saveAndLoadDigitsCheck() {
        int digitIndex = 5;
        Digits digits = new Digits();
        GuessStore guessStore = new GuessStore();
        guessStore.saveGuess("0123", 20);
        assertEquals(-1, digits.getDigit(digitIndex).getPosition());
        assertEquals(DigitState.FREE, digits.getDigit(digitIndex).getState());
        digits.getDigit(digitIndex).setBull(3);
        assertEquals(3, digits.getDigit(digitIndex).getPosition());
        assertEquals(DigitState.BULL, digits.getDigit(digitIndex).getState());
        guessStore.saveDigits(0, digits);
        assertEquals(3, digits.getDigit(digitIndex).getPosition());
        assertEquals(DigitState.BULL, digits.getDigit(digitIndex).getState());
        digits.reset();
        assertEquals(-1, digits.getDigit(digitIndex).getPosition());
        assertEquals(DigitState.FREE, digits.getDigit(digitIndex).getState());
        guessStore.restoreDigits(0, digits);
        assertEquals(3, digits.getDigit(digitIndex).getPosition());
        assertEquals(DigitState.BULL, digits.getDigit(digitIndex).getState());
        digits.getDigit(1).setCow(2);
        digits.getDigit(0).setCow(1);
        guessStore.saveDigits(0, digits);
        assertEquals(2, digits.getDigit(1).getPosition());
        assertEquals(DigitState.COW, digits.getDigit(1).getState());
        assertEquals(1, digits.getDigit(0).getPosition());
        assertEquals(DigitState.COW, digits.getDigit(0).getState());
        digits.reset();
        assertEquals(-1, digits.getDigit(0).getPosition());
        assertEquals(DigitState.FREE, digits.getDigit(0).getState());
        guessStore.restoreDigits(0, digits);
        assertEquals(2, digits.getDigit(1).getPosition());
        assertEquals(DigitState.COW, digits.getDigit(1).getState());
        assertEquals(1, digits.getDigit(0).getPosition());
        assertEquals(DigitState.COW, digits.getDigit(0).getState());

    }
}