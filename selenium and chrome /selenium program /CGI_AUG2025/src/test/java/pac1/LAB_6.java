package pac1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LAB_6 {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("https://tutorialsninja.com/demo/");

            // 1. Login
            driver.findElement(By.xpath("//a[@title='My Account']")).click();
            driver.findElement(By.linkText("Login")).click();
            driver.findElement(By.id("input-email")).sendKeys("srihitha@test.com");
            driver.findElement(By.id("input-password")).sendKeys("Srihitha@123");
            driver.findElement(By.xpath("//input[@value='Login']")).click();
            System.out.println("Login successful with: srihitha@test.com");

         // Select 25 from 'Show' dropdown
            WebElement showDropdown = driver.findElement(By.id("input-limit"));
            Select select = new Select(showDropdown);
            select.selectByVisibleText("25");

            // Wait for products to load
            Thread.sleep(2000);

            // Click on 'Add to Cart' for the first item
            WebElement addToCartBtn = driver.findElement(By.xpath("(//button[@title='Add to Cart'])[1]"));
            addToCartBtn.click();
            System.out.println("Clicked on Add to Cart for first monitor");

            // 5. Click on 'Specification' tab
            driver.findElement(By.xpath("//a[text()='Specification']")).click();
            System.out.println("Specification tab opened");

            // 6. Add to Wishlist
            driver.findElement(By.xpath("//button[@data-original-title='Add to Wish List']")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-success")));
            System.out.println("Apple Cinema added to wishlist");

            // 7. Search for 'Mobile'
            driver.findElement(By.name("search")).sendKeys("Mobile");
            driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();

            // 8. Click 'Search in product descriptions'
            driver.findElement(By.id("description")).click();
            driver.findElement(By.id("button-search")).click();

            // 9. Click HTC Touch HD
            WebElement htcLink = wait.until(ExpectedConditions
                    .visibilityOfElementLocated(By.xpath("//a[text()='HTC Touch HD']")));
            htcLink.click();

            // 10. Change Qty to 3
            WebElement qty = driver.findElement(By.id("input-quantity"));
            qty.clear();
            qty.sendKeys("3");

            // 11. Add to cart
            driver.findElement(By.id("button-cart")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-success")));
            System.out.println("Success: HTC Touch HD added to shopping cart!");

            // 12. View cart
            driver.findElement(By.id("cart-total")).click();
            driver.findElement(By.xpath("//strong[text()=' View Cart']")).click();

            // 13. Verify mobile name
            WebElement cartItem = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("HTC Touch HD")));
            if (cartItem.isDisplayed()) {
                System.out.println("Verified: HTC Touch HD is present in the cart");
            }

            // 14. Checkout
            driver.findElement(By.linkText("Checkout")).click();

            // 15. Logout
            driver.findElement(By.xpath("//a[@title='My Account']")).click();
            driver.findElement(By.linkText("Logout")).click();

            // 16. Verify logout heading
            WebElement logoutHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Account Logout']")));
            if (logoutHeading.isDisplayed()) {
                System.out.println("Verified: Account Logout heading is displayed");
            }

            // 17. Continue
            driver.findElement(By.linkText("Continue")).click();

            System.out.println("Lab 6 Test Completed!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
