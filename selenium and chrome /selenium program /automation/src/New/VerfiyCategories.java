package New;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class VerfiyCategories {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://automationexercise.com/products");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // ✅ Women category
        WebElement womenCat = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[@class='panel-title']/a[contains(text(),'Women')]")));
        System.out.println("✅ Women category is present.");
        womenCat.click();
        System.out.println("👉 Clicked on Women category.");

        // ✅ Men category
        WebElement menCat = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[@class='panel-title']/a[contains(text(),'Men')]")));
        System.out.println("✅ Men category is present.");
        menCat.click();
        System.out.println("👉 Clicked on Men category.");

        // ✅ Kids category
        WebElement kidsCat = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[@class='panel-title']/a[contains(text(),'Kids')]")));
        System.out.println("✅ Kids category is present.");
        kidsCat.click();
        System.out.println("👉 Clicked on Kids category.");

        driver.quit();
    }
}
