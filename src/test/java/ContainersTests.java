import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class ContainersTests {
    @Test
    public void listOfPairs()
    {
        List<Map.Entry<String, String>> listPairs = List.of(Map.entry("a", "1"), Map.entry("b", "2"));
        listPairs.forEach(pair -> System.out.println(pair.getKey() + " " + pair.getValue()));
    }
}
