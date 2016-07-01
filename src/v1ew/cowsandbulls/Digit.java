package v1ew.cowsandbulls;

/**
 * Created by Shakhov on 21.06.2016.
 * Класс Digit представляет цифру, имеющую статус и позицию. Для коров доступна история по занимаемым ранее позициям.
 * История учитывается при подготовке нового варианта.
 */
public class Digit implements Comparable<Digit> {
    public Digit(int digit) {
        this.digit = digit;
        positions = new boolean[Guesser.NUMBER_LENGTH];
        free();
    }

    // TODO
    // Необходимо оптимизировать эту функцию
    public void copyTo(Digit digitToCopy) {
        digitToCopy.digit = this.digit;
        digitToCopy.state = this.state;
        digitToCopy.position = this.position;
        for(int i = 0; i < Guesser.NUMBER_LENGTH; ++i) {
            digitToCopy.positions[i] = this.positions[i];
        }
    }

    public int compareTo(Digit d) {
        if(this.digit < d.digit) return -1;
        else if(this.digit == d.digit) return 0;
        else return 1;
    }

    public void free() {
        setState(DigitState.FREE, -1);
    }

    public boolean equals(char ch) {
        String digitString = "" + digit;
        return ch == digitString.charAt(0);
    }

    public boolean isDeleted() {
        return state == DigitState.LOCAL_DELETED;
    }

    public boolean isFree() {
        return state == DigitState.FREE;
    }

    public boolean isCow() {
        return state == DigitState.COW;
    }

    // Возвращает, стояла ли корова на указанном месте
    // Не обязательно, то место, где она сейчас стоит
    public boolean isCow(int position) {
        return state == DigitState.COW && this.positions[position];
    }

    public boolean isBull() {
        return state == DigitState.BULL;
    }

    public boolean isBull(int position) {
        return state == DigitState.BULL && this.position == position;
    }

    public boolean setCow(int position) {
        return setState(DigitState.COW, position);
    }

    public boolean setBull(int position) {
        return setState(DigitState.BULL, position);
    }

    public void delete() {
        setState(DigitState.LOCAL_DELETED, position);
    }

    private boolean setState(DigitState state, int position) {
        this.state = state;
        this.position = position;
        if(this.position < 0) {
            for (int i = 0; i < Guesser.NUMBER_LENGTH; ++i) {
                this.positions[i] = false;
            }
        } else {
            this.positions[this.position] = true;
        }

        return true;
    }

    public int getDigit() {
        return digit;
    }

    public String getDigitString() {
        return digit + "";
    }

    public int getPosition() {
        return position;
    }

    public DigitState getState() {
        return state;
    }

    public boolean equals(int x) {
        return x == digit;
    }

    @Override
    public String toString() {
        return " " + digit + "[" + state + "(" + position + ")]";
    }

    private DigitState state;
    private int position;
    private int digit;
    private boolean[] positions;
}
