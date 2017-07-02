package Task10.app.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by inna.pshenychna on 7/2/2017.
 */
public class Page {
        protected WebDriver driver;
        protected WebDriverWait wait;

        public Page(WebDriver driver) {
            this.driver = driver;
            wait = new WebDriverWait(driver, 10);
        }
}
