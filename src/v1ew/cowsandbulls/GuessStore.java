package v1ew.cowsandbulls;

import java.util.ArrayList;

/**
 * Created by Shakhov on 21.06.2016.
 */
public class GuessStore {
    public GuessStore() {
        guessAnswers = new ArrayList<>();
        digitsStore = new ArrayList<>();
        arrangers = new ArrayList<>();
        guessDatas = new ArrayList<>();
    }

    public void saveDigits(int index, Digits digitsToSave) {
        Digits digits = digitsStore.get(index);
        digits.reset();
        for(int i = 0; i < digits.getLength(); ++i) {
            digitsToSave.getDigit(i).copyTo(digits.getDigit(i));
        }

    }

    public void restoreDigits(int index, Digits digits) {
        Digits digitsFromLoad = digitsStore.get(index);
        digits.reset();
        for(int i = 0; i < digits.getLength(); ++i) {
            digitsFromLoad.getDigit(i).copyTo(digits.getDigit(i));
        }
    }

    public void saveGuess(String guess, int answer) {
        guessAnswers.add(new GuessAnswer(guess, answer));
        arrangers.add(new Arranger(answer, Guesser.NUMBER_LENGTH));
        digitsStore.add(new Digits());
        guessDatas.add(new GuessData(guess, answer));
    }

    public int guessCount() {
        return guessAnswers.size();
    }

    public boolean isGuessNew(String guess) {
        for(int i = 0; i < guessAnswers.size(); ++i) {
            if(guessAnswers.get(i).getGuess().equals(guess)) {
                return false;
            }
        }
        return true;
    }

    public String getGuess(int index) {
        return guessAnswers.get(index).getGuess();
    }

    public int getAnswer(int index) {
        return guessAnswers.get(index).getAnswer();
    }

    public String getArrange(int index) {
        return arrangers.get(index).arrange();
    }

    public void arrangeIndexReset(int index) {
        arrangers.get(index).defaultIndexReset();
    }

    public void arrangesReset() {
        for(int i = 0; i < arrangers.size(); ++i) {
            arrangeIndexReset(i);
        }
    }

    private ArrayList<GuessAnswer> guessAnswers;
    private ArrayList<Arranger> arrangers;
    private ArrayList<Digits> digitsStore;
    private ArrayList<GuessData> guessDatas;
}
