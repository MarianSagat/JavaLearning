//NOTE: generic argument implementing class has to extend Comparable<T>,
// if MinMax<T extends Comparable<T>> is replaced with  MinMax<T> and T is used as return type from min, max
// file doesnt compile
// SEE MinMaxError
public class MinMax<T extends Comparable<T>> implements IMinMax<T> {
    T[] values;

    public MinMax(T[] values) {
        this.values = values.clone();
    }

    @Override
    public T min() {
        T min = values[0];
        for (T value : values) {
            if (value.compareTo(min)<0)
                min = value;
        }
        return min;
    }

    @Override
    public T max() {
        T max = values[0];
        for (T value : values) {
            if (value.compareTo(max)>0)
                max = value;
        }
        return max;
    }
}
