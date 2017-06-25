import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;


public class Task4new {

    private final By USER_NAME_INPUT_FIELD = By.name("username");
    private final By PASSWORD_INPUT_FIELD = By.name("password");
    private final By LOGIN_BUTTON = By.xpath("//button[@name='login']");


    private String logIn = "admin";
    public WebDriver driver;
    public WebDriverWait wait;


    @Before
    public void doPreconditions() {

        driver = new FirefoxDriver();

        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Logging in to AC");
        driver.get("http://localhost:9000/litecart/admin/");
        driver.findElement(USER_NAME_INPUT_FIELD).sendKeys(logIn);
        driver.findElement(PASSWORD_INPUT_FIELD).sendKeys(logIn);
        driver.findElement(LOGIN_BUTTON).click();
        wait.until(elementToBeClickable(By.xpath("//*[@href='http://localhost:9000/litecart/admin/?app=catalog&doc=catalog']")));


    }

    @After
    public void doPostconditions() {

        if (driver != null) {
            System.out.println("Closing browser");
            driver.quit();
        }
    }

    @Test
    public void navigateBetweenSideMenuItems() {
        System.out.println("Checking tabs");
        List<WebElement> listOfTabs = driver.findElements(By.xpath("//*[@id='app-']"));
        for (int i = 1; i <= listOfTabs.size(); i++) {
            String tabLocator = String.format("//*[@id='app-'][%s]", i);
            driver.findElement(By.xpath(tabLocator)).click();
            List<WebElement> listOfSubTabs = driver.findElements(By.xpath(tabLocator + "//li"));
            for (int a = 1; a <= listOfSubTabs.size(); a++) {
                String subTabLocator = String.format(tabLocator + "//li[%s]", a);
                driver.findElement(By.xpath(subTabLocator)).click();
                assertTrue(driver.findElements(By.cssSelector("#main>h1")) != null);
            }

        }
    }



}
