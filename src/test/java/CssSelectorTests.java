import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.nio.channels.Selector;
import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

//https://www.dofactory.com/css/ref/selectors
//https://www.digitalocean.com/community/tutorials/css-css-selectors
public class CssSelectorTests {


    final Path path_1 = Path.of("src/test/testResources/page_1.html");

    /**
     * <p>div > p</p>
     * <p>
     * p is direct child of div
     **/
    @Test
    public void div_greater_p() {
        WebDriver driver = new ChromeDriver();
        driver.get(path_1.toUri().toString());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        List<WebElement> elements = driver.findElements(By.cssSelector("div > p"));
        elements.forEach(webElement -> System.out.println(webElement.getText()));
        Assert.assertEquals(elements.size(), 2);
        Assert.assertEquals(elements.get(0).getText(), "This is red");
        Assert.assertEquals(elements.get(1).getText(), "This is red");
        driver.quit();
    }

    private static void testElements(List<WebElement> elements, List<String> expectedResults) {
        elements.forEach(webElement -> System.out.println(webElement.getText()));
        Assert.assertEquals(expectedResults.size(), elements.size());
        //unfortunately I am not aware of how iterate nice way through 2 collections (iterators , raw for loop is not a nice way)
        IntStream.range(0, expectedResults.size()).forEach(value -> Assert.assertEquals(elements.get(value).getText(), expectedResults.get(value)));
    }

    /**
     * <p>div p</p>
     * <p>
     * p is direct or indirect child of div
     **/
    @Test
    public void div_all_descendant() {
        WebDriver driver = new ChromeDriver();
        driver.get(path_1.toUri().toString());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        List<WebElement> elements = driver.findElements(By.cssSelector("div p"));
        List<String> expectedResults = List.of("This is red", "This is red", "This is normal colored");
        testElements(elements, expectedResults);

        driver.quit();
    }

    /**
     * <p> div ~ p </p>
     */
    @Test
    public void all_siblings_after_div() {
        WebDriver driver = new ChromeDriver();
        driver.get(path_1.toUri().toString());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        List<WebElement> elements = driver.findElements(By.cssSelector("div ~ p"));
        List<String> expectedResults = List.of("This is blue, is green", "This is green", "This is green");

        testElements(elements, expectedResults);

        driver.quit();
    }


    /**
     * <p>div + p</p>
     **/
    @Test
    public void one_adjacent_sibling_immediately_after_div() {
        WebDriver driver = new ChromeDriver();
        driver.get(path_1.toUri().toString());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        List<WebElement> elements = driver.findElements(By.cssSelector("div + p"));
        List<String> expectedResults = List.of("This is blue, is green");
        testElements(elements,expectedResults);

        driver.quit();
    }

    /**
     * <p>div * p</p>
     **/
    @Test
    public void secondLevelDescendant_firstLevelArbitraryTag() {
        WebDriver driver = new ChromeDriver();
        driver.get(path_1.toUri().toString());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        List<WebElement> elements = driver.findElements(By.cssSelector("div * p"));
        List<String> expectedResults = List.of("This is normal colored");
        testElements(elements,expectedResults);

        driver.quit();
    }

    final Path basicWebPath = Path.of("src/test/testResources/Basic-Web/index.html");


    @Test
    public void locateAllImagesFromSrcDir() {
        WebDriver driver = new ChromeDriver();
        driver.get(basicWebPath.toUri().toString());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        List<WebElement> elements = driver.findElements(By.cssSelector("img[src^=img]"));
        Assert.assertEquals(elements.size(),9);

        driver.quit();
    }

    @Test
    public void locateOneImageOutsideOfDirSrc() {
        WebDriver driver = new ChromeDriver();
        driver.get(basicWebPath.toUri().toString());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        //img which attribute src NOT start with img string
        WebElement element = driver.findElement(By.cssSelector("img:not(img[src^=img])"));
        String srcAttribute = element.getAttribute("src");
        Assert.assertEquals(srcAttribute.substring(srcAttribute.lastIndexOf("/")+1).trim(), "logo6.jpg");

        //only one not more not less
        Assert.assertEquals(driver.findElements(By.cssSelector("img:not(img[src^=img])")).size(),1);
        driver.quit();
    }

    @Test
    public void twoDescendantClasses() {
        WebDriver driver = new ChromeDriver();
        driver.get(basicWebPath.toUri().toString());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        //img which attribute src NOT start with img string
        List<WebElement> elements = driver.findElements(By.cssSelector("div.col-12 p.lead"));
        Assert.assertEquals(elements.size(),1);
        WebElement foundedElement = elements.stream().findFirst().get();
        Assert.assertTrue(foundedElement.getText().isEmpty());
        //<p> containing "Welcome to my website tutorial!"
        //for now i dont know how it checked without xpath, when i

    }

    @Test
    public void severalDescendants() {
        WebDriver driver = new ChromeDriver();
        driver.get(basicWebPath.toUri().toString());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        //img which attribute src NOT start with img string
        List<WebElement> elements = driver.findElements(By.cssSelector("div div div div p"));
        Assert.assertEquals(elements.size(),3);
        WebElement foundedElement = elements.stream().findFirst().get();
        Assert.assertTrue(foundedElement.getText().isEmpty());

        //same but with simpler way
        elements = driver.findElements(By.cssSelector("div p.card-text"));
        Assert.assertEquals(elements.size(),3);
        foundedElement = elements.stream().findFirst().get();
        Assert.assertTrue(foundedElement.getText().isEmpty());
        //TODO how i get the correct one element containging text inside <p></p> withou xpath?
        driver.quit();
    }

    @Test
    public void moreComplicatedConstruction()
    {
        WebDriver driver = new ChromeDriver();
        driver.get(basicWebPath.toUri().toString());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

        //img which attribute src NOT start with img string
        List<WebElement> elements = driver.findElements(By.cssSelector("div.card-body p+a[class^=\"btn btn\"]"));
        Assert.assertEquals(elements.size(), 3);
        WebElement foundedElement = elements.stream().findFirst().get();
        Assert.assertTrue(foundedElement.getText().isEmpty());


        //UNIQUE SELECTION OF ONLY ONE FROM THESE 3
        List<WebElement> elements_2 = driver.findElements(By.cssSelector("div.card img[src*=\"team1\"]+div >a"));
        Assert.assertEquals(elements_2.size(), 1);
        WebElement foundedElement_2 = elements.stream().findFirst().get();
        Assert.assertTrue(foundedElement_2.getText().isEmpty());

        driver.quit();
        //TODO i would find if foundedElement_2 is in elements
    }

    @Test
    public void listOfSomeCssSelectors()
    {
        List<By> selectors = List.of(
                //inside table with class
                // find another element (it is table ) with summary
                //direct child tbody
                //direct child table row (tr) first child (from up to down)
                //direct child table row (tr) first child (from up to down)
                //direct child table data (td) first child
                //all indirect children with tag input (in concrete case there was only one input tag)
                new By.ByCssSelector("table[class='x1he x1i2'][summary='Opportunity Revenue Items']>tbody>tr:nth-child(1)>td:nth-child(1) input")
        );
    }
    /* TODO use testNg template tests, because these 3 tests are the same, only difference is cssSelector and expectedResults, same code */
}
