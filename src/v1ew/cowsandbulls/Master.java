package v1ew.cowsandbulls;

/**
 * Created by Shakhov on 14.06.2016.
 * Класс Master хранит загаданное число и отвечает на вопросы с вариантами количеством быков и коров.
 * Также содержит статические методы, помогающие выделить из ответа коров и быков.
 */
public class Master {
    /**
     * Заданное число передается конструктору в виде строки
     */
    public Master(String number) {
        this.number = number;
    }

    /**
     * Версия передается в виде строки
     * Ответ в виде числа:
     * 0 - нет совпадений
     * единицы - количество коров
     * десятки - количество быков
     */
    public int ask(String guess) {
        int cows = 0;
        int bulls = 0;
        for(int i = 0; i < guess.length(); ++i) {
            char ch = guess.charAt(i);
            for (int j = 0; j < number.length(); ++j) {
                if (ch == number.charAt(j)) {
                    if (i == j) {
                        bulls++;
                    } else {
                        cows++;
                    }
                }
            }
        }
//        System.out.println(++counter + ". Asked " + guess + ", is a " + bulls + " bulls and " + cows + " cows.");
        return bulls * 10 + cows;
    }

    /**
     * Коровы - количество единиц - остаток от деления на 10
     */
    public static int cows(int answer) {
        return answer % 10;
    }

    /**
     * Быки - количество десятков - остаток от деления на 100 минус количество единиц
     */
    public static int bulls(int answer) {
        return (answer /10) % 10;
    }

    private String number;
    private long counter = 0;
}
