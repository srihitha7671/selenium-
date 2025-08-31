package pac1;
 
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
 
public class Loginpage {

WebDriver driver;

	public Loginpage(WebDriver driver) {

		this.driver=driver;

		// TODO Auto-generated constructor stub

	}

	By uname=By.name("username");

	By pword=By.name("password");

	By submit=By.xpath("//button[@type='submit']");

	By Dashbord=By.xpath("//h6[text()='Dashboard']");

	public void enterusername(String username)

	{

		driver.findElement(uname).sendKeys(username);

	}

	public boolean usernameisdisplayed()

	{

		return driver.findElement(uname).isDisplayed();

	}

	public String unamegetattributevalue()

	{

		return driver.findElement(uname).getAttribute("placeholder");

	}

	public boolean dashisdisplayed()

	{

		return driver.findElement(Dashbord).isDisplayed();

	}

	public void enterpassword(String password)

	{

		driver.findElement(pword).sendKeys(password);

	}

	public void clickonbutton()

	{

		driver.findElement(submit).click();

	}

	public boolean usernameisdisplayed1() {
		// TODO Auto-generated method stub
		return false;
	}

}

 