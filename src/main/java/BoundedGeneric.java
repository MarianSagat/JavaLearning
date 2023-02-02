public class BoundedGeneric<T extends Number> {
    BoundedGeneric(T[] numbers) {
        this.numbers = numbers.clone();
    }

    double calculateAverage() {
        double sum = 0.0;
        for (var val : numbers) {
            sum+=val.doubleValue();
        }
        return  sum/numbers.length;
    }


    final private T[] numbers;
}
