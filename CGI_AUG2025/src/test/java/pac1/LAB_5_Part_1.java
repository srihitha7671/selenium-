package pac1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LAB_5_Part_1 {

    public static void main(String[] args) {
        System.out.println("Test Start...");

        // Setup Chrome
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            // Step 1: Launch URL
            driver.get("https://tutorialsninja.com/demo/index.php?");
            driver.manage().window().maximize();

            // Step 2: Verify Title
            System.out.println("Page Title: " + driver.getTitle());

            // Step 3: Click My Account
            driver.findElement(By.xpath("//span[text()='My Account']")).click();

            // Step 4: Click Register
            driver.findElement(By.linkText("Register")).click();

            // Step 5: Verify Register Account heading
            WebElement heading = driver.findElement(By.xpath("//div[@id='content']/h1"));
            String headingText = heading.getText();
            if (headingText.equals("Register Account")) {
                System.out.println("Heading Verified: " + headingText);
            } else {
                System.out.println("Heading mismatch! Found: " + headingText);
            }

            // Step 6: Click Continue without filling form
            driver.findElement(By.xpath("//input[@value='Continue']")).click();

            // Step 7: Capture Warning message
            WebElement warning = driver.findElement(By.cssSelector(".alert-danger"));
            System.out.println("Warning Message: " + warning.getText());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close browser
            driver.quit();
            System.out.println("Test Completed!");
        }
    }
}
