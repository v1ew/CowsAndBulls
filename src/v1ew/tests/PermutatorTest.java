package v1ew.tests;

import org.junit.Test;
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
    public void sortCheck() {
        Permutator permutator = new Permutator("325476");
        permutator.sort();
        assertEquals("234567", permutator.toString());
    }

    @Test
    public void nextPermCheck() {
        Permutator permutator = new Permutator("325476");
        assertEquals("325647", permutator.nextPerm());
        assertEquals("325674", permutator.nextPerm());
        assertEquals("325746", permutator.nextPerm());
        assertEquals("325764", permutator.nextPerm());
        assertEquals("326457", permutator.nextPerm());
        assertEquals("326475", permutator.nextPerm());
    }
}