package New;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class verifyVideoTutorial {
    public static void main(String[] args) {
        // Set path to ChromeDriver
        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");

        WebDriver driver = new ChromeDriver();

        try {
            // Open Contact Us page
            driver.get("https://automationexercise.com/contact_us");
            driver.manage().window().maximize();

            // Wait until "Video Tutorials" link is clickable
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement videoTutorials = wait.until(
                    ExpectedConditions.elementToBeClickable(By.linkText("Video Tutorials"))
            );

            // Store parent window
            String parentWindow = driver.getWindowHandle();

            // Click on Video Tutorials link
            videoTutorials.click();
            System.out.println("Clicked on Video Tutorials link");

            // Wait until a new window opens
            wait.until(driver1 -> driver1.getWindowHandles().size() > 1);

            // Switch to the child window
            Set<String> allWindows = driver.getWindowHandles();
            for (String window : allWindows) {
                if (!window.equals(parentWindow)) {
                    driver.switchTo().window(window);
                    break;
                }
            }

            // Verify the URL
            String currentUrl = driver.getCurrentUrl();
            System.out.println("Current URL: " + currentUrl);
            if (currentUrl.contains("youtube")) {
                System.out.println("✅ Successfully navigated to YouTube Video Tutorials page.");
            } else {
                System.out.println("❌ Navigation to Video Tutorials failed.");
            }

            // Close the child window
            driver.close();

            // Switch back to parent window
            driver.switchTo().window(parentWindow);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Quit driver safely
            driver.quit();
        }
    }
}