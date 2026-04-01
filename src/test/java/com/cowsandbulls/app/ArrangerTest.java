package com.cowsandbulls.app;

import org.junit.Test;
import com.cowsandbulls.app.Arranger;
import com.cowsandbulls.app.Guesser;

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
