import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.*;
/**
 * Created by inna.pshenychna on 6/15/2017.
 */
public class Task5CorrectPageIsOpened {



    private final String ITEM_PRODUCT = "//*[@id='campaign-products']//*[contains(@href,'/rubber-ducks-c-1/subcategory-c-2/yellow-duck-p-1')]//div[@class='info']";




    public WebDriver driver;
    public WebDriverWait wait;



    @Before
    public void doPreconditions() {

        //FF
//        driver = new FirefoxDriver();

        //GC
//        System.setProperty("webdriver.chrome.driver", "C:/drivers/chromedriver/chromedriver.exe");
//        driver = new ChromeDriver();

        //IE
        System.setProperty("webdriver.ie.driver", "C:\\drivers\\IEDriverServer\\IEDriverServer.exe");
        driver = new InternetExplorerDriver();

        wait = new WebDriverWait(driver, 10);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Navigate to Litecart");
        driver.get("http://localhost:9000/litecart/");
            }

    @After
    public void doPostconditions() {

        if (driver != null) {
            System.out.println("Closing browser");
            driver.quit();
        }
    }


    @Test
    public void verifyProductPageOpened() {
        System.out.println("Check that correct page is opened for Product");
         //((JavascriptExecutor) driver).executeScript("arguments[0].selectedIndex = 1; arguments[0].dispatchEvent(new Event('change'))", driver.findElement(By.xpath(ITEM_PRODUCT)));
       // ((JavascriptExecutor) driver).executeScript("arguments[0].style.opacity=1", driver.findElement(By.xpath(ITEM_PRODUCT)));

        WebElement producatItem = driver.findElement(By.xpath(ITEM_PRODUCT));
        String productItemName = driver.findElement(By.xpath(ITEM_PRODUCT+"//*[@class='name']")).getAttribute("textContent");

        WebElement campainPriceItem = driver.findElement(By.xpath(ITEM_PRODUCT+"//*[@class='campaign-price']"));
        WebElement regularPriceItem = driver.findElement(By.xpath(ITEM_PRODUCT+"//*[@class='regular-price']"));
        String campainPrice = campainPriceItem.getAttribute("textContent").toString().substring(1);
        String campainPriceColor = campainPriceItem.getCssValue("color").toString();
        String campainPriceFont = campainPriceItem.getCssValue("font-weight").toString();
        String regularPrice = regularPriceItem.getAttribute("textContent").toString().substring(1);
        String regularColor= regularPriceItem.getCssValue("color").toString();
        String regularFont = regularPriceItem.getCssValue("text-decoration-line").toString();
        System.out.println(productItemName+"\n Regular price:  "+regularPrice+" "+regularColor+" "+regularFont +"\n Campain price:  "+ campainPrice+" "+ campainPriceColor+" "+campainPriceFont);

        producatItem.click();
        wait.until((WebDriver driver)->driver.findElements(By.cssSelector(".btn.btn-success")).size()>=1);



        String productItemName2 = driver.findElement(By.xpath("//*[@class='col-sm-halfs col-md-thirds'][1]/h1")).getAttribute("textContent");
        WebElement campainPriceItem2 = driver.findElement(By.xpath("//*[@class='col-sm-halfs col-md-thirds'][2]//*[@class='campaign-price']"));
        WebElement regularPriceItem2 = driver.findElement(By.xpath("//*[@class='col-sm-halfs col-md-thirds'][2]//*[@class='regular-price']"));
        String campainPrice2 = campainPriceItem2.getAttribute("textContent").toString().substring(1);
        String campainPriceColor2 = campainPriceItem2.getCssValue("color").toString();
        String campainPriceFont2 = campainPriceItem2.getCssValue("font-weight").toString();
        String regularPrice2 = regularPriceItem2.getAttribute("textContent").toString().substring(1);
        String regularColor2= regularPriceItem2.getCssValue("color").toString();
        String regularFont2 = regularPriceItem2.getCssValue("text-decoration-line").toString();
        System.out.println(productItemName2+"\n Regular price:  "+regularPrice2+" "+regularColor2+" "+regularFont2 +"\n Campain price:  "+ campainPrice2+" "+ campainPriceColor2+" "+campainPriceFont2);

        assertEquals(productItemName2, productItemName);
        assertEquals(campainPrice2, campainPrice);
        assertEquals(campainPriceColor2, campainPriceColor);
        assertEquals(campainPriceFont2, campainPriceFont);
        assertEquals(regularPrice2, regularPrice);
        assertEquals(regularColor2, regularColor);
        assertEquals(regularFont2, regularFont);

    }
}
