package v1ew.tests;

import org.junit.Test;
import v1ew.cowsandbulls.*;

import static org.junit.Assert.*;

/**
 * Created by Shakhov on 15.06.2016.
 */
public class GuesserTest {

    @Test
    public void digitsReflectionCheck() {
        Guesser guesser = new Guesser();
        int answer = 21;
        Arranger arranger = new Arranger(answer, Guesser.NUMBER_LENGTH);
        String number = "3280";
        String arrange = arranger.arrange(11);
        guesser.mergeStates(number, arrange);
        assertEquals(DigitState.BULL, guesser.digits.getDigit(3).getState());
        assertEquals(0, guesser.digits.getDigit(3).getPosition());
        assertEquals(DigitState.BULL, guesser.digits.getDigit(2).getState());
        assertEquals(1, guesser.digits.getDigit(2).getPosition());
        assertEquals(DigitState.COW, guesser.digits.getDigit(8).getState());
        assertEquals(2, guesser.digits.getDigit(8).getPosition());
        assertEquals(DigitState.LOCAL_DELETED, guesser.digits.getDigit(0).getState());
    }

    @Test
    public void selectArrangeCheck() {
        Guesser guesser = new Guesser();
        guesser.digits.getDigit(0).setCow(0);
        assertTrue(guesser.isArrangeCorrect("0b00", "4056"));
        assertTrue(guesser.isArrangeCorrect("0c00", "4056"));
        assertFalse(guesser.isArrangeCorrect("0000", "4056"));
        assertFalse(guesser.isArrangeCorrect("c000", "4056"));

        guesser.digits.getDigit(0).setBull(0);
        assertFalse(guesser.isArrangeCorrect("b000", "4056")); // реальный бык в другом месте не корова
        assertFalse(guesser.isArrangeCorrect("bc00", "4056")); // на месте реального быка другой бык
        assertFalse(guesser.isArrangeCorrect("cb00", "4056")); // бык стал быком на другом месте

        guesser.digits.getDigit(0).setBull(1);
        assertFalse(guesser.isArrangeCorrect("b000", "4056"));
        assertTrue(guesser.isArrangeCorrect("0b00", "4056"));
        guesser.digits.getDigit(9).setCow(3);
        guesser.digits.getDigit(8).setCow(3);
        guesser.digits.getDigit(7).setCow(3);
        assertTrue(guesser.isArrangeCorrect("0b00", "4056"));
        guesser.digits.getDigit(3).setCow(3);
        assertFalse(guesser.isArrangeCorrect("0b00", "4056"));
    }

    @Test
    public void tryToMakeGuess1Check() {
        Guesser guesser = new Guesser();
        assertEquals("0123", guesser.guess());
        guesser.saveAnswer("0123", 4);
        String guess = guesser.guess();
        assertEquals("1032", guess);
        guesser.saveAnswer("1032", 4);
        guess = guesser.guess();
        assertEquals("2301", guess);
        guesser.saveAnswer("2301", 4);
        guess = guesser.guess();
        assertEquals("3210", guess);
    }

    @Test
    public void tryToMakeGuess2Check() {
        Guesser guesser = new Guesser();
        guesser.saveAnswer("0123", 12);
        String guess = guesser.guess();
        assertEquals("2413", guess);
    }

    @Test
    public void game0Check() {
        Master master = new Master("0123");
        Guesser guesser = new Guesser();
        String guess = guesser.guess();
        assertEquals("0123", guess);
        int answer = master.ask(guess);
        assertEquals(40, answer);
        guesser.saveAnswer(guess, answer);
        guess = guesser.guess();
        assertEquals("0123", guess);
        guesser.saveAnswer(guess, answer);
        guess = guesser.guess();
        assertEquals("0123", guess);
    }

    @Test
    public void game1Check() {
        Master master = new Master("8976");
        Guesser guesser = new Guesser();
        String guess = guesser.guess();
        assertEquals("0123", guess);

        int answer = master.ask(guess);
        assertEquals(0, answer);
        guesser.saveAnswer(guess, answer);
        guess = guesser.guess();
        assertEquals("4567", guess);
    }

