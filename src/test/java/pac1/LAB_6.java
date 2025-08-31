package pac1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LAB_6 {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://tutorialsninja.com/demo/index.php?");
        driver.findElement(By.linkText("My Account")).click();
        driver.findElement(By.linkText("Login")).click();    
        driver.findElement(By.xpath("//*[@id=\"input-email\"]")).sendKeys("srihitha@test.com");
        driver.findElement(By.xpath("//*[@id=\"input-password\"]")).sendKeys("Test1234");       
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/form/input")).click();
        
        driver.findElement(By.linkText("Components")).click();
        driver.findElement(By.linkText("Monitors (2)")).click();
        
        driver.findElement(By.xpath("//*[@id=\"input-limit\"]/option[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[2]/div/div[1]/a/img")).click();
        
        driver.findElement(By.xpath("//*[@id=\"button-cart\"]")).click();
        
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div[1]/button[1]")).click();
        
        driver.findElement(By.xpath("//*[@id=\"search\"]/input")).sendKeys("Mobile");
        driver.findElement(By.xpath("//*[@id=\"search\"]/span/button")).click();
        
        driver.findElement(By.xpath("//*[@id=\"description\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"button-search\"]")).click();
        
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[1]/div/div[1]/a/img")).click();
        
        WebElement quantityInput = driver.findElement(By.xpath("//*[@id='input-quantity']"));
        quantityInput.clear();
        quantityInput.sendKeys("3");
        
        driver.findElement(By.xpath("//*[@id=\"button-cart\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"cart\"]/button")).click();
        driver.findElement(By.xpath("//*[@id=\"cart\"]/ul/li[2]/div/p/a[2]/strong")).click();
        
        driver.findElement(By.linkText("My Account")).click();
        driver.findElement(By.linkText("Logout")).click(); 
        
        WebElement logoutHeading = driver.findElement(By.xpath("//*[@id='content']/h1"));
        String actualHeadingText = logoutHeading.getText();
        String expectedHeadingText = "Account Logout";
        if (actualHeadingText.equals(expectedHeadingText)) {
            System.out.println("Verification Successful: The page heading (Account Logout) is correct.");
        } else {
            System.out.println("Verification Failed: The page heading is not correct.");
        }
        
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/a")).click();
        driver.quit();
    }
}