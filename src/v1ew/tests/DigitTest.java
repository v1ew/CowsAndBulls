package v1ew.tests;

import org.junit.Test;
import v1ew.cowsandbulls.Digit;

import static org.junit.Assert.*;

/**
 * Created by Shakhov on 24.06.2016.
 */
public class DigitTest {
    @Test
    public void isCow() throws Exception {
        Digit digit = new Digit(1);
        assertFalse(digit.isCow(3));
        assertFalse(digit.isCow(0));
        digit.setCow(3);
        assertTrue(digit.isCow(3));
        assertFalse(digit.isCow(0));
        digit.setCow(0);
        assertTrue(digit.isCow(3));
        assertTrue(digit.isCow(0));
    }

}