package v1ew.tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import v1ew.cowsandbulls.Master;

import static org.junit.Assert.*;

/**
 * Created by Shakhov on 14.06.2016.
 */
public class MasterTest {
    @Test
    public void gameProtocolCowsCheck() {
        int answer = master.ask("8214");
        int cows = Master.cows(answer);
        assertEquals(answer, 21);
        assertEquals(cows, 1);
    }

    @Test
    public void gameProtocolBullsCheck() {
        int answer = master.ask("8214");
        int bulls = Master.bulls(answer);
        assertEquals(answer, 21);
        assertEquals(bulls, 2);
    }

    @Test
    public void gameProtocolNothingCheck() {
        int answer = master.ask("5678");
        int cows = Master.cows(answer);
        int bulls = Master.bulls(answer);
        assertEquals(answer, 0);
        assertEquals(cows, 0);
        assertEquals(bulls, 0);
    }

    @BeforeClass
    public static void setUp() {
        master = new Master("1234");
    }

    @AfterClass
    public static void tearDown() {
        master = null;
    }

    static Master master;
}