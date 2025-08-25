package pac1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LAB_5_Part_2 {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://tutorialsninja.com/demo/index.php?route=account/register");

        // Step 1: First Name with 33 characters
        WebElement firstName = driver.findElement(By.id("input-firstname"));
        firstName.sendKeys("ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFG"); // 33 chars
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
        Thread.sleep(2000);
        WebElement firstNameError = driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div"));
        System.out.println("First Name Error: " + firstNameError.getText());

        // Step 2: Last Name with 33 characters
        driver.navigate().refresh();
        Thread.sleep(2000);
        driver.findElement(By.id("input-firstname")).sendKeys("Sri");
        driver.findElement(By.id("input-lastname")).sendKeys("ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFG"); // 33 chars
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
        Thread.sleep(2000);
        WebElement lastNameError = driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div"));
        System.out.println("Last Name Error: " + lastNameError.getText());

        // Step 3: Enter valid Email
        driver.navigate().refresh();
        Thread.sleep(2000);
        driver.findElement(By.id("input-firstname")).sendKeys("Sri");
        driver.findElement(By.id("input-lastname")).sendKeys("Pusuluru");
        driver.findElement(By.id("input-email")).sendKeys("test" + System.currentTimeMillis() + "@mail.com");
        System.out.println("Valid Email entered successfully.");

        // Step 4: Enter Telephone (between 3–32 chars)
        driver.findElement(By.id("input-telephone")).sendKeys("9876543210");
        System.out.println("Valid Telephone entered successfully.");

        driver.quit();
    }
}
