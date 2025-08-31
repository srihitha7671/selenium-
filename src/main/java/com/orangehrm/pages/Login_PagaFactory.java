package com.orangehrm.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login_PagaFactory {
	//By uname=By.name("username");
		@FindBy(name="username")
		WebElement uname;
		//By pword=By.name("password");
		@FindBy(name="password")
		WebElement pword;
		//By loginbutton=By.xpath("//button[@type='submit']");
		@FindBy(xpath="//button[@type='submit']")
		WebElement loginbutton;
		

		public void enterusername(String username)
		{
			uname.sendKeys(username);
		}
		
		
		public void enterpassword(String password)
		{
			pword.sendKeys(password);
		}
		
		
		public void clickonlogin()
		{
			loginbutton.click();
		}
		
	}