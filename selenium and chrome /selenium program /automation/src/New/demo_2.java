package New;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class demo_2  {
    public static void main(String[] args) {
        // Launch Safari browser
        WebDriver driver = new SafariDriver();

        // Maximize the window
        driver.manage().window().maximize();

        // Navigate to a URL
        driver.get("https://automationexercise.com/products");

        // Get and print the page title
        String title = driver.getTitle();
        System.out.println("Page Title: " + title);

        // Verify if title contains "Products"
        if (title.contains("Products")) {
            System.out.println("✅ Product page is visible successfully.");
        } else {
            System.out.println("❌ Product page is NOT visible.");
        }

        // Close browser
        driver.quit();
    }
}