import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;


import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class Task8 {

    private final By USER_NAME_INPUT_FIELD = By.name("username");
    private final By PASSWORD_INPUT_FIELD = By.name("password");
    private final By LOGIN_BUTTON = By.xpath("//button[@name='login']");
    private String logIn = "admin";
    public WebDriver driver;
    public WebDriverWait wait;


    @Before
    public void doPreconditions() {
        //IE
//        System.setProperty("webdriver.ie.driver", "C:\\drivers\\IEDriverServer\\IEDriverServer.exe");
//        driver = new InternetExplorerDriver();
//        //GC
//        System.setProperty("webdriver.chrome.driver", "C:/drivers/chromedriver/chromedriver.exe");
//        driver = new ChromeDriver();

       driver = new FirefoxDriver();

        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Logging in to AC");
        driver.get("http://localhost:9000/litecart/admin/");
        driver.findElement(USER_NAME_INPUT_FIELD).sendKeys(logIn);
        driver.findElement(PASSWORD_INPUT_FIELD).sendKeys(logIn);
        driver.findElement(LOGIN_BUTTON).click();
        wait.until(elementToBeClickable(By.xpath("//*[contains(@href,'/?app=catalog&doc=catalog')]")));


    }

    @After
    public void doPostconditions() {

        if (driver != null) {
            System.out.println("Closing browser");
            driver.quit();
        }
    }

    @Test
    public void verifyThatlinksAreOpenedInNewWindow() {
        System.out.println("Checking if links opened in new window");

        driver.get("http://localhost:9000/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.xpath("//*[contains(@href,'/?app=countries&doc=edit_country')]")).click();
        String originalHandle = driver.getWindowHandle();
        Set<String> existWs = driver.getWindowHandles();
        System.out.println(originalHandle);

        List<WebElement>  linksList = driver.findElements(By.cssSelector(".fa.fa-external-link"));
        for(WebElement link: linksList){
            verifyThatWindowsOpened (link, existWs);
            driver.switchTo().window(originalHandle);
        }
    }

    public void verifyThatWindowsOpened (WebElement link, Set<String> existingWindows){
        link.click();
        String newW = wait.until(anyWindowOtherThan(existingWindows));
        driver.switchTo().window(newW);
        System.out.println(newW);
        assertFalse(existingWindows.contains(newW));
        driver.close();
    }

    public ExpectedCondition<String> anyWindowOtherThan(Set<String> windows) {

        return new ExpectedCondition<String>() {

            public String apply(WebDriver input) {

                Set<String> handles = driver.getWindowHandles();

                handles.removeAll(windows);

                return handles.size() > 0 ? handles.iterator().next() : null;

            }

        };


    }



}