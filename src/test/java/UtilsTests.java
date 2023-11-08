import org.junit.jupiter.api.Test;

public class UtilsTests {
    @Test
    public void round() {
        System.out.println(Math.round(2.4));
        System.out.println(Math.round(2.8));
        System.out.println(Math.round((float) 1/4));
        System.out.println(Math.round((float) 1/4.0f));
        System.out.println(Math.round((float) 3/4));
        System.out.println(Math.round((float) 3/4.0f));

    }

    @Test
    public void parseFloat() {
        System.out.println(Float.parseFloat("2.4"));
    }
}
