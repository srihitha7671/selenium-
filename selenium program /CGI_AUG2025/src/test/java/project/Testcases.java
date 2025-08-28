package project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Testcases {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://automationexercise.com/");
    }

    @Test(priority = 1)
    public void loginTest() {
        // Click Signup/Login link
        driver.findElement(By.xpath("//a[@href='/login']")).click();

        // Enter valid credentials
        driver.findElement(By.xpath("//input[@data-qa='login-email']"))
                .sendKeys("srihitha@test.com");
        driver.findElement(By.xpath("//input[@data-qa='login-password']"))
                .sendKeys("123456");

        // Click login button
        driver.findElement(By.xpath("//button[@data-qa='login-button']")).click();

        // Verify "Logged in as username" is displayed
        WebElement loggedInText = driver.findElement(By.xpath("//a[contains(text(),'Logged in as')]"));
        Assert.assertTrue(loggedInText.isDisplayed(), "Login failed - 'Logged in as' not visible");
        System.out.println("✅ Login successful");
    }

    @Test(priority = 2, dependsOnMethods = {"loginTest"})
    public void logoutTest() {
        // Click Logout link
        driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();

        // Verify user is redirected back to login page
        WebElement loginPageText = driver.findElement(By.xpath("//h2[normalize-space()='Login to your account']"));
        Assert.assertTrue(loginPageText.isDisplayed(), "Logout failed - Login page not visible");
        System.out.println("✅ Logout successful");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
