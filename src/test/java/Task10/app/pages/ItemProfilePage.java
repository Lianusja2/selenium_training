package Task10.app.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.attributeToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Created by inna.pshenychna on 7/2/2017.
 */
public class ItemProfilePage extends Page {
    public ItemProfilePage(WebDriver driver) {
        super(driver);
    }

    public ItemProfilePage verifyProfileOpened(){
        wait.until(visibilityOfElementLocated(By.xpath("html/body/div[2]/div/div[2]/div[1]/div[3]/div/div[4]/form/div/div/div[2]/button")));
        return this;
    }

    public ItemProfilePage addToCart(){
        String quantiyBefore = driver.findElement(By.xpath("//*[@class='quantity']")).getAttribute("textContent");
        int qntBefore = Integer.parseInt(quantiyBefore);
        System.out.println("Number of items in cart before adding = " + quantiyBefore);
        int qntExpected = qntBefore + 1;
        //If Yellow Duck
        List<WebElement> selectsize =  driver.findElements(By.xpath("//*[@class='form-control' and @name='options[Size]']"));
        if (selectsize.size()>0){
            Select dropdown = new Select(driver.findElement(By.xpath("//*[@class='form-control' and @name='options[Size]']")));
            dropdown.selectByVisibleText("Small");
        }

        driver.findElement(By.xpath("html/body/div[2]/div/div[2]/div[1]/div[3]/div/div[4]/form/div/div/div[2]/button")).click();
        wait.until(attributeToBe(By.xpath("//*[@class='quantity']"), "textContent", "" + qntExpected));
        return this;
    }

    public void closeItemProfile(){
        driver.findElement(By.xpath("html/body/div[2]/div/button")).click();
    }


}
