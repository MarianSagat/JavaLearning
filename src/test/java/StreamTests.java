import net.sf.jasperreports.olap.mapping.Tuple;
import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTests
{
    @Test
    void testTransform()
    {
        Set<String> stringSet = List.of(0, 1, 2, 3).stream()
                .map(i -> 2 * i)
                .map(String::valueOf)
                .collect(Collectors.toCollection(HashSet::new));

        stringSet.forEach(e -> System.out.println(e));
    }

    @Test
    void testTransform2()
    {
        List<String> stringSet = List.of(0, 1, 2, 3).stream()
                .map(i -> "item" + i)
                .flatMap(s -> Stream.of(s, s + "Extra"))
                .collect(Collectors.toList());

        stringSet.forEach(e -> System.out.println(e));
    }

    public interface IElement
    {
        String getValue();
    }

    public class Section implements IElement
    {

        private String sectionName;

        Section(String sectionName){this.sectionName = sectionName;}

        @Override
        public String getValue()
        {
            return sectionName;
        }
    }

    public class Paragraph implements IElement
    {
        private String paragraphName;

        Paragraph(String paragraphName){this.paragraphName = paragraphName;}

        @Override
        public String getValue()
        {
            return paragraphName;
        }
    }

    public record ElementBasicInfo(String sectionName, String paragraphName){}

    ;

    @Test
    public void testFlatMap()
    {
        List<ElementBasicInfo> elementsInfo = List.of(
                new ElementBasicInfo("Section", "new world"),
                new ElementBasicInfo("Section 2nd", "new city"),
                new ElementBasicInfo("Section 3nd", "new house")
        );

        List<IElement> elements = elementsInfo.stream().flatMap(
                info ->
                {
                    return Stream.of(new Section(info.sectionName), new Paragraph(info.paragraphName));
                })
                .toList();

        elements.forEach(e->System.out.println(e.getValue()));
    }
}
