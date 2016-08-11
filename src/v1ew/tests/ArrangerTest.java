package v1ew.tests;

import org.junit.Test;
import v1ew.cowsandbulls.Arranger;
import v1ew.cowsandbulls.Guesser;

import static org.junit.Assert.*;

/**
 * Created by Shakhov on 15.06.2016.
 */
public class ArrangerTest {

    @Test
    public void guesserArrangeCheck() {
        Arranger arranger = new Arranger(21, Guesser.NUMBER_LENGTH);
        assertEquals("bbc0", arranger.arrange(11));
    }

}