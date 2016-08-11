package v1ew.cowsandbulls;

import java.util.ArrayList;
import java.util.Arrays;

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
        arrangeIndex = 0;
        arranges = new ArrayList<>();
        generateArranges(answer, length);
    }

    /**
     * Генерирует все возможные распределения, используя перестановки.
     * @param answer ответ мастера (десятки - быки, единицы - коровы)
     * @param length длина числа
     */
    private void generateArranges(int answer, int length) {
        Digit[] number = new Digit[length];
        int cows = Master.cows(answer);
        int bulls = Master.bulls(answer);
        for(int i = 0; i < length; ++i) {
            if(bulls-- > 0) {
                number[i] = new Digit(2);
            } else if(cows-- > 0) {
                number[i] = new Digit(1);
            } else {
                number[i] = new Digit(0);
            }
        }
        Arrays.sort(number);
        Permutator permutator = new Permutator(number);
        String perm = permutator.nextPerm();
        boolean arrangeFound;
        while(perm != "") {
            arrangeFound = false;
            perm = perm.replace('1', 'c');
            perm = perm.replace('2', 'b');
            for(String arrange: arranges) {
                if(arrange.equals(perm)) {
                    arrangeFound = true;
                    break;
                }
            }
            if(!arrangeFound) {
                arranges.add(perm);
            }
            perm = permutator.nextPerm();
        }
    }

    /**
     * Возвращаемся к первому распределению
     */
    public void restart() {
        arrangeIndex = 0;
    }

    /**
     * Для получения очередного распределения используем внутренний счетчик
     * @return распределение
     */
    public String arrange() {
        if(arrangeIndex < arranges.size())
            return arranges.get(arrangeIndex++);
        return "";
    }

    /**
     * Получить распределение по индексу.
     * @param index номер распределения по порядку
     * @return распределение в виде строки
     */
    public String arrange(int index) {
        if(index < arranges.size())
            return arranges.get(index);
        return "";
    }

    private int arrangeIndex;
    private ArrayList<String> arranges;
}
