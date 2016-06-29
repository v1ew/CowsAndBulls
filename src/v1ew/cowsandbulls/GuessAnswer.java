package v1ew.cowsandbulls;

/**
 * Created by Shakhov on 21.06.2016.
 */
public class GuessAnswer {
    public GuessAnswer (String guess, int answer) {
        this.guess = guess;
        this.answer = answer;
    }

    public String getGuess() {
        return guess;
    }

    public int getAnswer() {
        return answer;
    }

    private String guess;
    private int answer;
}
