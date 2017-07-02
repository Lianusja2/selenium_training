package Task10.app.app;

//import io.github.bonigarcia.wdm.ChromeDriverManager;
import Task10.app.pages.ItemProfilePage;
import Task10.app.pages.MainPage;
import Task10.app.pages.ShoppingCartPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;


/**
 * Created by inna.pshenychna on 7/2/2017.
 */
public class Application {
    private String baseUrl = "http://localhost:9000/litecart/";
    public WebDriver driver;
    public WebDriverWait wait;
    public ItemProfilePage itemProfilePage;
    public MainPage mainPage;
    public ShoppingCartPage shoppingCartPage;

    public Application() {
        driver = new FirefoxDriver();
        itemProfilePage = new ItemProfilePage(driver);
        mainPage = new MainPage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);

    }

    public void quit() {
        driver.quit();
    }
     public Integer numberOfItemsInShoppingCart(){
        mainPage.verifyPageOpened();
        int  qntRemoved = mainPage.numberOfItemsInCart();
        return qntRemoved;
     }

     public void add3itemsToCartRemoveAll(){
         mainPage.open(baseUrl);
         for(int i=1; i<=3; i++) {
             mainPage.navigateToPopuLarTab();
             itemProfilePage = mainPage.openFirstItemProfile();
             itemProfilePage.verifyProfileOpened();
             itemProfilePage.addToCart();
             itemProfilePage.closeItemProfile();
             mainPage.verifyPageOpened();
             mainPage.refreshPage();
         }
         mainPage.verifyPageOpened()
                 .navigateToShoppingCart().verifyCartOpened();
         int numberOfItems = shoppingCartPage.getNumberOfItemsToRemove();
         for (int i=1; i<=(numberOfItems-1); i++) {
             shoppingCartPage.removeItem()
                     .waitForTabelToRefreshe();
         }
         shoppingCartPage.removeItem();
         shoppingCartPage.navigateToMainPage();
         mainPage.verifyPageOpened();
         }

    public Application navigateToCatalog(){
        mainPage.open(baseUrl);
        return this;
    }
    public Application addItemToCart(){
        mainPage.navigateToPopuLarTab();
        itemProfilePage = mainPage.openFirstItemProfile();
        itemProfilePage.verifyProfileOpened();
        itemProfilePage.addToCart();
        itemProfilePage.closeItemProfile();
        mainPage.verifyPageOpened();
        mainPage.refreshPage();
        return this;
    }

    public Application removeAllItemsFromCart(){
        mainPage.verifyPageOpened()
                .navigateToShoppingCart().verifyCartOpened();
        int numberOfItems = shoppingCartPage.getNumberOfItemsToRemove();
        for (int i=1; i<=(numberOfItems-1); i++) {
            shoppingCartPage.removeItem()
                    .waitForTabelToRefreshe();
        }
        shoppingCartPage.removeItem();
        shoppingCartPage.navigateToMainPage();
        mainPage.verifyPageOpened();
        return this;
    }
}




