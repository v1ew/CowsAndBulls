package v1ew.cowsandbulls;

/**
 * Created by Shakhov on 21.06.2016.
 * Класс представляет набор всех цифр от 0 до 9.
 * Каждая цифра может иметь свое состояние и позицию в загаданном числе.
 */
public class Digits extends Number {
    public Digits () {
        super(Guesser.DIGITS_LENGTH);
    }

    /**
     * Скопировать состояние всех цифр из заданного набора в свой.
     * @param digitsToSave заданный набор
     */
    public void copyFrom(Digits digitsToSave) {
        freeAll();
        for(int i = 0; i < Guesser.DIGITS_LENGTH; ++i) {
            digitsToSave.getDigit(i).copyTo(digits[i]);
        }

    }

    /**
     * Копировать состояние всех цифр в заданный набор
     * @param digitsToLoad заданный набор
     */
    public void copyTo(Digits digitsToLoad) {
        digitsToLoad.freeAll();
        for(int i = 0; i < Guesser.DIGITS_LENGTH; ++i) {
            digits[i].copyTo(digitsToLoad.getDigit(i));
        }
    }
}
