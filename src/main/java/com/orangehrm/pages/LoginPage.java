package com.orangehrm.pages;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LoginPage {

	
	WebDriver driver;
	By uname;
	By pword;
	By loginbutton;
	String projectpath=System.getProperty("user.dir");
	public LoginPage(WebDriver driver2) throws ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated constructor stub
			this.driver=driver2;
	

	
	File file = new File(projectpath+"\\data1.xml");

	 DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	 DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

	 Document d1= dBuilder.parse(file);
	// d1.getDocumentElement().normalize();
	 
	// System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

	 NodeList nList = d1.getElementsByTagName("user");

	 for (int i = 0; i < nList.getLength(); i++) {
		 
	     Node node = nList.item(i);

	     if (node.getNodeType() == Node.ELEMENT_NODE) {
	         Element element = (Element) node;

	     String    uname1  = element.getElementsByTagName("usertag").item(0).getTextContent();
	     String    pword1= element.getElementsByTagName("passtag").item(0).getTextContent();
	     String   loginbutton1= element.getElementsByTagName("continuetag").item(0).getTextContent();
	    
	 
	uname=By.name(uname1);
	 pword=By.name(pword1);
	loginbutton=By.xpath(loginbutton1);
	
	 }
	     }
	}
	 
	public void enterusername(String username)
	{
		driver.findElement(uname).sendKeys(username);
	}
	
	
	public void enterpassword(String password)
	{
		driver.findElement(pword).sendKeys(password);
	}
	
	
	public void clickonlogin()
	{
		driver.findElement(loginbutton).click();
}
}
	
