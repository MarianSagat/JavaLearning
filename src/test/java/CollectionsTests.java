import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CollectionsTests
{
    @Test
    public void preserveOrderOfInsertInMap()
    {
        Map<String, Integer> mapPreserveOrder = new LinkedHashMap<>();
        mapPreserveOrder.put("First", 1);
        mapPreserveOrder.put("Second", 2);
        mapPreserveOrder.put("Third", 3);
        mapPreserveOrder.put("Fourth", 4);

        Assertions.assertIterableEquals(
                List.of(1, 2, 3, 4),
                mapPreserveOrder
                        .entrySet()
                        .stream()
                        .map(Map.Entry::getValue)
                        .toList()
        );

        Map<String, Integer> classicMap = new HashMap<>();
        classicMap.put("First", 1);
        classicMap.put("Second", 2);
        classicMap.put("Third", 3);
        classicMap.put("Fourth", 4);

        Assertions.assertIterableEquals(
                List.of(2,3, 1, 4),
                classicMap
                        .entrySet()
                        .stream()
                        .map(Map.Entry::getValue)
                        .toList());
    }
}
