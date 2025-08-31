package project;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class Testcases {

    WebDriver driver;
    String email;
    String password;

    ExtentReports extent;
    ExtentTest test;

    // ✅ Utility method to read Excel data
    public String[] readExcelData(String filePath, String sheetName, int rowNum, int colNum) {
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            Row row = sheet.getRow(rowNum - 1);  // 0-based index
            Cell cell = row.getCell(colNum - 1);

            String cellValue = cell.getStringCellValue().trim();

            if (!cellValue.contains(",")) {
                throw new RuntimeException("Cell must contain 'email,password'. Found: " + cellValue);
            }

            String[] creds = cellValue.split(",");
            creds[0] = creds[0].trim();
            creds[1] = creds[1].trim();
            return creds;

        } catch (IOException e) {
            throw new RuntimeException("Error reading Excel file: " + e.getMessage(), e);
        }
    }

    @BeforeClass
    public void setUp() {
        // ✅ Setup Extent Report
        String projectPath = System.getProperty("user.dir");
        String date = new SimpleDateFormat("MMM_dd_yyyy_HH_mm_ss").format(new Date());
        ExtentSparkReporter spark = new ExtentSparkReporter(projectPath + "\\Reports\\TestReport_" + date + ".html");

        extent = new ExtentReports();
        extent.attachReporter(spark);

        // ✅ Read email & password from Excel
        String[] creds = readExcelData("/Users/srihithapusuluru/Documents/Firefox Selenium /selenium program /CGI_AUG2025/project_data  .xlsx",
                                       "sheet1", 7, 6);
        email = creds[0];
        password = creds[1];

        // ✅ Setup WebDriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://automationexercise.com/");
    }

    @Test(priority = 1)
    public void loginTest() {
        test = extent.createTest("Login Test");

        try {
            driver.findElement(By.xpath("//a[@href='/login']")).click();
            driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys(email);
            driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys(password);
            driver.findElement(By.xpath("//button[@data-qa='login-button']")).click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement loggedInText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//a[contains(text(),'Logged in as')]")
            ));

            Assert.assertTrue(loggedInText.isDisplayed(), "Login failed - 'Logged in as' not visible");
            test.pass("✅ Login successful with email: " + email);

        } catch (Exception e) {
            takeScreenshot("LoginFail.png");
            test.fail("❌ Login test failed: " + e.getMessage());
            throw e;
        }
    }

    @Test(priority = 2, dependsOnMethods = {"loginTest"})
    public void logoutTest() {
        test = extent.createTest("Logout Test");

        try {
            driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement loginPageText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h2[normalize-space()='Login to your account']")
            ));

            Assert.assertTrue(loginPageText.isDisplayed(), "Logout failed - Login page not visible");
            test.pass("✅ Logout successful");

        } catch (Exception e) {
            takeScreenshot("LogoutFail.png");
            test.fail("❌ Logout test failed: " + e.getMessage());
            throw e;
        }
    }

    // ✅ Utility method to take screenshots
    private void takeScreenshot(String fileName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);
            String path = System.getProperty("user.dir") + "\\Reports\\" + fileName;
            File dest = new File(path);
            FileUtils.copyFile(src, dest);
            test.addScreenCaptureFromPath(path);
        } catch (Exception ex) {
            test.warning("Screenshot capture failed: " + ex.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
        if (extent != null) extent.flush();
    }
}
