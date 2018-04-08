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

	// ������һ��Excel�ļ�
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

	// ��ȡExcel��ָ��sheet��������������

	public static int getExcelCountRow(String sheetName) {

		try {
			Sheet = ExcelWBook.getSheet(sheetName);

			// ��Ϊ��һ������������
			int count = (Sheet.getLastRowNum());
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

	}

	// ��ȡ��Ӧexcel�ı������
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
