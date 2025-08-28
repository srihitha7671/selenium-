package project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage_POM {
    private WebDriver driver;

    private By signupLoginLink = By.linkText("Signup / Login");
    private By emailInput = By.xpath("//input[@data-qa='login-email']");
    private By passwordInput = By.xpath("//input[@data-qa='login-password']");
    private By loginBtn = By.xpath("//button[@data-qa='login-button']");
    private By logoutLink = By.linkText("Logout");
    private By loggedInUser = By.xpath("//a[contains(text(),'Logged in as')]");

    public LoginPage_POM(WebDriver driver) {
        this.driver = driver;
    }

    public void goToLoginPage() {
        driver.findElement(signupLoginLink).click();
    }

    public void enterEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    public void enterPassword(String pwd) {
        driver.findElement(passwordInput).sendKeys(pwd);
    }

    public void clickLogin() {
        driver.findElement(loginBtn).click();
    }

    public boolean isLoggedIn() {
        return driver.findElement(loggedInUser).isDisplayed();
    }

    public void clickLogout() {
        driver.findElement(logoutLink).click();
    }

    public boolean isLoginPageDisplayed() {
        return driver.findElement(loginBtn).isDisplayed();
    }
}
