package v1ew.cowsandbulls;

/**
 * Created by Shakhov on 16.06.2016.
 */
public class Number {
    public Number(int length) {
        this.length = length;
        digits = new Digit[length];
        for(int i = 0; i < length; ++i) {
            digits[i] = new Digit(i);
        }
    }

    public Number(String numberString, Digits allDigits) {
        length = numberString.length();
        digits = new Digit[length];
        int digitInt = Integer.parseInt(numberString);
        for(int i = numberString.length() - 1; i >= 0; --i) {
            int rest = digitInt % 10;
            digits[i] = allDigits.getDigit(rest);
            digitInt /= 10;
        }
    }

    public Number(String numberString) {
        length = numberString.length();
        digits = new Digit[length];
        int digitInt = Integer.parseInt(numberString);
        for(int i = numberString.length() - 1; i >= 0; --i) {
            int rest = digitInt % 10;
            digits[i] = new Digit(rest);
            digitInt /= 10;
        }
    }

    public void setDigit(int index, Digit digit) {
        digits[index] = digit;
    }

    public Digit getDigit(int index) {
        return digits[index];
    }

    public String allCows() {
        String result = "";
        for(int i = 0; i < length; ++i) {
            if(digits[i].isCow())
                result += digits[i].getDigitString();
        }
        return result;
    }

    public String allNotBulls() {
        String result = "";
        for(int i = 0; i < length; ++i) {
            if(digits[i].isCow() || digits[i].isFree())
                result += digits[i].getDigitString();
        }
        return result;
    }

    public int freeCount() {
        int counter = 0;
        for(int i = 0; i < length; ++i) {
            if(digits[i].isFree())
                counter++;
        }
        return counter;
    }

    public int cowsCount() {
        int counter = 0;
        for(int i = 0; i < length; ++i) {
            if(digits[i].isCow())
                counter++;
        }
        return counter;
    }

    public int bullsCount() {
        int counter = 0;
        for(int i = 0; i < length; ++i) {
            if(digits[i].isBull())
                counter++;
        }
        return counter;
    }

    public int getLength() {
        return length;
    }

    public void freeAll() {
        for(int i = 0; i < length; ++i) {
            digits[i].free();
        }
    }

    public void print() {
        for(int i = 0; i < length; ++i) {
            System.out.print(digits[i]);
        }
        System.out.println(".");
    }

    protected Digit digits[];
    private int length;

}
