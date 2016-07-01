package v1ew.cowsandbulls;

/**
 * Created by Shakhov on 21.06.2016.
 */
public class Digits extends Number {
    public Digits () {
        super(Guesser.DIGITS_LENGTH);
    }

    public void copyFrom(Digits digitsToSave) {
        freeAll();
        for(int i = 0; i < Guesser.DIGITS_LENGTH; ++i) {
            digitsToSave.getDigit(i).copyTo(digits[i]);
        }

    }

    public void copyTo(Digits digitsToLoad) {
        digitsToLoad.freeAll();
        for(int i = 0; i < Guesser.DIGITS_LENGTH; ++i) {
            digits[i].copyTo(digitsToLoad.getDigit(i));
        }
    }
}
