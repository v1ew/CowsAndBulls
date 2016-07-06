package v1ew.cowsandbulls;

/**
 * Created by Shakhov on 06.07.2016.
 */
public class Helper {

    public static boolean isCorrectNumber(String number) {
        if(number.length() != Guesser.NUMBER_LENGTH)
            return false;
        String allDigits = "0123456789";
        for(char ch: number.toCharArray()) {
            if(allDigits.indexOf(ch, 0) < 0)
                return false;
        }
        return isNumberDigitsUnique(number);
    }

    public static boolean isNumberDigitsUnique(String number) {
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

    /**
     * Переводит строку цифр в массив объектов Digit
     * @param number строка цифр
     * @return массив объектов Digit
     */
    public static Digit[] stringToDigits(String number) {
        Digit[] digits = new Digit[number.length()];
        for(int i = 0; i < number.length(); ++i) {
            int x = Integer.parseInt(number.charAt(i) + "");
            digits[i] = new Digit(x);
        }
        return digits;
    }

}
