package v1ew.cowsandbulls;

import java.util.ArrayList;

/**
 * Created by Shakhov on 21.06.2016.
 */
public class GuessStore {
    public GuessStore() {
        dataArrayList = new ArrayList<>();
    }

    public void saveDigits(int index, Digits digitsToSave) {
        dataArrayList.get(index).saveDigits(digitsToSave);
    }

    public void restoreDigits(int index, Digits digitsToLoad) {
        dataArrayList.get(index).restoreDigits(digitsToLoad);
    }

    public void saveGuess(String guess, int answer) {
        dataArrayList.add(new GuessData(guess, answer));
    }

    public int guessCount() {
        return dataArrayList.size();
    }

    public boolean isGuessNew(String guess) {
        for(GuessData guessData: dataArrayList) {
            if(guessData.getGuess().equals(guess)) {
                return false;
            }
        }
        return true;
    }

    public String getGuess(int index) {
        return dataArrayList.get(index).getGuess();
    }

    public int getAnswer(int index) {
        return dataArrayList.get(index).getAnswer();
    }

    public String getArrange(int index) {
        return dataArrayList.get(index).getArrange();
    }

    public void arrangeIndexReset(int index) {
        dataArrayList.get(index).resetArrange();
    }

    public void arrangesReset() {
        for(int i = 0; i < dataArrayList.size(); ++i) {
            arrangeIndexReset(i);
        }
    }

    private ArrayList<GuessData> dataArrayList;
}
