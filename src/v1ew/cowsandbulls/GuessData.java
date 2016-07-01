package v1ew.cowsandbulls;

/**
 * Created by Shakhov on 30.06.2016.
 */
public class GuessData {
    /**
     * Класс GuessData хранит оперативную информацию по каждой итерации с вариантом и ответом.
     * @param guess вариант.
     * @param answer ответ.
     */
    public GuessData(String guess, int answer) {
        this.answer = answer;
        this.guess = guess;
        arranger = new Arranger(answer, Guesser.NUMBER_LENGTH);
        digits = new Digits();
    }

    public String getGuess() {
        return guess;
    }

    public int getAnswer() {
        return answer;
    }

    public void saveDigits(Digits digitsToSave) {
        digits.copyFrom(digitsToSave);
    }

    public void restoreDigits(Digits digitsToLoad) {
        digits.copyTo(digitsToLoad);
    }

    public String getArrange() {
        return arranger.arrange();
    }

    public void resetArrange() {
        arranger.restart();
    }

    private String guess;
    private int answer;
    private Arranger arranger;
    private Digits digits;
}
