public class GenericDerived<T> extends GenericBase<T> {
    GenericDerived(T value) {
        super(value);
    }

    @Override
    public T getValue() {
        System.out.println("printed from Derived");
        return  super.value;
    }
}
