package jaro.ev3dev;

public class MutableObject<T> {

    private T value;

    public MutableObject() {
        // keeping the value null
    }

    public MutableObject(final T value) {
        this.value = value;
    }

    public boolean hasValue() {
        return (this.value != null);
    }

    public T getValue() {
        return value;
    }

    public void setValue(final T value) {
        this.value = value;
    }
}
