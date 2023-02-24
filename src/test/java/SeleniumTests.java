import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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


    @Test
    public void waitUntilTest() {
        //NO PAGE OBJECT MODEL
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://www.practice.bpbonline.com/");

        driver.findElement(By.linkText("My Account")).click();
        driver.findElement(By.name("email_address")).sendKeys("bpb@bpb.com");
        driver.findElement(By.name("password")).sendKeys("bpb@123");
        driver.findElement(By.id("tdb1")).click();


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
}
