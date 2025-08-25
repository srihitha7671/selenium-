package New;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class verifylogin {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://automationexercise.com/");
        driver.findElement(By.xpath("//a[text()=' Signup / Login']")).click();

        if (driver.getTitle().length() > 0) {
            System.out.println("✅ Login navigation works.");
        } else {
            System.out.println("❌ Login navigation failed.");
        }

        driver.quit();
    }
}