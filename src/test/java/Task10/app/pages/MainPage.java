package Task10.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static junit.framework.TestCase.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Created by inna.pshenychna on 7/2/2017.
 */
public class MainPage extends Page {
    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage open(String baseUrl) {
        driver.get(baseUrl);
        return this;
    }

    public MainPage navigateToPopuLarTab() {
    driver.findElement(By.xpath("html/body/div[1]/div[1]/div[2]/main/ul/li[2]/a")).click();
    return this;
    }

    public ItemProfilePage openFirstItemProfile(){
        driver.findElement(By.xpath("html/body/div[1]/div[1]/div[2]/main/div[3]/div[2]/div/div/div[1]/div/a")).click();
        return new ItemProfilePage(driver);
    }

    public MainPage verifyPageOpened(){
        wait.until(visibilityOfElementLocated(By.xpath("html/body/div[1]/div[1]/div[2]/main/div[1]/div/img")));
        return this;
    }
    public MainPage refreshPage(){
        driver.navigate().refresh();
        this.verifyPageOpened();
        return this;
    }

    public int numberOfItemsInCart(){
        String quantiyRemoved = driver.findElement(By.xpath("//*[@class='quantity']")).getAttribute("textContent");
        System.out.println("QNT after removal = " + quantiyRemoved);
        int qntRemoved = Integer.parseInt(quantiyRemoved);
        return qntRemoved;
    }

    public ShoppingCartPage  navigateToShoppingCart(){
        System.out.println("Openning shopping Cart");
        driver.findElement(By.xpath("html/body/div[1]/header/div[2]/a/div")).click();
        return new ShoppingCartPage(driver);
    }
}
