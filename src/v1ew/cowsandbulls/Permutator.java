package v1ew.cowsandbulls;

import java.util.Arrays;

/**
 * Created by Shakhov on 23.06.2016.
 * Перестановщик сортирует заданный набор цифр и перебирает все возможные перестановки
 */
public class Permutator {
    /**
     * Конструктор, принимает массив
     * @param digits набор цифр в виде массива объекьлв Digit
     */
    public Permutator(Digit[] digits) {
        digitsArray = digits;
        length = digitsArray.length;
    }

    /**
     * Конструктор, принимает строку
     * @param number набор цифр в виде строки
     */
    public Permutator(String number) {
        this(Helper.stringToDigits(number));
    }

    /**
     * Меняет местами значения двух ячеек массива
     * @param first индекс первой ячейки
     * @param second индекс второй ячейки
     */
    public void swap(int first, int second) {
        Digit temp = digitsArray[first];
        digitsArray[first] = digitsArray[second];
        digitsArray[second] = temp;
    }

    /**
     * Возвращает следующую перестановку или пустую строку, если список перестановок исчерпан
     * @return перестановка в виде строки
     */
    public String nextPerm() {
        if(firstPerm) {
            firstPerm = false;
            Arrays.sort(digitsArray);
            return toString();
        }
        int first = length - 2;
        while(first != -1 && digitsArray[first].getDigit() >= digitsArray[first + 1].getDigit()) first--;
        if(first > -1) {
            int second = length - 1;
            while (digitsArray[first].getDigit() >= digitsArray[second].getDigit()) second--;
            swap(first, second);
            int left = first + 1;
            int right = length - 1;
            while (left < right)
                swap(left++, right--);
        } else {
            return "";
        }

        return toString();
    }

    public String toString() {
        String result = "";
        for(int i = 0; i < length; ++i) {
            result += digitsArray[i].getDigit();
        }
        return result;
    }

    private Digit digitsArray[];
    private int length;
    private boolean firstPerm = true;
}
