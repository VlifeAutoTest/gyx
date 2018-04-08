package cn.phone.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelMethods {

	private static XSSFWorkbook ExcelWBook;
	private static XSSFSheet Sheet;
	private static XSSFCell Cell;

	// 设置哪一个Excel文件
	public static void setExcel(String ExcelPath) {

		try {

			FileInputStream fis = new FileInputStream(ExcelPath);

			ExcelWBook = new XSSFWorkbook(fis);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// 获取Excel是指定sheet中用例的总行数

	public static int getExcelCountRow(String sheetName) {

		try {
			Sheet = ExcelWBook.getSheet(sheetName);

			// 因为第一行是用例名称
			int count = (Sheet.getLastRowNum());
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

	// 获取对应excel的表格数据
	public static String getCellData(String ExcelSheetName, int rownum, int colnum) {

		try {
			Sheet = ExcelWBook.getSheet(ExcelSheetName);
			Cell = Sheet.getRow(rownum).getCell(colnum);
			Cell.setCellType(CellType.STRING);
			String CellData = Cell.getStringCellValue();

			return CellData;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

}
