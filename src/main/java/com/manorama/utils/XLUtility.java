package com.manorama.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.github.fge.jsonschema.main.cli.Main;
import com.manorama.setup.TestConfig;

public class XLUtility {

	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;

	List<String> arrayList1 = new ArrayList<String>();
	List<String> arrayList2 = new ArrayList<String>();
	List<String> headersList = new ArrayList<String>();

	public XLUtility(String path) {
		this.path = path;
	}

	//implemented by kanvitha
	public List<String> getCellData(String sheetName, String colName) throws IOException {

		try {
			fi = new FileInputStream(path);
			workbook = new XSSFWorkbook(fi);
			sheet = workbook.getSheet(sheetName);
			// row=sheet.getRow(0);
			int cellCount = sheet.getRow(0).getLastCellNum();
			int cellnum = 0;

			for (int i = 0; i < cellCount; i++) {
				String data = sheet.getRow(0).getCell(i).getStringCellValue();
				if (data.trim().equals(colName)) {
					cellnum = i;
				}
			}
			System.out.println("column no is: " + cellnum);
			int rowCount = sheet.getLastRowNum();
			for (int i = 0; i <= rowCount; i++) {
				String data = sheet.getRow(i).getCell(cellnum).getStringCellValue();
				arrayList1.add(data);
			}
		} catch (Exception e) {

		}
//		for(int j=0;j<al.size();j++) {
//			System.out.println(al.get(j));
//		}

		return arrayList1;
	}

	// implemented by kanvitha
	public List<String> getHeadersData(String sheetName) throws IOException {

		try {
			fi = new FileInputStream(path);
			workbook = new XSSFWorkbook(fi);
			sheet = workbook.getSheet(sheetName);
			int cellCount = sheet.getRow(0).getLastCellNum();

			for (int i = 0; i < cellCount; i++) {
				String data = sheet.getRow(0).getCell(i).getStringCellValue();
				headersList.add(data);
			}

		} catch (Exception e) {

		}
		return headersList;
	}

	// implemented by Kanvitha
	public String getCellData(String sheetName, int rownum, String colName) throws IOException {

		String data = null;

		try {
			fi = new FileInputStream(path);
			workbook = new XSSFWorkbook(fi);
			sheet = workbook.getSheet(sheetName);
			int cellCount = sheet.getRow(0).getLastCellNum();
			int cellnum = 0;

			for (int i = 0; i < cellCount; i++) {
				data = sheet.getRow(0).getCell(i).getStringCellValue();
				if (data.trim().equals(colName)) {
					cellnum = i;
				}
			}
			// System.out.println("column no is: "+cellnum);
			data = sheet.getRow(rownum).getCell(cellnum).getStringCellValue();

		} catch (Exception e) {

		}
		return data;

	}

	public int getRowCount(String sheetName) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		int rowcount = sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowcount;
	}

	public int getCellCount(String sheetName, int rownum) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		int cellcount = row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellcount;
	}

	public String getCellData(String sheetName, int rownum, int colnum) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);
		
		DataFormatter formatter = new DataFormatter();
		String data;
		try {
			data = formatter.formatCellValue(cell); // Returns the formatted value of a cell as a String regardless of
													// the cell type.
		} catch (Exception e) {
			data = "";
		}
		workbook.close();
		fi.close();
		return data;
	}

	public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {
		File xlfile = new File(path);
		if (!xlfile.exists()) // If file not exists then create new file
		{
			workbook = new XSSFWorkbook();
			fo = new FileOutputStream(path);
			workbook.write(fo);
		}

		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);

		if (workbook.getSheetIndex(sheetName) == -1) // If sheet not exists then create new Sheet
			workbook.createSheet(sheetName);
		sheet = workbook.getSheet(sheetName);

		if (sheet.getRow(rownum) == null) // If row not exists then create new Row
			sheet.createRow(rownum);
		row = sheet.getRow(rownum);

		cell = row.createCell(colnum);
		cell.setCellValue(data);
		fo = new FileOutputStream(path);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
	}

	public void fillGreenColor(String sheetName, int rownum, int colnum) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);

		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);

		style = workbook.createCellStyle();

		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(style);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
	}

	public void fillRedColor(String sheetName, int rownum, int colnum) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rownum);
		cell = row.getCell(colnum);

		style = workbook.createCellStyle();

		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(style);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
	}
	
	public static void main(String[] args) throws Exception {
		XLUtility xlUtil = new XLUtility(".//src//main//resources//Test_Data//TestData_TG//TestData_Manorama.xlsx");
		String dat = xlUtil.getCellData("Manorama", 1, "Prefix_val");
		String data = xlUtil.getCellData("Sheet1", 1, "FIRST_NAME");
		String data1 = xlUtil.getCellData("Sheet1", 1, "MIDDLE_NAME");
		String data2 = xlUtil.getCellData("Sheet1", 1, "LAST_NAME");
		String fullname =data+""+data1+" "+data2;
		System.out.println(fullname);
		List<String> valv = xlUtil.getCellData("Sheet1","ADDRESS");
		for (String v : valv) {
			System.out.println(v);
		}
		
	}
}
