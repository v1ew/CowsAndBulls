package v1ew.cowsandbulls;

import java.util.ArrayList;

/**
 * Created by Shakhov on 28.06.2016.
 * Экспериментальный класс - вариации на тему представления стада коров и быков
 */
public class Bevy {
    public Bevy(String number) {
        this.number = number;
        cows = new ArrayList<>();
        bulls = new ArrayList<>();
    }

    public void dropAll() {
        cows.clear();
        bulls.clear();
    }

    public int countCows() {
        return cows.size();
    }

    public int countBulls() {
        return bulls.size();
    }

    public int countAll() {
        return bulls.size() + cows.size();
    }

    public boolean addBull(int bullIndex) {
        if(cows.contains(bullIndex)) cows.remove(new Integer(bullIndex));
        return addAnimal(bulls, bullIndex);
    }

    public boolean isCow(int index) {
        return cows.contains(new Integer(index));
    }

    public boolean addCow(int cowIndex) {
        if(!isIndexExists(bulls, cowIndex))
            return addAnimal(cows, cowIndex);
        return false;
    }

    public boolean moveAnimal(ArrayList<Integer> list, int from, int to) {
        if(list.contains(from)) {
            int index = list.indexOf(from);
            list.set(index, to);
            return true;
        }
        return false;
    }

    private boolean addAnimal(ArrayList<Integer> list, int index) {
        if(!isIndexExists(list, index) &&
                countAll() < number.length() && index < number.length()) {
            list.add(index);
            return true;
        }
        return false;
    }

    private void removeIndex(ArrayList<Integer> list, int index) {
        list.remove(new Integer(index));
    }

    private boolean isIndexExists(ArrayList<Integer> list, int index) {
        return list.contains(index);
    }

    public String toString() {
        String result = "";
        for(int i = 0; i < number.length(); ++i) {
            result += number.charAt(i);
            if(cows.contains(i)) result += "c";
            if(bulls.contains(i)) result += "b";
        }
        return result;
    }

    private ArrayList<Integer> cows;
    private ArrayList<Integer> bulls;
    private final String number;
}
