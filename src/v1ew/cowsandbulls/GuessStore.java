package v1ew.cowsandbulls;

import java.util.ArrayList;

/**
 * Created by Shakhov on 21.06.2016.
 */
public class GuessStore {
    public GuessStore() {
        guessAnswers = new ArrayList<>();
        arrangeIndexes = new ArrayList<>();
        digitsStore = new ArrayList<>();
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
        arrangeIndexes.add(0);
        digitsStore.add(new Digits());
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

    public GuessAnswer getGuessAnswer(int index) {
        lastAskedIndex = index + 1;
        return guessAnswers.get(index);
    }

    public GuessAnswer getGuessAnswer() {
        if(lastAskedIndex < guessAnswers.size()) {
            return guessAnswers.get(lastAskedIndex++);
        } else {
            lastAskedIndex = 0;
            return null;
        }
    }

    public void arrangeIndexReset(int index) {
        arrangeIndexes.set(index, 0);
    }

    public int getArrangeIndex(int index) {
        return arrangeIndexes.get(index);
    }

    public int getArrangeIndexWithIncrement(int index) {
        int arrangeIndex = arrangeIndexes.get(index);
        arrangeIndexes.set(index, arrangeIndex + 1);
        return arrangeIndex;
    }

    public void arrangesReset() {
        for(int i = 0; i < arrangeIndexes.size(); ++i) {
            arrangeIndexes.set(i, 0);
        }
    }

    private ArrayList<GuessAnswer> guessAnswers;
    private ArrayList<Integer> arrangeIndexes;
    private ArrayList<Digits> digitsStore;
    private int lastAskedIndex = 0;
}
