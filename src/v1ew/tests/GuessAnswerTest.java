package v1ew.tests;

import org.junit.Test;
import v1ew.cowsandbulls.GuessAnswer;

import static org.junit.Assert.*;

/**
 * Created by Shakhov on 30.06.2016.
 */
public class GuessAnswerTest {

    @Test
    public void gettersCheck() {
        GuessAnswer guessAnswer = new GuessAnswer("0123", 10);
        assertEquals("0123", guessAnswer.getGuess());
        assertEquals(10, guessAnswer.getAnswer());
    }
}