package v1ew.cowsandbulls;

/**
 * Created by Shakhov on 15.06.2016.
 */
public class Generator {
    public Generator(int numberLength) {
        this.numberLength = numberLength;
    }

    public static String generate(int length) {
        String number = "";
        Integer[] digits = new Integer[DIGITS_NUMBER];
        for(int i = 0; i < DIGITS_NUMBER; ++i) {
            digits[i] = i;
        }
        int tempDigitsNumber = DIGITS_NUMBER;
        for(int i = 0; i < length; ++i) {
            int x = (int) (tempDigitsNumber * Math.random());
            number += digits[x];
            digits[x] = digits[tempDigitsNumber - 1];
            tempDigitsNumber--;
        }
        return number;
    }

    public String generate() {
        return Generator.generate(numberLength);
    }

    int numberLength;
    public static final int DIGITS_NUMBER = 10;
}
