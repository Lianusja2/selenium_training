import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class Task1 {


    public WebDriver driver;
    public WebDriverWait wait;


    @Before
    public void doPreconditions() {
        //FF
        //driver = new FirefoxDriver();

        //GC
        //System.setProperty("webdriver.chrome.driver", "C:/drivers/chromedriver/chromedriver.exe");
        //driver = new ChromeDriver();

        //IE
        System.setProperty("webdriver.ie.driver", "C:\\drivers\\IEDriverServer\\IEDriverServer.exe");
        driver = new InternetExplorerDriver();

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
        System.out.println("Opening Google");
        driver.get("https://www.google.com.ua");



    }



}