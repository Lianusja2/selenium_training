import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;


public class Task4 {

    private final By USER_NAME_INPUT_FIELD = By.name("username");
    private final By PASSWORD_INPUT_FIELD = By.name("password");
    private final By LOGIN_BUTTON = By.xpath("//button[@name='login']");
    private String LINK_LOCATOR = "//*[@href='http://localhost:9000/litecart/admin/%s']";
    private String HEADER_LOCATOR = "//h1[contains(.,'%s')]";

    private String logIn = "admin";
    public WebDriver driver;
    public WebDriverWait wait;


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
    public void navigateBetweenSideMenuItems() {
        System.out.println("Checking tabs");

        for (TabNamesAC name: TabNamesAC.values()) {
            driver.findElement(By.xpath(String.format(LINK_LOCATOR,name.getLinkPart()))).click();
            driver.findElement(By.xpath(String.format(HEADER_LOCATOR,name.getHeaderText())));
            if(driver.findElement(By.xpath(String.format(HEADER_LOCATOR,name.getHeaderText()))) !=null){
                System.out.println("Menu item "+ name.getDisplayName() + " is opened and " +name.getHeaderText()+" header is present");
            }else{
                System.out.println("Menu item "+name.getDisplayName() + " is NOT opened and " +name.getHeaderText()+" header is absent");            }
        }

    }



}