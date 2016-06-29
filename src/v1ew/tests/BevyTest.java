package v1ew.tests;

import org.junit.Test;
import v1ew.cowsandbulls.Bevy;

import static org.junit.Assert.*;

/**
 * Created by Shakhov on 28.06.2016.
 */
public class BevyTest {
    @Test
    public void addingCheck() {
        Bevy bevy = new Bevy("3275");
        assertTrue(bevy.addCow(1));
        assertTrue(bevy.addCow(2));
        assertEquals(2, bevy.countCows());
        assertFalse(bevy.addCow(2));
        assertEquals(2, bevy.countCows());
        assertTrue(bevy.addBull(0));
        assertFalse(bevy.addCow(0));
        assertTrue(bevy.addBull(1));
        assertTrue(bevy.addCow(3));
        assertEquals("3b2b7c5c", bevy.toString());
        bevy.dropAll();
        assertFalse(bevy.addCow(4));
        assertEquals("3275", bevy.toString());
    }
}