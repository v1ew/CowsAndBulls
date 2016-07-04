package v1ew.cowsandbulls;

/**
 * Created by Shakhov on 15.06.2016.
 * Класс используется для генерации и перебора распределения коров и быков по числу.
 * Например, мы получили, что в числе 1234 всего 1 корова, тогда варианты распределения будут следующие
 * 1) корова - 1, 234 - вычеркиваем, 2) корова - 2, 134 вычеркиваем, и т.д.
 */
public class Arranger {
    /**
     * Конструктор сохраняет переданные значения и обнуляет счетчик распределений.
     * @param answer ответ "Мастера" в виде двухзначного десятичного числа, где единицы - коровы, десятки - быки
     * @param length длина загадываемого числа
     */
    public Arranger(int answer, int length) {
        this.length = length;
        this.answer = answer;
        arrangeIndex = 0;
    }

    public void restart() {
        arrangeIndex = 0;
    }

    /**
     * Возвращает двоичное представление числа
     * @param number число
     * @return строка, содержащая двоичное представление числа
     */
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

    /**
     * Возвращает троичное представление числа
     * @param number число
     * @return строка, содержащая троичное представление числа
     */
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

    /**
     * Считает количество символов в строке
     * @param where строка символов
     * @param what символ, количество вхождений которого надо посчитать
     * @return сколько раз символ встретился в заданной строке
     */
    private int symbolsCount(String where, char what) {
        int counter = 0;

        for(int i = 0; i < where.length(); ++i) {
            if(where.charAt(i) == what) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Для получения очередного распределения используем внутренний счетчик
     * @return распределение
     */
    public String arrange() {
        return arrange(arrangeIndex++);
    }

    /**
     * Когда в ответе только быки или только коровы, для получения распределения достаточно двоичной формы,
     * где 1 - значимая цифра (корова или бык), 0 - вычеркиваемая цифра. Если же есть и быки и коровы, то для
     * получения их распределения нужна троичная форма.
     * Для поиска очередного распределения перебираем числа по порядку от 1 до 2 или 3 в степени = количеству цифр в
     * числе.
     * @param index номер распределения по порядку
     * @return распределение в виде строки
     */
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
