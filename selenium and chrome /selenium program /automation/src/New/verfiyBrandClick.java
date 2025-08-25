package New;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

public class verfiyBrandClick {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://automationexercise.com/products");

        List<WebElement> brands = driver.findElements(By.xpath("//div[@class='brands-name']//li"));

        if (brands.size() > 0) {
            System.out.println("✅ Brands list is displayed with " + brands.size() + " items.");
            for (WebElement brand : brands) {
                System.out.println("👉 Brand: " + brand.getText());
                brand.findElement(By.tagName("a")).click();
                System.out.println("👉 Clicked on brand: " + brand.getText());
                driver.navigate().back();
            }
        }

        driver.quit();
    }
}
