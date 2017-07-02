package Task10.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Created by inna.pshenychna on 7/2/2017.
 */
public class ShoppingCartPage extends Page {
    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public int getNumberOfItemsToRemove(){
       return driver.findElements(By.xpath("//*[@title='Remove']")).size();
    }
    public ShoppingCartPage removeItem(){
        System.out.println("Removing Item");
        driver.findElement(By.xpath("html/body/div[2]/div/main/form/div/div[1]/div/div/table/tbody/tr[1]/td[6]/button")).click();
        return this;
    }
    public ShoppingCartPage waitForTabelToRefreshe(){
//        WebElement tabel =driver.findElement(By.cssSelector("#order_confirmation-wrapper"));
//        wait.until(ExpectedConditions.stalenessOf(tabel));
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.textToBePresentInElementLocated(By.xpath("html/body/div[2]/div/main/form/div/div[3]/div/div/table/tbody/tr[1]/td[2]"),"$")));
        return this;
    }

    public MainPage navigateToMainPage(){
        wait.until(visibilityOfElementLocated(By.xpath("html/body/div[2]/div/main/form/div/div[1]/p[2]/a")));
        driver.findElement(By.xpath("html/body/div[2]/div/main/form/div/div[1]/p[2]/a")).click();
        return new MainPage(driver);
    }
    public ShoppingCartPage verifyCartOpened(){
        wait.until(visibilityOfElementLocated(By.cssSelector("#box-checkout-customer")));
        return this;
    }

}
