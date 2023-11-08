import net.sf.jasperreports.olap.mapping.Tuple;
import org.apache.tools.ant.types.resources.selectors.InstanceOf;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;
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
    void testTransform_11()
    {
        Set<Integer> stringSet = List.of(0, 1, 2, 3).stream()
                .map(i -> 2 * i)
                .map(integer -> integer+1)
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

        elements.forEach(e -> System.out.println(e.getValue()));
    }

    @Test
    public void streamTest()
    {
        Stream.of(new Section("Section"), new Section("Section 2"), "234")
                .map(value -> (value instanceof Section ? ((Section) value).sectionName : value))
                .forEach(System.out::println);
    }

    @Test
    public void streamTest2()
    {
        //still cannot convert simply into one stream if we dont want to use some flat map if(instanceof...)
        //stream of {string,stream}
        Section[] sections = new Section[]{new Section("Section"), new Section("Section 2")};
        Stream.of(new String("bla bla"),
                        Stream.of(sections)
                                .map(Section::getValue)
                                .toList()
                )
                .toList()
                .forEach(System.out::println);
    }

    @Test
    public void streamTest3()
    {
        Arrays.asList("123",
                        Stream.of("234", 345)
                                .toList())
                .forEach(System.out::println);
    }

    @Test
    public void streamTest4()
    {
        //it is ugly but the best solution which i found
        Section[] sections = new Section[]{new Section("Section"), new Section("Section 2")};
        Stream.concat(
                        Stream.of(new String("bla bla")),
                        Stream.of(sections)
                                .map(Section::getValue)
                )
                .toList()
                .forEach(System.out::println);
    }

    @Test
    public void reduce() throws ParseException {
       System.out.println(List.of(1,2,3,4,5).stream().reduce(Integer::sum));
        NumberFormat.getNumberInstance(java.util.Locale.US).parse("265,858");
    }
}
