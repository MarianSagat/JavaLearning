import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

public class EnumClassTests {

    @Test
    public void simpleMultipleValuesEnumTest() {
        MultipleValuesEnum elementHe = MultipleValuesEnum.H;

        Assert.assertTrue(Arrays.asList(MultipleValuesEnum.values()).contains(elementHe));
    }

    @Test
    public void cashedExampleTest() {
        MultipleValuesEnumCached.Element cachedElement = MultipleValuesEnumCached.Element.H;

        Assert.assertTrue(Arrays.asList(MultipleValuesEnumCached.Element.values()).contains(cachedElement));
        Assert.assertEquals(MultipleValuesEnumCached.Element.valueOfLabel("Helium").atomicNumber,2);
    }
}
