package v1ew.cowsandbulls;

/**
 * Created by Shakhov on 16.06.2016.
 * Представляет число, заданной длины.
 * Каждая цифра может иметь состояние и позицию в загаданном числе.
 */
public class Number {
    /**
     * Создает число из цифр подряд от 0 до length-1
     * @param length длина создаваемого числа
     */
    public Number(int length) {
        this.length = length;
        digits = new Digit[length];
        for(int i = 0; i < length; ++i) {
            digits[i] = new Digit(i);
        }
    }

    /**
     * Создает число на основе строки из цифр, где цифры отображаются на общий набор цифр, отражающий состояние.
     * В будущем изменения состояний цифр сразу отразятся в общем наборе цифр.
     * @param numberString строка, представляющая число
     * @param allDigits общий набор цифр
     */
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

    /**
     * Создает число на основе строки из цифр. Каждая цифра - объект Digit.
     * @param numberString строка, представляющая число
     */
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
