import java.util.Arrays;

public class GenericMethod {
    static <T extends Comparable, V extends T> boolean inInside(T val, V[] values) {
        return Arrays.stream(values).anyMatch(v -> {
            return v == val;
        });
    }

    static <T extends Comparable<T>, V extends T> boolean inInsideVer2(T val, V[] values) {
        return Arrays.stream(values).anyMatch(v -> {
            return v == val;
        });
    }
}
