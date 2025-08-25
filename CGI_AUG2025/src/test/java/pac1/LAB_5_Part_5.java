package pac1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LAB_5_Part_5 {
    public static void main(String[] args) throws Exception {
        System.out.println("Test Start...");

        // Setup ChromeDriver if not in PATH
        // System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Open registration page
        driver.get("https://tutorialsninja.com/demo/index.php?route=account/register");

        // Firstname
        driver.findElement(By.id("input-firstname")).sendKeys("Srihitha");
        // Lastname
        driver.findElement(By.id("input-lastname")).sendKeys("P");
        // Email (unique each time)
        driver.findElement(By.id("input-email"))
              .sendKeys("srihitha" + System.currentTimeMillis() + "@test.com");
        // Telephone
        driver.findElement(By.id("input-telephone")).sendKeys("9876543210");

        // Password + Confirm
        driver.findElement(By.id("input-password")).sendKeys("Test1234");
        driver.findElement(By.id("input-confirm")).sendKeys("Test1234");

        // Newsletter 'Yes'
        WebElement newsletterYes = driver.findElement(By.xpath("//input[@name='newsletter' and @value='1']"));
        if(!newsletterYes.isSelected()) {
            newsletterYes.click();
        }

        // Agree checkbox
        driver.findElement(By.name("agree")).click();

        // Click Continue button
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        // ✅ Wait for success page and get <h1>
        WebElement successHeader = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='content']/h1"))
        );
        String successMsg = successHeader.getText();

        if (successMsg.equals("Your Account Has Been Created!")) {
            System.out.println("Account creation successful!");
        } else {
            System.out.println("Message mismatch! Found: " + successMsg);
        }

        // ✅ Wait and click Continue link after success
        WebElement continueLink = wait.until(
            ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='content']//a[text()='Continue']"))
        );
        continueLink.click();

        // ✅ Navigate to order history
        WebElement orderHistoryLink = wait.until(
            ExpectedConditions.elementToBeClickable(By.linkText("View your order history"))
        );
        orderHistoryLink.click();

        System.out.println("Test Completed!");
        driver.quit();
    }
}
