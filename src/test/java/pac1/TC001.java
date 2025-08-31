package pac1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class TC001 {
    public static void main(String[] args) {
        System.out.println("Test Started...");

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("https://tutorialsninja.com/demo/index.php?");
            System.out.println("Title is: " + driver.getTitle());

            driver.findElement(By.xpath("//span[text()='My Account']")).click();
            driver.findElement(By.linkText("Register")).click();

            String heading = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
            if (heading.equals("Register Account")) {
                System.out.println("Heading matched: " + heading);
            } else {
                System.out.println("Heading not matched, got: " + heading);
            }

            driver.findElement(By.xpath("//input[@value='Continue']")).click();
            WebElement warn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-danger")));
            System.out.println("Warning shown: " + warn.getText());

            // first name error
            driver.findElement(By.id("input-firstname")).sendKeys("ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFG");
            driver.findElement(By.xpath("//input[@value='Continue']")).click();
            WebElement firstErr = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@id='input-firstname']/following-sibling::div")));
            System.out.println("First Name error msg: " + firstErr.getText());

            // last name error
            driver.navigate().refresh();
            driver.findElement(By.id("input-firstname")).sendKeys("Sri");
            driver.findElement(By.id("input-lastname")).sendKeys("ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFG");
            driver.findElement(By.xpath("//input[@value='Continue']")).click();
            WebElement lastErr = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@id='input-lastname']/following-sibling::div")));
            System.out.println("Last Name error msg: " + lastErr.getText());

            driver.get("https://tutorialsninja.com/demo/index.php?route=account/register");

            String email = "srihitha@test.com";
            String password = "Test1234";

            driver.findElement(By.id("input-firstname")).sendKeys("Srihitha");
            driver.findElement(By.id("input-lastname")).sendKeys("P");
            driver.findElement(By.id("input-email")).sendKeys(email);
            driver.findElement(By.id("input-telephone")).sendKeys("9876543210");
            driver.findElement(By.id("input-password")).sendKeys(password);
            driver.findElement(By.id("input-confirm")).sendKeys(password);

            driver.findElement(By.name("agree")).click();
            driver.findElement(By.xpath("//input[@name='newsletter' and @value='1']")).click();
            driver.findElement(By.xpath("//input[@value='Continue']")).click();

            try {
                WebElement success = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='content']/h1")));
                if (success.getText().equals("Your Account Has Been Created!")) {
                    System.out.println("Account created with: " + email);
                }
            } catch (Exception e) {
                WebElement error = driver.findElement(By.cssSelector(".alert-danger"));
                System.out.println("Account already exists. Try login with: " + email);
            }

            if (driver.findElements(By.linkText("View your order history")).size() > 0) {
                driver.findElement(By.linkText("View your order history")).click();
                System.out.println("Order history opened.");
            }

        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
        } finally {
            driver.quit();
            System.out.println("Test Finished.");
        }
    }
}
