package utils;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

public class Utilities {

    public static final int Implicit_Wait_time = 10;
    public static final int Page_Load_time = 10;


    public  static String  generateEmailTimeStamp()
    {
        Date date = new Date();
        String timestamp = date.toString().replace(" ","_").replace(":","_");
        return "aashma"+timestamp+"@yopmail.com";
    }


    public static Object[][] getTestDataFromExcelSheet(String sheetName) throws IOException {

        File excelFile = new File(System.getProperty("user.dir") + "\\src\\test\\java\\testdata\\ExcelTestData.xlsx");
        FileInputStream fis = new FileInputStream(excelFile);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet(sheetName);

        int rows = sheet.getLastRowNum();
        int columns = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rows][columns];

        for (int i = 0 ; i<rows;i++)
        {
            XSSFRow row = sheet.getRow(i+1);

            for (int j = 0 ; j<columns;j++)
            {
                XSSFCell cell = row.getCell(j);
                CellType cellType = cell.getCellType();
                switch (cellType)
                {
                    case STRING:
                        data[i][j] = cell.getStringCellValue();
                        break;
                    case NUMERIC:
                        data[i][j] = Integer.toString((int)cell.getNumericCellValue());
                        break;
                    case BOOLEAN:
                        data[i][j] = cell.getBooleanCellValue();
                        break;
                }
            }
        }
        return data;
    }

    public static String captureScreenshot(WebDriver driver, String testName) {

        File srcScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String destinationScreenshotPath = System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png";

        try {
            FileHandler.copy(srcScreenshot,new File(destinationScreenshotPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return destinationScreenshotPath;
    }


}

