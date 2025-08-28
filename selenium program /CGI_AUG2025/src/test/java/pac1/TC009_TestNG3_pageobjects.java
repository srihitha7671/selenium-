package pac1;
 
import org.testng.annotations.Test;
 
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
 
import io.github.bonigarcia.wdm.WebDriverManager;
 
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.BeforeClass;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
 
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
 
public class TC009_TestNG3_pageobjects {
	WebDriver driver;
	String projectpath=System.getProperty("user.dir");
  @Test(dataProvider="logindata")
  public void f(String username, String password) {
	 System.out.println("This is the test");
	  ExtentReports extent=new ExtentReports();
	  ExtentSparkReporter spark=new ExtentSparkReporter(projectpath+"\\Aug28th.html");
	  extent.attachReporter(spark);
	  ExtentTest test=extent.createTest("Verify the login");
	 driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		Loginpage objlogin=new Loginpage(driver);
			if(objlogin.usernameisdisplayed())
		{
			objlogin.enterusername(username);
		System.out.println("Get placeholder:"+objlogin.unamegetattributevalue());
		test.pass("username is displayed");
		}
		else
		{
			System.out.println("username is not displayed");
			test.fail("username is not displayed");
		}
		objlogin.enterpassword(password);
		objlogin.clickonbutton();
		if(objlogin.dashisdisplayed())
		{
//	Assert.assertTrue(true);
	test.pass("login success");
		}
		else
		{
		//	Assert.assertTrue(false);
			test.fail("login failed");
		}
	 extent.flush(); 
  }

  @BeforeMethod
  public void beforeMethod() {
	 WebDriverManager.chromedriver().setup();
		 driver=new ChromeDriver();
	  	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
  }
  @AfterMethod
  public void afterMethod() {
	  System.out.println("This is After Method");
	  driver.quit();
  }
 
 
  @DataProvider
  public String[][] logindata() throws IOException {
	String[][] data=new String[3][2];
	File file1=new File("/Users/srihithapusuluru/Documents/Firefox Selenium /selenium program /CGI_AUG2025/data.xlsx");
	System.out.println("project path:"+projectpath);
	FileInputStream fs=new FileInputStream(file1);
	XSSFWorkbook workbook=new XSSFWorkbook(fs);
	XSSFSheet worksheet=workbook.getSheetAt(0);
	int rowcount=worksheet.getPhysicalNumberOfRows();
	System.out.println("rows:"+rowcount);
	for(int i=0;i<rowcount;i++)
	{
		data[i][0]=worksheet.getRow(i).getCell(0).getStringCellValue();
		data[i][1]=worksheet.getRow(i).getCell(1).getStringCellValue();
	}
    return data;

    }
  @BeforeClass
  public void beforeClass() {
	  System.out.println("This is Before Class");
  }
 
  @AfterClass
  public void afterClass() {
	  System.out.println("This is After Class");
  }
 
  @BeforeTest
  public void beforeTest() {
	  System.out.println("This is Before Test");
  }
 
  @AfterTest
  public void afterTest() {
	  System.out.println("This is After Test");
  }
 
  @BeforeSuite
  public void beforeSuite() {
	  System.out.println("This is Before Suite");
  }
 
  @AfterSuite
  public void afterSuite() {
	  System.out.println("This is After Suite");
  }
 
}