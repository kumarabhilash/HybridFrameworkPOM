package DataProviders;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataProvider {

	public File file;
	public XSSFWorkbook wkb;
	public XSSFRow row;
	public XSSFCell cell;
	public XSSFSheet sheet;

	public ExcelDataProvider() {
		file = new File("./AppTestData/AppData.xlsx");
		try {
			FileInputStream fs = new FileInputStream(file);
			wkb = new XSSFWorkbook(fs);
		} catch (Exception e) {
		}
	}

	// @SuppressWarnings("deprecation")
	public String getData(int sheetIndex, int rowNum, int colNum) {
		try {
			sheet = wkb.getSheetAt(sheetIndex);
			row = sheet.getRow(rowNum);
			cell = row.getCell(colNum);
			if (cell.getCellType() == cell.CELL_TYPE_STRING) {
				return cell.getStringCellValue();
			} else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
				return String.valueOf(cell.getNumericCellValue());
			} else if (cell.getCellType() == cell.CELL_TYPE_BOOLEAN) {
				return String.valueOf(cell.getBooleanCellValue());
			} else if (cell.getCellType() == cell.CELL_TYPE_BLANK) {
				return "";
			}
		} catch (Exception e) {
			System.out.println("Exception is : " + e.getMessage());
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	public String getData(String sheetName, int rowNum, int colNum) {
		try {
			sheet = wkb.getSheet(sheetName);
			row = sheet.getRow(rowNum);
			cell = row.getCell(colNum);
			if (cell.getCellType() == cell.CELL_TYPE_STRING) {
				return cell.getStringCellValue();
			} else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
				return String.valueOf(cell.getNumericCellValue());
			} else if (cell.getCellType() == cell.CELL_TYPE_BOOLEAN) {
				return String.valueOf(cell.getBooleanCellValue());
			} else if (cell.getCellType() == cell.CELL_TYPE_BLANK) {
				return "";
			}
		} catch (Exception e) {
			System.out.println("Exception is : " + e.getMessage());
		}
		return null;
	}

	public int getRowCount(String sheetName) {
		try {
			int index = wkb.getSheetIndex(sheetName);
			if (index == -1) {
				return 0;
			} else {
				sheet = wkb.getSheetAt(index);
				int number = sheet.getLastRowNum() + 1;
				return number;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int getColumnCount(String sheetName) {
		try {
			int index = wkb.getSheetIndex(sheetName);
			if (index == -1) {
				return 0;
			} else {
				sheet = wkb.getSheet(sheetName);
				row = sheet.getRow(0);
				return row.getLastCellNum();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static void main(String[] args) throws IOException {

		ExcelDataProvider obj = new ExcelDataProvider();
		System.out.println(obj.getColumnCount("Login"));
		System.out.println(obj.getRowCount("Login"));
		for (int i = 0; i < obj.getRowCount("Login"); i++) {
			for (int j = 0; j < obj.getColumnCount("Login"); j++) {
				System.out.print(obj.getData("Login", i, j) + " | ");
			}
			System.out.println();
		}
	}
}
