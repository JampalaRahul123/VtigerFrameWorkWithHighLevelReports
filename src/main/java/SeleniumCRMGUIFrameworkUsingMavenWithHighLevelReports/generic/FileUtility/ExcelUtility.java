package SeleniumCRMGUIFrameworkUsingMavenWithHighLevelReports.generic.FileUtility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {

	public String getDataFromExcel(String sheetName, int rowNum, int cellNum) throws Throwable {
		// Read Data From Excel File
		// Step 1 : Convert physical file into java readable object
		FileInputStream fis1 = new FileInputStream("./src/test/resources/testScriptdata.xlsx");
		// Step 2 : Open Workbook in read mode
		Workbook wb = WorkbookFactory.create(fis1);
		// Step 3 : Go to Specific Sheet,cell,row and fetch the data using method chaining concept in which we call
		// different methods in a single line using same object reference
		String data = wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).getStringCellValue();
		// Step 4: Close WorkBook
		wb.close();
		return data;
	}
	public String getDataFromExcel1(String sheetName, int rowNum, int cellNum) throws Throwable {
		// Read Data From Excel File
		// Step 1 : Convert physical file into java readable object
		FileInputStream fis1 = new FileInputStream("./src/test/resources/testScriptdata.xlsx");
		// Step 2 : Open Workbook in read mode
		Workbook wb = WorkbookFactory.create(fis1);
		//Step 3 : Go to Specific Sheet
		Sheet sh = wb.getSheet("sheetName");
		//Step 4 : Go to Specific Row
		Row r = sh.getRow(rowNum);
		//Step 5 : Go to Specific Cell
		Cell c = r.getCell(cellNum);
		//Step 6 : Fetch Data
		String data=c.getStringCellValue();
		return data;
	}
	public int getRowCount(String sheetName) throws Throwable {
		// Step 1 : Convert physical file into java readable object
		FileInputStream fis1 = new FileInputStream("./src/test/resources/testScriptdata.xlsx");
		// Step 2 : Open Workbook in read mode
		Workbook wb = WorkbookFactory.create(fis1);
		// Step 3 : Fetch The Data
		int rowCount = wb.getSheet(sheetName).getLastRowNum();
		// Step 4: Close WorkBook
		wb.close();
		return rowCount;
	}
	public void setDataInToExcel(String sheetName, int rowNum, int cellNum, String data) throws Throwable {
		// Step 1 : Convert physical file into java readable object
		FileInputStream fis1 = new FileInputStream("./src/test/resources/testScriptdata.xlsx");
		// Step 2 : Open Workbook in read mode
		Workbook wb = WorkbookFactory.create(fis1);
		wb.getSheet(sheetName).getRow(rowNum).createCell(cellNum);
		// Step 3 : Open Excel Sheet in write mode, write the data and save it.
		FileOutputStream fos = new FileOutputStream("./src/test/resources/testScriptdata.xlsx");
		// Get the writemode object of physical file
		wb.write(fos); // write the data in object
		// Step 4: Close WorkBook
		wb.close();
	}
}
