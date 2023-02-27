import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.time.Duration;

public class SeleniumTests {
    @Ignore
    @Test
    public void implicitTimeOut() {
        //NO PAGE OBJECT MODEL
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://www.practice.bpbonline.com/");

        /*<span class="ui-button-text">My Account</span>*/
        driver.findElement(By.linkText("My Account")).click();

        /*<input type="text" name="email_address">*/
        driver.findElement(By.name("email_address")).sendKeys("bpb@bpb.com");

        /*<input type="password" name="password" maxlength="40">*/
        driver.findElement(By.name("password")).sendKeys("bpb@123");

        /*<button id="tdb1" type="submit" class="ui-button ui-widget ui-state-default ui-corner-all ui-button-text-icon-primary ui-priority-primary" role="button" aria-disabled="false"><span class="ui-button-icon-primary ui-icon ui-icon-key"></span><span class="ui-button-text">Sign In</span></button>*/
        driver.findElement(By.id("tdb1")).click();

        /*<span class="ui-button-text">Log Off</span>*/
        driver.findElement(By.linkText("Log Off")).click();


        //<span class="ui-button-text">Continue</span>
        driver.findElement(By.linkText("Continue")).click();

        //close current window, if it is only one close driver too
        //driver.close();

        //close all windows, close whole driver
        driver.quit();
    }

    private static void logUser(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        driver.get("http://www.practice.bpbonline.com/");

        driver.findElement(By.linkText("My Account")).click();
        driver.findElement(By.name("email_address")).sendKeys("bpb@bpb.com");
        driver.findElement(By.name("password")).sendKeys("bpb@123");
        driver.findElement(By.id("tdb1")).click();
    }
    @Test
    public void waitUntilTest() {
        //NO PAGE OBJECT MODEL
        WebDriver driver = new ChromeDriver();
        logUser(driver);


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        WebDriverWait webDriverWait = new WebDriverWait(driver,Duration.ZERO);
        //NOTE until(Function<? super T, V> isTrue) takes functional interface -> function taking argument T and returning V
        //presenceOfElementLocated is static method, therefore there is no "new" operator
        //until is only stupid indefinite while, escaping in case that element was found
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Log Off")));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.linkText("Log Off")).click();

        driver.findElement(By.linkText("Continue")).click();
        //THIS WILL FAIL, THROW EXCEPTION
        //webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("JUNK")));
        driver.quit();
    }

    @Test
    public void formsTest() {
        //NO PAGE OBJECT MODEL
        WebDriver driver = new ChromeDriver();
        logUser(driver);

        driver.findElement(By.linkText("View or change my account information.")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//input[@value='m']")).isSelected());
        var telephoneElement = driver.findElement(By.name("telephone"));
        telephoneElement.clear();
        telephoneElement.sendKeys("0902999666");
        WebElement w1 = driver.findElement(By.id("tdb5"));//
        WebElement w2 = driver.findElement(By.xpath("//button//*[contains(., 'Continue')]"));
        System.out.println(w1.getText());
        System.out.println(w2.getText());

        //they differ by one pixel
        System.out.println(w1.getLocation());
        System.out.println(w2.getLocation());
        Assert.assertEquals(w1.getText(),(w2.getText()));
        w2.click();
        //NOTE IT IS REALLY HTML SOURCE OF PAGE
        //System.out.println(driver.getPageSource());

        Assert.assertTrue(driver.getPageSource().contains("Your account has been successfully updated."));



        driver.quit();
    }

    @Test
    public void dragAndDropActionTest() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().window().maximize();


        driver.get("https://jqueryui.com/droppable/");
        // is this necessary?
        //JavascriptExecutor executor =  (JavascriptExecutor) driver;
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
        driver.switchTo().frame(driver.findElement(By.className("demo-frame")));

        WebElement drag = driver.findElement(By.id("draggable"));
        WebElement drop = driver.findElement(By.id("droppable"));
        Actions actions = new Actions(driver);
        actions.dragAndDrop(drag,drop).build().perform();

        driver.quit();
    }

    @Test
    public void compositeActionsTest() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/key_presses");
        WebElement textBox = driver.findElement(By.id("target"));
        Actions actions = new Actions(driver);
        actions.sendKeys(textBox,Keys.TAB).sendKeys(textBox,Keys.SPACE).build().perform();

        driver.quit();
    }

}
