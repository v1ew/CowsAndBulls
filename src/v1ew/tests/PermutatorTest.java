package v1ew.tests;

import org.junit.Test;
import v1ew.cowsandbulls.Digit;
import v1ew.cowsandbulls.Permutator;

import static org.junit.Assert.*;

/**
 * Created by Shakhov on 23.06.2016.
 */
public class PermutatorTest {
    @Test
    public void translateCheck() {
        Permutator permutator = new Permutator("325476");
        assertEquals("325476", permutator.toString());
    }

    @Test
    public void swapCheck() {
        Permutator permutator = new Permutator("325476");
        permutator.swap(2, 3);
        assertEquals("324576", permutator.toString());
    }

    @Test
    public void nextPermCheck() {
        int length = 6;
        Digit[] digits = new Digit[length];
        for(int i = 0; i < length; ++i)
            digits[i] = new Digit(i + 2);
        Permutator permutator = new Permutator(digits);
        assertEquals("234567", permutator.nextPerm());
        assertEquals("234576", permutator.nextPerm());
        assertEquals("234657", permutator.nextPerm());
        assertEquals("234675", permutator.nextPerm());
        assertEquals("234756", permutator.nextPerm());
        assertEquals("234765", permutator.nextPerm());
        assertEquals("235467", permutator.nextPerm());
        permutator = new Permutator("32");
        assertEquals("23", permutator.nextPerm());
        assertEquals("32", permutator.nextPerm());
        assertEquals("", permutator.nextPerm());
    }
}