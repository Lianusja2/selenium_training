import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.*;
import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Created by inna.pshenychna on 6/18/2017.
 */
public class Task6AddNewItem {

    private final By USER_NAME_INPUT_FIELD = By.name("username");
    private final By PASSWORD_INPUT_FIELD = By.name("password");
    private final By LOGIN_BUTTON = By.xpath("//button[@name='login']");


    private String logIn = "admin";
    public WebDriver driver;
    public WebDriverWait wait;
    public String  itemName = "Test Item 2";


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


    }

    @After
    public void doPostconditions() {

        if (driver != null) {
            System.out.println("Closing browser");
            driver.quit();
        }
    }

    @Test
    public void addNewItemAC() {
        System.out.println("Adding new Item in AC");
        wait.until(elementToBeClickable(By.xpath("//*[@href='http://localhost:9000/litecart/admin/?app=catalog&doc=catalog']")));
        driver.findElement(By.xpath("//*[@href='http://localhost:9000/litecart/admin/?app=catalog&doc=catalog']")).click();
        driver.findElement(By.xpath("//*[@href='http://localhost:9000/litecart/admin/?category_id=0&app=catalog&doc=edit_product']")).click();

        wait.until(visibilityOfElementLocated(By.cssSelector("#main>h1")));

         ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("ironman.jpg").getFile());
        driver.findElement(By.name("new_images[]")).sendKeys(file.getAbsolutePath());
        System.out.println("Fill in general");
        driver.findElement(By.xpath("html/body/div[2]/main/form/div/div/div[1]/div/div[1]/div[1]/div/div/label[1]")).click();
        driver.findElement(By.xpath("html/body/div[2]/main/form/div/div/div[1]/div/div[2]/div[1]/input")).sendKeys("0000011");
        driver.findElement(By.xpath("html/body/div[2]/main/form/div/div/div[1]/div/div[2]/div[2]/div/input")).sendKeys(itemName);
        driver.findElement(By.xpath("html/body/div[2]/main/form/div/div/div[1]/div/div[2]/div[4]/div/div[1]/input")).clear();
        driver.findElement(By.xpath("html/body/div[2]/main/form/div/div/div[1]/div/div[2]/div[4]/div/div[1]/input")).sendKeys("1");
        driver.findElement(By.xpath("html/body/div[2]/main/form/div/div/div[1]/div/div[2]/div[5]/div/input")).clear();
        driver.findElement(By.xpath("html/body/div[2]/main/form/div/div/div[1]/div/div[2]/div[5]/div/input")).sendKeys("1");
        driver.findElement(By.xpath("html/body/div[2]/main/form/div/div/div[1]/div/div[2]/div[6]/div/input[1]")).sendKeys("10");
        driver.findElement(By.xpath("html/body/div[2]/main/form/div/div/div[1]/div/div[2]/div[6]/div/input[2]")).sendKeys("20");
        driver.findElement(By.xpath("html/body/div[2]/main/form/div/div/div[1]/div/div[2]/div[6]/div/input[3]")).sendKeys("30");
        driver.findElement(By.xpath("html/body/div[2]/main/form/div/div/div[1]/div/div[1]/div[4]/div/div/div[3]")).click();
        driver.findElement(By.xpath("html/body/div[2]/main/form/div/div/div[1]/div/div[1]/div[5]/input")).sendKeys("2017-06-06");
       driver.findElement(By.xpath("html/body/div[2]/main/form/div/div/div[1]/div/div[1]/div[6]/input")).sendKeys("2017-11-08");

        System.out.println("Fill in price and save");
        driver.findElement(By.xpath("html/body/div[2]/main/form/div/ul/li[3]/a")).click();
        driver.findElement(By.xpath("html/body/div[2]/main/form/div/div/div[3]/div[1]/div/div[1]/div/input")).sendKeys("10");
        driver.findElement(By.xpath("html/body/div[2]/main/form/div/div/div[3]/div[1]/table/tbody/tr[1]/td[1]/div/input")).sendKeys("20");

        System.out.println("Verify if item is present in list on AC");
        driver.findElement(By.xpath("html/body/div[2]/main/form/p/button[1]")).click();
        wait.until(visibilityOfElementLocated(By.cssSelector(".table.table-striped")));
        String createdItemLocator = String.format("//a[contains(.,'%s')]", itemName);
        assertTrue(isElementPresent(By.xpath(createdItemLocator)));

    }


    public boolean isElementPresent(By locator) {

        return driver.findElements(locator).size() > 0;

    }

    public boolean isElementNotPresent(By locator) {

        try {

            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);

            return driver.findElements(locator).size() == 0;

        }

        finally {

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        }

    }


}
