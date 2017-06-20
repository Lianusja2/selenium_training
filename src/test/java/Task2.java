import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class Task2 {

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
    }

    @After
    public void doPostconditions() {

        if (driver != null) {
            System.out.println("Closing browser");
            driver.quit();
        }
    }

    @Test
    public void test() {
        System.out.println("Opening Page");
        driver.get("http://localhost:9000/litecart/admin/");
        WebElement userNameInput = driver.findElement(USER_NAME_INPUT_FIELD);
        userNameInput.sendKeys(logIn);
        WebElement userPasswordInput = driver.findElement(PASSWORD_INPUT_FIELD);
        userPasswordInput.sendKeys(logIn);
        WebElement buttonLogIn = driver.findElement(LOGIN_BUTTON);
        buttonLogIn.click();


    }



}