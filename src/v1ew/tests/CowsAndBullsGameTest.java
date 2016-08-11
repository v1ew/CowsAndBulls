package v1ew.tests;

import org.junit.Test;
import v1ew.cowsandbulls.CowsAndBullsGame;
import v1ew.cowsandbulls.Generator;
import v1ew.cowsandbulls.Guesser;

import static org.junit.Assert.*;

/**
 * Created by Shakhov on 23.06.2016.
 */

public class CowsAndBullsGameTest {
    @Test
    public void start() throws Exception {
        for(int i = 0; i < 20; ++i) {
            Generator generator = new Generator(Guesser.NUMBER_LENGTH);
            String number = generator.generate();
            assertTrue(CowsAndBullsGame.start(number) < 9); // Rarely 9, then test fails
        }
    }

    @Test
    public void start5761() {
        CowsAndBullsGame.start("5761");
    }

    @Test
    public void start6074() {
        CowsAndBullsGame.start("6074");
    }

    @Test
    public void start0395() {
        CowsAndBullsGame.start("0395");
    }

    @Test
    public void start3758() {
        CowsAndBullsGame.start("3758");
    }

    @Test
    public void start0982() {
        CowsAndBullsGame.start("0982");
    }

    @Test
    public void start9745() {
        CowsAndBullsGame.start("9745");
    }

    @Test
    public void start7460() {
        CowsAndBullsGame.start("7560");
    }

    @Test
    public void start2760() {
        CowsAndBullsGame.start("2760");
    }

    @Test
    public void start4869() {
        CowsAndBullsGame.start("4869");
    }

    @Test
    public void start4832() {
        CowsAndBullsGame.start("4832");
    }

    // Template
    /*
    @Test
    public void start() {
        CowsAndBullsGame.start("");
    }
    */
}