    @Test
    public void game2Check() {
        Master master = new Master("4832");
        Guesser guesser = new Guesser();
        String guess = guesser.guess();
        assertEquals("0123", guess);

        int answer = master.ask(guess);
        assertEquals(2, answer);
        guesser.saveAnswer(guess, answer);
        guess = guesser.guess();
        assertEquals("2354", guess);

        answer = master.ask(guess);
        assertEquals(3, answer);
        guesser.saveAnswer(guess, answer);
        guess = guesser.guess();
        assertEquals("3246", guess);

        answer = master.ask(guess);
        assertEquals(3, answer);
        guesser.saveAnswer(guess, answer);
        guess = guesser.guess();
        assertEquals("4732", guess);

        answer = master.ask(guess);
        assertEquals(30, answer);
        guesser.saveAnswer(guess, answer);
        guess = guesser.guess();
        assertEquals("4832", guess);
    }

    @Test
    public void game5Check() {
        Master master = new Master("3872");
        Guesser guesser = new Guesser();
        String guess = guesser.guess();
        assertEquals("0123", guess);

        int answer = master.ask(guess);
        assertEquals(2, answer);
        guesser.saveAnswer(guess, answer);
        guess = guesser.guess();
        assertEquals("2354", guess);

        answer = master.ask(guess);
        assertEquals(2, answer);
        guesser.saveAnswer(guess, answer);
        guess = guesser.guess();
        assertEquals("3267", guess);

        answer = master.ask(guess);
        assertEquals(12, answer);
        guesser.saveAnswer(guess, answer);
        guess = guesser.guess();
        assertEquals("7238", guess);

        answer = master.ask(guess);
        assertEquals(4, answer);
        guesser.saveAnswer(guess, answer);
        guess = guesser.guess();
        assertEquals("3782", guess);

        answer = master.ask(guess);
        assertEquals(22, answer);
        guesser.saveAnswer(guess, answer);
        guess = guesser.guess();
        assertEquals("3872", guess);
    }

    @Test
    public void game6Check() {
        Master master = new Master("4190");
        Guesser guesser = new Guesser();
        String guess = guesser.guess();
        assertEquals("0123", guess);

        int answer = master.ask(guess);
        assertEquals(11, answer);
        guesser.saveAnswer(guess, answer);
        guess = guesser.guess();
        assertEquals("2453", guess);

        answer = master.ask(guess);
        assertEquals(1, answer);
        guesser.saveAnswer(guess, answer);
        guess = guesser.guess();
        assertEquals("1627", guess);

        answer = master.ask(guess);
        assertEquals(1, answer);
        guesser.saveAnswer(guess, answer);
        guess = guesser.guess();
        assertEquals("3189", guess);

        answer = master.ask(guess);
        assertEquals(11, answer);
        guesser.saveAnswer(guess, answer);
        guess = guesser.guess();
        assertEquals("5190", guess);

        answer = master.ask(guess);
        assertEquals(30, answer);
        guesser.saveAnswer(guess, answer);
        guess = guesser.guess();
        assertEquals("4190", guess);
    }

    @Test
    public void game7Check() {
        Master master = new Master("5761");
        Guesser guesser = new Guesser();
        String guess = guesser.guess();
        assertEquals("0123", guess);

        int answer = master.ask(guess);
        assertEquals(1, answer);
        guesser.saveAnswer(guess, answer);
        guess = guesser.guess();
        assertEquals("3456", guess);

        answer = master.ask(guess);
        assertEquals(2, answer);
        guesser.saveAnswer(guess, answer);
        guess = guesser.guess();
        assertEquals("6378", guess);

        answer = master.ask(guess);
        assertEquals(2, answer);
        guesser.saveAnswer(guess, answer);
        guess = guesser.guess();
        assertEquals("5839", guess);

        answer = master.ask(guess);
        assertEquals(10, answer);
        guesser.saveAnswer(guess, answer);
        guess = guesser.guess();
        assertEquals("5762", guess);

        answer = master.ask(guess);
        assertEquals(30, answer);
        guesser.saveAnswer(guess, answer);
        guess = guesser.guess();
        assertEquals("5761", guess);
    }
}