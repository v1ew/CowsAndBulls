package v1ew.tests;

import org.junit.Test;
import v1ew.cowsandbulls.Generator;
import v1ew.cowsandbulls.Master;

import static org.junit.Assert.*;

/**
 * Created by Shakhov on 15.06.2016.
 */
public class GeneratorTest {

    @Test
    public void generatedNumberLengthCheck() {
        int length = 4;
        Generator generator = new Generator(length);
        String number = generator.generate();
        assertEquals(number.length(), length);
        length = 5;
        generator = new Generator(length);
        number = generator.generate();
        assertEquals(number.length(), length);
    }

    @Test
    public void generatorUniqueCheck() {
        Generator generator = new Generator(4);
        assertEquals(isNumberDigitsUnique(generator.generate()), true);
        assertEquals(isNumberDigitsUnique(generator.generate()), true);
        assertEquals(isNumberDigitsUnique(generator.generate()), true);
    }

    @Test
    public void numberDigitsUniqueCheck() {
        assertFalse(isNumberDigitsUnique("122567"));
        assertTrue(isNumberDigitsUnique("23548"));
        assertFalse(isNumberDigitsUnique("987659"));
        assertTrue(isNumberDigitsUnique("85371"));
    }

    @Test(timeout=1000)
    public void guessCheck() {
        Master master = new Master(Generator.generate(4));
        int answer;
        int guessCount = 0;
        do {
            answer = master.ask(Generator.generate(4));
            guessCount++;
        } while(answer < 40);
        System.out.println("Guess count: " + guessCount);
        assertEquals(answer, 40); // 40 - 4 быка, т.е. число угадано
    }

    private static boolean isNumberDigitsUnique(String number) {
        int len = number.length();
        for(int i = 0; i < len; ++i) {
            char ch = number.charAt(i);
            for(int j = i+1; j < len; ++j) {
                if(ch == number.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }
}