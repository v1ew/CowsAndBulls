package v1ew.cowsandbulls;

/**
 * Created by Shakhov on 14.06.2016.
 * Класс Guesser анализирует предыдущие версии и ответы на них.
 * Затем формирует новую версию.
 * Содержит две константы, используемые в других классах.
 */
public class Guesser {
    /**
     * Конструктор создает объекты:
     * 1) для хранения состояния, в виде набора цифр, и
     * 2) для хранения оперативной информации и списка пар из вариантов и ответов
     */
    public Guesser() {
        digits = new Digits();
    }

    /**
     * Метод mergeStates переносит информацию из очередной версии в состояние набора цифр.
     * @param number версия в виде числа
     * @param arrange распределение коров и быков в передаваемом числе
     */
    public void mergeStates(String number, String arrange) {
        Number numberDigits = new Number(number, digits);
        for(int i = 0; i < arrange.length(); ++i) {
            switch(arrange.charAt(i)) {
                case 'b':
                    numberDigits.getDigit(i).setBull(i);
                    break;
                case 'c':
                    if(!numberDigits.getDigit(i).isBull())
                        numberDigits.getDigit(i).setCow(i);
                    break;
                default:
                    if(numberDigits.getDigit(i).isFree()) {
                        numberDigits.getDigit(i).delete();
                    }
                    break;
            }
        }
//        System.out.print("mergeStates:");
//        digits.print();
    }

    /**
     * Проверяет корректность распределения коров и быков, учитывая текущее состояние набора цифр.
     * Для проверки анализируются отдельные признаки, говорящие о возможности перехода от предыдущих вариантов к
     * текущему. В том числе, расчитывается количество коров и быков, которое будет после применения распределения.
     * @param arrange распределение коров и быков в формате "0bc0", где 0 - означает, что соответствующая цифра не
     *                является значимой, b - бык, c - корова в передаваемом числе.
     * @param number число-вариант, к которому относится распределение.
     * @return Положительный ответ говорит о том, что данное распределение согласуется с текущим состоянием и его можно
     * применить, используя mergeStates.
     */
    public boolean isArrangeCorrect(String arrange, String number) {
        Number numberDigit = new Number(number, digits);

        // Проверим общее поголовье, получаемое после применения этого расклада
        // Для этого просуммируем количество голов, не входящих в этот номер и количество голов расклада
        int countCowsAndBullsNotInNumber = 0;
        for(int i = 0; i < DIGITS_LENGTH; ++i) {
            if(digits.getDigit(i).isCow() || digits.getDigit(i).isBull()) {
                boolean inNumber = false;
                for(int j = 0; j < numberDigit.getLength(); ++j) {
                    if(digits.getDigit(i).getDigit() == numberDigit.getDigit(j).getDigit()) {
                        inNumber = true;
                        break;
                    }
                }
                if(!inNumber) {
                    countCowsAndBullsNotInNumber++;
                }
            }
        }
        int countCowsAndBullsInArrange = 0;
        for(int i = 0; i < arrange.length(); ++i) {
            if(arrange.charAt(i) != '0')
                countCowsAndBullsInArrange++;
        }
        if(countCowsAndBullsNotInNumber + countCowsAndBullsInArrange > NUMBER_LENGTH)
            return false;

        // Выполним проверки каждой цифры варианта по отдельным признакам
        for(int i = 0; i < numberDigit.getLength(); ++i) {
            // бык на своем месте не бык
            if(numberDigit.getDigit(i).isBull(i) && arrange.charAt(i) != 'b')
                return false;
            // бык на другом месте не корова
            if(numberDigit.getDigit(i).isBull()
                    && numberDigit.getDigit(i).getPosition() != i && arrange.charAt(i) != 'c')
                return false;
            // корова стала ни кем
            if(numberDigit.getDigit(i).isCow() && arrange.charAt(i) == '0')
                return false;
            // на месте быка другой бык
            if(numberDigit.getDigit(i).isBull() && numberDigit.getDigit(i).getPosition() != i
                    && arrange.charAt(numberDigit.getDigit(i).getPosition()) == 'b')
                return false;
            // на месте отсутствующего в этом номере быка пытается появиться другой бык
            if(arrange.charAt(i) == 'b') {
                for(int j = 0; j < DIGITS_LENGTH; ++j) {
                    if(!digits.getDigit(j).equals(numberDigit.getDigit(i).getDigit()) && digits.getDigit(j).isBull(i)) {
                        return false;
                    }
                }
            }
            // корова не может стать на том же месте быком
            if(arrange.charAt(i) == 'b') {
                if(numberDigit.getDigit(i).isCow(i))
                    return false;
            }
            // удаленная цифра становится кем-либо
            if(numberDigit.getDigit(i).isDeleted() && arrange.charAt(i) != '0')
                return false;
        }
        return true;
    }

