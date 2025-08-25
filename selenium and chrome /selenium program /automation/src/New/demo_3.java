package New;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class demo_3  {
    public static void main(String[] args) {
        // Optional: Set GeckoDriver path if Selenium Manager doesn't auto-resolve
        // System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");

        // Set Firefox binary path explicitly
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("/Applications/Firefox.app/Contents/MacOS/firefox");

        // Launch Firefox browser
        WebDriver driver = new FirefoxDriver(options);

        // Maximize window
        driver.manage().window().maximize();

        // Navigate to URL
        driver.get("https://automationexercise.com/");

        // Print page title
        String title = driver.getTitle();
        System.out.println("Page Title: " + title);

        // Verify title
        if (title.contains("Automation Exercise")) {
            System.out.println("✅ Homepage loaded successfully in Firefox.");
        } else {
            System.out.println("❌ Homepage not loaded as expected.");
        }

        // Close browser
        driver.quit();
    }
}