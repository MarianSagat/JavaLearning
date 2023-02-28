import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.time.Duration;
import java.util.List;
import java.util.stream.IntStream;

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

    /* TODO use testNg template tests, because these 3 tests are the same, only difference is cssSelector and expectedResults, same code */
}
