package v1ew.cowsandbulls;

import java.util.ArrayList;

/**
 * Created by Shakhov on 21.06.2016.
 * Предоставляет итератор по базе вариантов и ответов.
 */
public class GuessStore {
    public GuessStore() {
        dataArrayList = new ArrayList<>();
    }

    public boolean hasNext() {
        return (storeIndex + 1) < dataArrayList.size();
    }

    public boolean hasPrev() {
        return storeIndex > 0;
    }

    public void moveNext() {
        if(hasNext())
            storeIndex++;
    }

    public void movePrev() {
        if(hasPrev())
            storeIndex--;
    }

    public void saveDigits(Digits digitsToSave) {
        dataArrayList.get(storeIndex).saveDigits(digitsToSave);
    }

    public void restoreDigits(Digits digitsToLoad) {
        dataArrayList.get(storeIndex).restoreDigits(digitsToLoad);
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

    public String getGuess() {
        return dataArrayList.get(storeIndex).getGuess();
    }

    public int getAnswer() {
        return dataArrayList.get(storeIndex).getAnswer();
    }

    public String getArrange() {
        return dataArrayList.get(storeIndex).getArrange();
    }

    public void arrangeIndexReset() {
        dataArrayList.get(storeIndex).resetArrange();
    }

    public void restart() {
        storeIndex = 0;
        for(GuessData guessData: dataArrayList) {
            guessData.resetArrange();
        }
    }

    private ArrayList<GuessData> dataArrayList;
    private int storeIndex = 0;
}
