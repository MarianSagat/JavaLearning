public class GenericBase<T> {
    T value;
    GenericBase(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