    /**
     * Главный метод, который выполняет всю основную работу.
     * @return Возвращает новый вариант в виде числа, представленного строкой. При невозможности сгенерировать
     * новый вариант, пока вызывает System.exit(0). В дальнейшем будет исправлено на генерацию исключения.
     */
    public String guess(GuessStore guessStore) throws Exception {
        String newGuessString = "";

        if(guessStore.guessCount() == 0)
            return tryToMakeGuess();

        guessStore.restart();
        digits.freeAll();

        boolean arrangeFound = false;
        do {
            String number = guessStore.getGuess();
            String arrange = guessStore.getArrange();
            while(arrange.length() > 0) {
                if(isArrangeCorrect(arrange, number)) {
                    arrangeFound = true;
                    break;
                }
                arrange = guessStore.getArrange();
            }
            if(arrangeFound) {
                arrangeFound = false;
                guessStore.saveDigits(digits);
                mergeStates(number, arrange);

                if(guessStore.hasNext()) {
                    guessStore.moveNext();
                } else {
                    newGuessString = tryToMakeGuess();
                    guessStore.restoreDigits(digits); // need if newGuessString.length() < NUMBER_LENGTH
                }
            } else {
                guessStore.arrangeIndexReset();
                if(guessStore.hasPrev()) {
                    guessStore.movePrev();
                    guessStore.restoreDigits(digits);
                } else {
                    throw new Exception("Решение не найдено!");
                }
            }
        } while(newGuessString.length() < NUMBER_LENGTH);

        return newGuessString;
    }

    /**
     * После того, как будут проанализированы все варианты с ответами, а результат анализа будет отражен в текущем
     * состоянии наора цифр, пробуем сформировать новый вариант. Т.к. возможность применить очередной расклад не
     * гарантирует возможность сформировать полноценный вариант.
     * Здесь используется класс Permutator для генерации перестановок, чтобы ни одна корова не стояла повторно на
     * одной позиции.
     * @return Возвращает новый вариант, который может оказаться не полным из-за недостатка свободных цифр.
     */
    private String tryToMakeGuess() {
        String guessString = "";
        Digit guessDigits[] = new Digit[NUMBER_LENGTH];
        boolean freeDigitUsed[] = new boolean[DIGITS_LENGTH];

        // Расставляем по местам всех быков
        for (int digitsIndex = 0; digitsIndex < DIGITS_LENGTH; ++digitsIndex) {
            if (digits.getDigit(digitsIndex).isBull())
                guessDigits[digits.getDigit(digitsIndex).getPosition()] = digits.getDigit(digitsIndex);
        }
        // Теперь пытаемся расставить всех коров и заполнить оставшиеся места
        switch (digits.cowsCount()) {
            case 0:
                break;
            case 1:
                cowToPlace:
                for (int digitsIndex = 0; digitsIndex < DIGITS_LENGTH; ++digitsIndex) {
                    if (digits.getDigit(digitsIndex).isCow()) {
                        for (int guessDigitIndex = 0; guessDigitIndex < NUMBER_LENGTH; ++guessDigitIndex) {
                            if(guessDigits[guessDigitIndex] == null && !digits.getDigit(digitsIndex).isCow(guessDigitIndex)) {
                                guessDigits[guessDigitIndex] = digits.getDigit(digitsIndex);
                                break cowToPlace;
                            }
                        }
                    }
                }
                break;
            default: // 2-3-4 cows
                String allCows = digits.allCows();
                allCows += digits.nextFreeDigits(NUMBER_LENGTH - (allCows.length() + digits.bullsCount()));
                // Расставляя цифры, используем перестановки, чтобы не повторяться
                Permutator permutator = new Permutator(allCows);
                String perm = permutator.nextPerm();
                while(perm.length() > 0){
                    Number number = new Number(perm);
                    int sedCowsCount = 0;
                    checkPermStart:
                    for(int numberIndex = 0; numberIndex < number.getLength(); ++numberIndex) {
                        for (int guessDigitIndex = 0; guessDigitIndex < NUMBER_LENGTH; ++guessDigitIndex) {
                            if(guessDigits[guessDigitIndex] == null) {
                                if(digits.getDigit(number.getDigit(numberIndex).getDigit()).isCow(guessDigitIndex)) {
                                    break checkPermStart;
                                } else {
                                    guessDigits[guessDigitIndex] = digits.getDigit(number.getDigit(numberIndex).getDigit());
                                    sedCowsCount++;
                                    break;
                                }
                            }
                        }
                    }
                    if(sedCowsCount == NUMBER_LENGTH - digits.bullsCount()) {
                        break;
                    } else {
                        for (int guessDigitIndex = 0; guessDigitIndex < NUMBER_LENGTH; ++guessDigitIndex) {
                            if (guessDigits[guessDigitIndex] != null &&
                                    (guessDigits[guessDigitIndex].isCow() || guessDigits[guessDigitIndex].isFree())) {
                                guessDigits[guessDigitIndex] = null;
                            }
                        }
                    }
                    perm = permutator.nextPerm();
                }
                break;
        }
        // Расставляем оставшиеся цифры, при необходимости
        for (int guessDigitIndex = 0; guessDigitIndex < NUMBER_LENGTH; ++guessDigitIndex) {
            if(guessDigits[guessDigitIndex] == null) {
                for (int digitsIndex = 0; digitsIndex < DIGITS_LENGTH; ++digitsIndex) {
                    if (digits.getDigit(digitsIndex).isFree() && !freeDigitUsed[digitsIndex]) {
                        guessDigits[guessDigitIndex] = digits.getDigit(digitsIndex);
                        freeDigitUsed[digitsIndex] = true;
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < NUMBER_LENGTH; ++i) {
            if (guessDigits[i] != null) {
                guessString += guessDigits[i].getDigitString();
            }
        }

        return guessString;
    }

    /**
     * Набор цифр, хранящий текущее состояние в процессе анализа истории вариантов и ответов
     */
    public Digits digits;
    /**
     * Количество цифр в загадываемом числе.
     */
    public static final int NUMBER_LENGTH = 4;
    /**
     * Количество цифр в наборе
     */
    static final int DIGITS_LENGTH = 10;
}
