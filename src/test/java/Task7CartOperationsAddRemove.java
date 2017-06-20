import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

/**
 * Created by inna.pshenychna on 6/18/2017.
 */
public class Task7CartOperationsAddRemove {


    public WebDriver driver;
    public WebDriverWait wait;



    @Before
    public void doPreconditions() {

        driver = new FirefoxDriver();

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
    public void addRemovetoCart() {
        System.out.println("Add To Cart");

        for(int i=1; i<=3; i++){
            System.out.println("Add Item : "+ i);
            driver.findElement(By.xpath("html/body/div[1]/div[1]/div[2]/main/ul/li[2]/a")).click();
            driver.findElement(By.xpath("html/body/div[1]/div[1]/div[2]/main/div[3]/div[2]/div/div/div[1]/div/a")).click();
            wait.until(visibilityOfElementLocated(By.xpath("html/body/div[2]/div/div[2]/div[1]/div[3]/div/div[4]/form/div/div/div[2]/button")));
            String quantiyBefore = driver.findElement(By.xpath("//*[@class='quantity']")).getAttribute("textContent");
            int qntBefore = Integer.parseInt(quantiyBefore);
            System.out.println("Number of items in cart before adding = " + quantiyBefore);
            int qntExpected = qntBefore + 1;

            driver.findElement(By.xpath("html/body/div[2]/div/div[2]/div[1]/div[3]/div/div[4]/form/div/div/div[2]/button")).click();


            wait.until(attributeToBe(By.xpath("//*[@class='quantity']"), "textContent", "" + qntExpected));

            String quantiyAdded = driver.findElement(By.xpath("//*[@class='quantity']")).getAttribute("textContent");
            System.out.println("QNT after adding = " + quantiyAdded);
            driver.findElement(By.xpath("html/body/div[2]/div/button")).click();
            driver.navigate().refresh();
        }

        String quantiyFinal = driver.findElement(By.xpath("//*[@class='quantity']")).getAttribute("textContent");
        System.out.println("Final quantity = " + quantiyFinal);

        System.out.println("Openning shopping Cart");
        driver.findElement(By.xpath("html/body/div[1]/header/div[2]/a/div")).click();

        int numberOfItems = driver.findElements(By.xpath("//*[@title='Remove']")).size();
        for (int i=1; i<=(numberOfItems-1); i++) {

            System.out.println("Removing Item: " +i);
            driver.findElement(By.xpath("html/body/div[2]/div/main/form/div/div[1]/div/div/table/tbody/tr[1]/td[6]/button")).click();
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.textToBePresentInElementLocated(By.xpath("html/body/div[2]/div/main/form/div/div[3]/div/div/table/tbody/tr[1]/td[2]"),"$")));

        }
        System.out.println("Removing Item: " +numberOfItems);
        driver.findElement(By.xpath("html/body/div[2]/div/main/form/div/div[1]/div/div/table/tbody/tr[1]/td[6]/button")).click();

        driver.findElement(By.xpath("html/body/div[2]/div/main/form/div/div[1]/p[2]/a")).click();
        String quantiyRemoved = driver.findElement(By.xpath("//*[@class='quantity']")).getAttribute("textContent");
        System.out.println("QNT after removal = " + quantiyRemoved);
        int qntRemoved = Integer.parseInt(quantiyRemoved);
        assertEquals(0,qntRemoved);
    }
}
