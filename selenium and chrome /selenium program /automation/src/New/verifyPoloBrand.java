package New;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class verifyPoloBrand {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://automationexercise.com/products");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // ✅ Click POLO brand
        WebElement polo = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='brands_products']//a[contains(text(),'Polo')]")));
        polo.click();
        System.out.println("✅ Clicked on POLO brand");

        // ✅ Verify POLO brand page loaded
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[contains(text(),'Brand - Polo Products')]")));
        System.out.println("✅ Verified POLO brand page loaded");

        driver.quit();
    }
}
