public class GenericType<T> {
    GenericType(T type) {
        this.type = type;
    }

    T getType() {
        return  type;
    }

    String getObjTypeName() {
        return  type.getClass().getTypeName();
    }
    final private T type;
}
