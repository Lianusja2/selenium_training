import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
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
    public String  itemName = "IronMan";
    public String baseURL="http://localhost:9000/litecart/admin/"; //litecart version 2.0.1


    @Before
    public void doPreconditions() {

        driver = new FirefoxDriver();

        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Logging in to AC");
        driver.get(baseURL);
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

        wait.until(elementToBeClickable(By.xpath("//*[contains(@href,'/?app=catalog&doc=catalog')]")));
        driver.findElement(By.xpath("//*[contains(@href,'/?app=catalog&doc=catalog')]")).click();
        driver.findElement(By.xpath("//*[contains(@href,'/?category_id=0&app=catalog&doc=edit_product')]")).click();

        wait.until(visibilityOfElementLocated(By.cssSelector("#main>h1")));
        assertTrue(isElementPresent(By.name("name[en]")));
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("ironman.jpg").getFile());
        driver.findElement(By.name("new_images[]")).sendKeys(file.getAbsolutePath());
        System.out.println("Fill in general");
        driver.findElement(By.xpath("//*[@class='tab-pane active']//*[@class='btn btn-default']")).click();
        driver.findElement(By.name("code")).sendKeys("0000011");
        driver.findElement(By.name("name[en]")).sendKeys(itemName);
        driver.findElement(By.name("quantity")).clear();
        driver.findElement(By.name("quantity")).sendKeys("1");
        driver.findElement(By.name("weight")).clear();
        driver.findElement(By.name("weight")).sendKeys("1");
        driver.findElement(By.name("dim_x")).sendKeys("10");
        driver.findElement(By.name("dim_y")).sendKeys("20");
        driver.findElement(By.name("dim_z")).sendKeys("30");
        driver.findElement(By.xpath("//*[contains(.,'Gender')]//*[@class='checkbox'][2]")).click();
        driver.findElement(By.name("date_valid_from")).sendKeys("2017-06-06");
        driver.findElement(By.name("date_valid_to")).sendKeys("2017-11-08");
        Select dropdown = new Select(driver.findElement(By.name("sold_out_status_id")));
        dropdown.selectByVisibleText("Sold out");

        System.out.println("Fill in price and save");
        driver.findElement(By.xpath("//*[@href='#tab-prices']")).click();
        driver.findElement(By.name("purchase_price")).sendKeys("10");
        driver.findElement(By.name("prices[USD]")).sendKeys("20");
        driver.findElement(By.name("save")).click();

        System.out.println("Verify if item is present in list on AC");
        wait.until(visibilityOfElementLocated(By.cssSelector(".table.table-striped")));
        String createdItemLocator = String.format("//a[contains(.,'%s')]", itemName);
        assertTrue(isElementPresent(By.xpath(createdItemLocator)));

    }


    private boolean isElementPresent(By locator) {

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
