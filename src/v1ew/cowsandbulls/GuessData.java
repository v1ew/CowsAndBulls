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
    // TODO
    // Заменить класс GuessAnswer на GuessData и, вероятно, избавиться от GuessStore.
    // Вместо него использовать ArrayList и Iterator для навигации по записям.
    public String getGuess() {
        return guess;
    }

    public int getAnswer() {
        return answer;
    }

    private String guess;
    private int answer;
    private Arranger arranger;
    private Digits digits;
}
