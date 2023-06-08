package ReadExcelData_Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	public static List<Map<String, String>> listAllData;

	public static List<Map<String, String>> getAllData(String excelFilePath, String sheetName) throws IOException {
		Workbook wBook = getWorkBook(excelFilePath);
		Sheet sheet = wBook.getSheet(sheetName);
		int lastRowN = sheet.getLastRowNum();
		listAllData = new ArrayList<Map<String, String>>();
		for (int i = 1; i <= lastRowN; i++) {
			Map<String, String> mapObj = new HashedMap<String, String>();
			Row row = sheet.getRow(i);
			Row firstRow = sheet.getRow(0);
			int lastCellN = row.getLastCellNum();
			for (int j = 0; j <= lastCellN - 1; j++) {
				String cellValue = getCellValue(row, j);
				String keyName = getCellValue(firstRow, j);
				mapObj.put(keyName, cellValue);
			}
			listAllData.add(mapObj);
		}
		return listAllData;
	}

	public static Map<String, String> getData(String excelFilePath, String sheetName, int rowNo) throws IOException {
		Workbook wBook = getWorkBook(excelFilePath);
		Sheet sheet = wBook.getSheet(sheetName);
		Map<String, String> mapObj = new HashedMap<String, String>();
		Row row = sheet.getRow(rowNo);
		Row firstRow = sheet.getRow(0);
		int lastCellN = row.getLastCellNum();
		for (int j = 0; j < lastCellN; j++) {
			String cellValue = getCellValue(row, j);
			String keyName = getCellValue(firstRow, j);
			mapObj.put(keyName, cellValue);
		}
		return mapObj;
	}

	public static Workbook getWorkBook(String excelFilePath) throws IOException {
		File fls = new File(excelFilePath);
		InputStream ins = new FileInputStream(fls);
		Workbook wBook = new XSSFWorkbook(ins);
		return wBook;
	}

	public static String getCellValue(Row row, int cellNumber) {
		String cellValue;
		Cell cell = row.getCell(cellNumber, MissingCellPolicy.CREATE_NULL_AS_BLANK);
		if (cell.getCellType() == CellType.STRING) {
			cellValue = cell.getStringCellValue();
		} else{
			Double doubleValue = cell.getNumericCellValue();
			Integer intValue = doubleValue.intValue();
			cellValue = intValue.toString();
		} 
		return cellValue;
	}
}
