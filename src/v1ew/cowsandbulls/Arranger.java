package v1ew.cowsandbulls;

/**
 * Created by Shakhov on 15.06.2016.
 */
public class Arranger {
    public Arranger(int answer, int length) {
        this.length = length;
        this.answer = answer;
        arrangeIndex = 0;
    }

    public void restart() {
        arrangeIndex = 0;
    }

    public static String binaryString(int number) {
        String res = "";
        int rest;

        while(number > 0) {
            rest = number % 2;
            number = number / 2;
            res = rest + res;
        }

        return res;
    }

    public static String ternaryString(int number) {
        String res = "";
        int rest;

        while(number > 0) {
            rest = number % 3;
            number = number / 3;
            res = rest + res;
        }

        return res;
    }

    private int symbolsCount(String where, char what) {
        int counter = 0;

        for(int i = 0; i < where.length(); ++i) {
            if(where.charAt(i) == what) {
                counter++;
            }
        }
        return counter;
    }

    public String arrange() {
        return arrange(arrangeIndex++);
    }

    public String arrange(int index) {
        if(arrangeIndex != (index + 1)) arrangeIndex = index + 1;
        int cows = Master.cows(answer);
        int bulls = Master.bulls(answer);
        int iterations = 0;
        int nDigits;
        char sym;
        if(answer == 0) {
            String zeros = "";
            if(iterations == index) {
                while (zeros.length() < length) {
                    zeros += "0";
                }
            }
            return zeros;
        }
        if((cows > 0 && bulls == 0) || (cows == 0 && bulls > 0)) {
            if(cows > 0) {
                nDigits = cows;
                sym = 'c';
            } else {
                nDigits = bulls;
                sym = 'b';
            }
            for(int i = 1; i < Math.pow(2, length); ++i) {
                String bin = binaryString(i);
                if(symbolsCount(bin, '1') == nDigits) {
                    if(iterations == index) {
                        while(bin.length() < length) bin = "0" + bin;
                        bin = bin.replace('1', sym);
                        return bin;
                    }
                    iterations++;
                }
            }
        } else if(cows > 0 && bulls > 0) {
            for(int i = 0; i < Math.pow(3, length); ++i) {
                String three = ternaryString(i);
                if(symbolsCount(three, '1') == cows && symbolsCount(three, '2') == bulls) {
                    if(iterations == index) {
                        while(three.length() < length) three = "0" + three;
                        three = three.replace('2', 'b');
                        three = three.replace('1', 'c');
                        return three;
                    }
                    iterations++;
                }
            }
        }

        return "";
    }

    private int answer;
    private int length;
    private int arrangeIndex;
}
