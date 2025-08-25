package New;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class verfiyAutomationBanner {
    public static void main(String[] args) {
        // ✅ Set path for ChromeDriver
        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");

        // 1. Launch browser
        WebDriver driver = new ChromeDriver();

        // Maximize window
        driver.manage().window().maximize();

        // 2. Navigate to url
        driver.get("https://automationexercise.com");

        // 3. Locate the logo using xpath
        WebElement logo = driver.findElement(By.xpath("//img[@alt='Website for automation practice']"));

        // 4. Verify if logo is displayed
        if (logo.isDisplayed()) {
            System.out.println("✅ Logo is visible on the homepage.");
        } else {
            System.out.println("❌ Logo is NOT visible.");
        }

        // Close browser
        driver.quit();
    }
}