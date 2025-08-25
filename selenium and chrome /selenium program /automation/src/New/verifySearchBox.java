package New;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class verifySearchBox {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://automationexercise.com/products");

        WebElement searchBox = driver.findElement(By.id("search_product"));
        WebElement searchBtn = driver.findElement(By.id("submit_search"));

        if (searchBox.isDisplayed()) {
            System.out.println("✅ Search box is displayed.");
            searchBox.sendKeys("Tshirt");
            searchBtn.click();
            System.out.println("✅ Search executed for 'Tshirt'.");
        }

        driver.quit();
    }
}

