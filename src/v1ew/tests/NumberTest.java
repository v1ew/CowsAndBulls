package v1ew.tests;

import org.junit.Test;
import v1ew.cowsandbulls.Number;

import static org.junit.Assert.*;

/**
 * Created by Shakhov on 16.06.2016.
 */
public class NumberTest {
    @Test
    public void numberCheck() {
        Number number = new Number("4687");
        assertTrue(number.getDigit(0).equals(4));
        assertTrue(number.getDigit(1).equals(6));
        assertTrue(number.getDigit(2).equals(8));
        assertTrue(number.getDigit(3).equals(7));
    }

    @Test
    public void nextFreeDigitsCheck() {
        Number number = new Number(10);
        number.getDigit(0).delete();
        number.getDigit(1).setBull(0);
        number.getDigit(2).setCow(3);
        assertEquals("34", number.nextFreeDigits(2));
    }
}