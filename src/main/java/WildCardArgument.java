
public class WildCardArgument<T extends Number> {
    WildCardArgument(T num) {
        this.num = num;
    }

    String getArgumentTypeName() {
        return num.getClass().getTypeName();
    }

    public boolean isTheSameType(WildCardArgument<?> other) {
        System.out.println("this is" + this.getClass().getName());
        System.out.println("other is" + other.getClass().getName());
        //always same :/
        //return this.getClass().getTypeName() == other.getClass().getTypeName();

        System.out.println(this.getArgumentTypeName());
        System.out.println(other.getArgumentTypeName());
        return this.getArgumentTypeName().equals(other.getArgumentTypeName());
    }

    final private T num;
}
