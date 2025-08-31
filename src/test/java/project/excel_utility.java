package project;



import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;

public class excel_utility {

    @DataProvider(name = "excelData")
    public Object[][] getData() throws IOException {
        String path = "/Users/srihithapusuluru/Documents/Firefox Selenium /selenium program /CGI_AUG2025/project_data.xlsx";  // path to your Excel
        FileInputStream fis = new FileInputStream(path);
        Workbook wb = new XSSFWorkbook(fis);
        Sheet sheet = wb.getSheet("Sheet1");   // your login test cases are in Sheet1

        int rows = sheet.getPhysicalNumberOfRows();
        int cols = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rows - 1][cols];

        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Cell cell = sheet.getRow(i).getCell(j);
                data[i - 1][j] = (cell == null) ? "" : cell.toString();
            }
        }

        wb.close();
        return data;
    }
}
