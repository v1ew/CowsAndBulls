package v1ew.cowsandbulls;

import java.util.Arrays;

/**
 * Created by Shakhov on 23.06.2016.
 */
public class Permutator {
    public Permutator(String number) {
        length = number.length();
        numbers = new Digit[length];
        for(int i = 0; i < length; ++i) {
            int x = Integer.parseInt(number.charAt(i) + "");
            numbers[i] = new Digit(x);
        }
    }

    public void swap(int first, int second) {
        Digit temp = numbers[first];
        numbers[first] = numbers[second];
        numbers[second] = temp;
    }

    public void sort() {
        Arrays.sort(numbers);
    }

    public String nextPerm() {
        int first = length - 2;
        while(first != -1 && numbers[first].getDigit() >= numbers[first + 1].getDigit()) first--;
        //            sort();
        if(first > -1) {
            int second = length - 1;
            while (numbers[first].getDigit() >= numbers[second].getDigit()) second--;
            swap(first, second);
            int left = first + 1;
            int right = length - 1;
            while (left < right)
                swap(left++, right--);
        } else {
            sort();
        }

        return toString();
    }

    public String toString() {
        String result = "";
        for(int i = 0; i < length; ++i) {
            result += numbers[i].getDigit();
        }
        return result;
    }

    private Digit numbers[];
    private int length;
}
