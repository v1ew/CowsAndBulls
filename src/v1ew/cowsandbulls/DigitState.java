package v1ew.cowsandbulls;

/**
 * Created by Shakhov on 16.06.2016.
 */
public enum DigitState {
    FREE("Free"),
    USED("Used"),
    BULL("Bull"),
    COW("Cow"),
    GLOBAL_DELETED("GD"),
    LOCAL_DELETED("LD");

    DigitState(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    private final String name;
}
