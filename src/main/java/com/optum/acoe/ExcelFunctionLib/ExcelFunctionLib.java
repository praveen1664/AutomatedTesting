package com.optum.acoe.ExcelFunctionLib;


import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.optum.acoe.ApplicationFunLib.ErrorData;
import com.optum.acoe.Utils.PropertyReader;

import org.apache.log4j.Logger;

public class ExcelFunctionLib {
	
	private static XSSFWorkbook workbook = null;
	private static XSSFSheet sheet = null;
	private Row row = null;
	String key = "";
	static Logger log = Logger.getLogger(ExcelFunctionLib.class);
	
	/**
	 * Returns number of rows in an excel file with given sheetname.
	 * @param filepath
	 * @param sheetname
	 * @return int - number of rows.
	 * @throws IOException 
	 * @throws Exception 
	 */
	public int GetRowCount(String filepath, String sheetname) throws IOException {
		int a=0;
		FileInputStream file = null;
		try {
			file = new FileInputStream(new File(filepath));
			workbook = new XSSFWorkbook(file);
			sheet = workbook.getSheet(sheetname);
			a = sheet.getLastRowNum();
			file.close();
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
		}
		
		return a;

	}
	
	/**
	 * Clears the data in excel sheet
	 * @param filepath
	 * @param sheetname
	 * @throws Exception
	 */
	public void clearExcel(String filepath, String sheetname) throws Exception {
		
		FileInputStream  fis = null;
		XSSFWorkbook wb = null;
		File file = null;
		
		try {
			
			file = new File(filepath);
			fis = new FileInputStream(file);
			wb = new XSSFWorkbook(fis);
			sheet = wb.getSheet(sheetname);
			Iterator<Row> itr = sheet.rowIterator();
			while(itr.hasNext()){ 
				
				itr.next();
			    itr.remove();
			 
			}
			
			fis.close();
			FileOutputStream fos = new FileOutputStream(file);
			wb.write(fos);
			fos.close();
			wb.close();
		}
		catch(Exception e) {
			log.error(e);
			e.printStackTrace();
		}

	}
	
	/**
	 * Returns the row index value for the cell which has data that matches with rowflag.
	 * @param filePath
	 * @param Sheetname
	 * @param Rowflag
	 * @return int
	 * @throws Exception 
	 */
	public int GetRowNumforRowFlag(String filePath, String Sheetname, String Rowflag) throws Exception {
		int Rowflagnum = 0;
		FileInputStream file = null;
		try {
			file = new FileInputStream(new File(filePath));
			workbook = new XSSFWorkbook(file);
			sheet = workbook.getSheet(Sheetname);
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				row = rowIterator.next();
				if (row.getRowNum() != 0) {
					Iterator<Cell> cellIterator = row.cellIterator();
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
//						cell.setCellType(Cell.CELL_TYPE_STRING);
						key = cell.getStringCellValue();
						if (key.equals(Rowflag)) {
	
							Rowflagnum = row.getRowNum();
							return Rowflagnum;
						}
	
					}
				}
			}
			file.close();
		}
		catch(Exception e) {
			log.error(e);
			throw e;
		}
		
		return Rowflagnum;
	}
	
	/**
	 * Returns the entire row values as hashmap of strings that has cell value matching with rowflag.
	 * @param filePath
	 * @param Sheetname
	 * @param Rowflag
	 * @return HashMap <String, String>
	 * @throws IOException
	 */
	public HashMap<String, String> readRowData(String filePath, String Sheetname, String Rowflag) throws IOException {
		HashMap<String, String> rowData = new HashMap<String, String>();
		FileInputStream file = null;
		try {
			file = new FileInputStream(new File(filePath));
			XSSFWorkbook wb = new XSSFWorkbook(file);
			XSSFSheet sht = wb.getSheet(Sheetname);
			Iterator<Row> irows = sht.rowIterator();
			while (irows.hasNext()) {
				Row irow = irows.next();
				if (irow.getCell(0).getStringCellValue().equalsIgnoreCase(Rowflag)) {
					Row vrow = irows.next();
					for (int i = 1; i < irow.getLastCellNum(); i++) {
						rowData.put(irow.getCell(i).getStringCellValue(), vrow.getCell(i).getStringCellValue());
					}
	
					break;
				}
			}
			wb.close();
			file.close();
		}
		catch(Exception e) {
			log.error(e);
			throw e;
		}
		
		return rowData;
	}

	/**
	 * Enters String data into excel cell with given sheet name and cell details.
	 * @param row
	 * @param col
	 * @param data
	 * @param filepath
	 * @param sheetname
	 * @throws IOException
	 */
	public void enterDataToExcel(int row, int col, String data, String filepath, String sheetname) throws IOException {
		FileInputStream  fis = null;
		XSSFWorkbook wb = null;
		File file = null;
		try {
			file = new File(filepath);
			fis = new FileInputStream(file);
			wb = new XSSFWorkbook(fis);
			if (file.isFile() && file.exists()) { 
	            XSSFSheet sht = wb.getSheet(sheetname);
	            XSSFRow rrow = sht.getRow(row);
	            if(rrow == null) {
	            	rrow = sht.createRow(row);
	            }
	            XSSFCell cell = rrow.getCell(col);
	            if(cell == null) {
	            	cell = rrow.createCell(col);
	            }
	            cell.setCellValue(data);
	            fis.close();
	            FileOutputStream fos = new FileOutputStream(file);
				wb.write(fos);
				wb.close();
	        } 
	        else { 
	            System.out.println("xlsx either not exist"
	                               + " or can't open"); 
	        }
			
			
		}
		catch(Exception e) {
			log.error(e);
		}
		
	}
	
	/**
	 * Enters String data into excel cell with given sheet name and cell details.
	 * @param row
	 * @param col
	 * @param data
	 * @param filepath
	 * @param sheetname
	 * @throws IOException
	 */
	public void enterDataToExcel(int row, int col, int data, String filepath, String sheetname) throws IOException {
		
		FileInputStream  fis = null;
		XSSFWorkbook wb = null;
		File file = null;
		try {
			file = new File(filepath);
			fis = new FileInputStream(file);
			wb = new XSSFWorkbook(fis);
			if (file.isFile() && file.exists()) { 
	            XSSFSheet sht = wb.getSheet(sheetname);
	            XSSFRow rrow = sht.getRow(row);
	            if(rrow == null) {
	            	rrow = sht.createRow(row);
	            }
	            XSSFCell cell = rrow.getCell(col);
	            if(cell == null) {
	            	cell = rrow.createCell(col);
	            }
	            cell.setCellValue(data);
	            fis.close();
	            FileOutputStream fos = new FileOutputStream(file);
				wb.write(fos);
				wb.close();
	        } 
	        else { 
	            System.out.println("xlsx either not exist"
	                               + " or can't open"); 
	        }
		}
		catch(Exception e) {
			log.error(e);
			
		}		
	
	}
	
	/**
	 * Returns the data in the excel sheet cell with given sheet name and cell details.
	 * @param row
	 * @param col
	 * @param filepath
	 * @param sheetname
	 * @return
	 * @throws IOException
	 */
	public String getDataFromExcel(int row, int col, String filepath, String sheetname) throws IOException {
		String data = null;
		FileInputStream  fis = null;
		XSSFWorkbook wb = null;
		File file = null;
		try {
			file = new File(filepath);
			fis = new FileInputStream(file);
			wb = new XSSFWorkbook(fis);
			if (file.isFile() && file.exists()) { 
	            XSSFSheet sht = wb.getSheet(sheetname);
	            XSSFRow rrow = sht.getRow(row);
	            if(rrow == null) {
	            	rrow = sht.createRow(row);
	            }
	            XSSFCell cell = rrow.getCell(col);
	            if(cell == null) {
	            	cell = rrow.createCell(col);
	            }
	           data = cell.getStringCellValue();
	           fis.close();
				FileOutputStream fos = new FileOutputStream(file);
				wb.write(fos);
				wb.close();
	        } 
	        else { 
	            System.out.println("xlsx either not exist"
	                               + " or can't open"); 
	        }
		}
		catch(Exception e) {
			log.error(e);
		
		}
		
		return data;
		
	}


	public void enterErrorDataInfo(String businessgroupOption, String assetsgroupOption, String appname, String colwithnullValue,
			int rowindex, int columnindex, String file) throws IOException {
			
			int row = GetRowCount(file, "Sheet1");
			++row;
			enterDataToExcel( row, 0 , businessgroupOption, file, "Sheet1");
			enterDataToExcel( row, 1 , assetsgroupOption, file, "Sheet1");
			enterDataToExcel( row, 2 , appname, file, "Sheet1");
			enterDataToExcel( row, 3 , colwithnullValue, file, "Sheet1");
			enterDataToExcel( row, 4 , rowindex, file, "Sheet1");
			enterDataToExcel( row, 5 , columnindex, file, "Sheet1");
		
		
	}

	public void enterErrorDataInfo(LinkedList<ErrorData> errorrows) throws Exception {
		
		int rownum = 1;
		PropertyReader reader = new PropertyReader();
		String filepath = System.getProperty("user.dir") + "/" + reader.readProperty("validatenullvalues");
		File file = null;
		try {
			file = new File(filepath);
			workbook = new XSSFWorkbook();
			sheet = workbook.createSheet("Sheet1");
			
			Row headerrow = sheet.createRow(0);
			
			//adding column names in excel sheet
			headerrow.createCell(0).setCellValue("BusinessGroup");
			headerrow.createCell(1).setCellValue("AssetsGroup");
			headerrow.createCell(2).setCellValue("App Name");
			headerrow.createCell(3).setCellValue("Column With Null Value");
			headerrow.createCell(4).setCellValue("Page number");
			headerrow.createCell(5).setCellValue("row index");
			headerrow.createCell(6).setCellValue("Column index");
			
			//creating array of cell
			for(ErrorData e: errorrows) {
				Row row = sheet.createRow(rownum);
				
				//Entering error data into excel sheet
				row.createCell(0).setCellValue(e.getBusinessgroupOption());
				row.createCell(1).setCellValue(e.getAssetsgroupOption());
				row.createCell(2).setCellValue(e.getAppname());
				row.createCell(3).setCellValue(e.getColwithnullValue());
				row.createCell(4).setCellValue(e.getPage());
				row.createCell(5).setCellValue(e.getRowindex());
				row.createCell(6).setCellValue(e.getRowindex());
				
				rownum++;
				
			}
			
			
		}
		catch(Exception e) {
			
			log.error(e);
			e.printStackTrace();
			throw e;
		}
		finally {
		
			FileOutputStream fos = new FileOutputStream(file);
			workbook.write(fos);
			fos.close();
			
		}
		
	}

	public static void generateGenericReport(int totalScenario, int failedScenario, LinkedHashMap<String, String> scenarios) throws IOException {
		
		PropertyReader reader = new PropertyReader();
		String filepath = System.getProperty("user.dir") + "/" + reader.readProperty("genericreport");
		File file = null;
		try {
			file = new File(filepath);
			workbook = new XSSFWorkbook();
			sheet = workbook.createSheet("Sheet1");
			
			XSSFCellStyle style=workbook.createCellStyle();
			style.setBorderTop(BorderStyle.MEDIUM);
			style.setBorderBottom(BorderStyle.MEDIUM);
			style.setBorderLeft(BorderStyle.MEDIUM);
			style.setBorderRight(BorderStyle.MEDIUM);
			style.setWrapText(true);
			
			
			Cell cell = sheet.createRow(3).createCell(5);
			cell.setCellStyle(style);
			cell.setCellValue("Passed number of scenario's");
			
			cell = sheet.getRow(3).createCell(6);
			cell.setCellStyle(style);
			cell.setCellValue(totalScenario - failedScenario);
			
			cell = sheet.createRow(4).createCell(5);
			cell.setCellStyle(style);
			cell.setCellValue("Failed number of scenario's");
			
			cell = sheet.getRow(4).createCell(6);
			cell.setCellStyle(style);
			cell.setCellValue(failedScenario);
			
			cell = sheet.createRow(5).createCell(5);
			cell.setCellStyle(style);
			cell.setCellValue("Total number of scenario's");
			
			cell = sheet.getRow(5).createCell(6);
			cell.setCellStyle(style);
			cell.setCellValue(totalScenario);
			
			cell = sheet.createRow(8).createCell(0);
			cell.setCellStyle(style);
			cell.setCellValue("Scenario Name");
			
			cell = sheet.getRow(8).createCell(1);
			cell.setCellStyle(style);
			cell.setCellValue("Scenario Status");
			
			Set<String> keys = scenarios.keySet();
			Iterator<String> itr = keys.iterator();
			
			int i = 9;
			
			while(itr.hasNext()) {
				
				String temp = itr.next();
				cell = sheet.createRow(i).createCell(0);
				cell.setCellStyle(style);
				cell.setCellValue(temp);
				
				cell = sheet.getRow(i).createCell(1);
				cell.setCellStyle(style);
				cell.setCellValue(scenarios.get(temp));
				i++;
				
			}
		}
		catch(Exception e) {
			
			log.error(e);
			e.printStackTrace();
			throw e;
		}
		finally {
		
			FileOutputStream fos = new FileOutputStream(file);
			workbook.write(fos);
			fos.close();
			
		}
	}

	public static void writeErrorInfoValidateTCO(HashMap<String, Integer> errorTCO, Integer actualPageTCO, Integer actualTCOsum) throws IOException {
		// TODO Auto-generated method stub
		PropertyReader reader = new PropertyReader();
		String filepath = System.getProperty("user.dir") + "/" + reader.readProperty("validateTCOsum");
		File file = null;
		try {
			file = new File(filepath);
			workbook = new XSSFWorkbook();
			sheet = workbook.createSheet("Sheet1");
			
			XSSFCellStyle style=workbook.createCellStyle();
			style.setBorderTop(BorderStyle.MEDIUM);
			style.setBorderBottom(BorderStyle.MEDIUM);
			style.setBorderLeft(BorderStyle.MEDIUM);
			style.setBorderRight(BorderStyle.MEDIUM);
			style.setWrapText(true);
			
			//Adding headers
			Cell cell = sheet.createRow(0).createCell(0);
			cell.setCellStyle(style);
			cell.setCellValue("Sum name");
			
			cell = sheet.getRow(0).createCell(1);
			cell.setCellStyle(style);
			cell.setCellValue("expected value");
			
			cell = sheet.getRow(0).createCell(2);
			cell.setCellStyle(style);
			cell.setCellValue("Available value");
			
			int i=1;
			
			Set<String> keys = errorTCO.keySet();
			
			if(keys.contains("pagetco")) {
				cell = sheet.createRow(i).createCell(0);
				cell.setCellStyle(style);
				cell.setCellValue("PageTCOSum");
				
				cell = sheet.getRow(i).createCell(1);
				cell.setCellStyle(style);
				cell.setCellValue(actualPageTCO);
				
				cell = sheet.getRow(i).createCell(2);
				cell.setCellStyle(style);
				cell.setCellValue(errorTCO.get("pagetco"));
				i++;
			}
			else {
				cell = sheet.createRow(i).createCell(0);
				cell.setCellStyle(style);
				cell.setCellValue("totalTCOSum");
				
				cell = sheet.getRow(i).createCell(1);
				cell.setCellStyle(style);
				cell.setCellValue(actualTCOsum);
				
				cell = sheet.getRow(i).createCell(2);
				cell.setCellStyle(style);
				cell.setCellValue(errorTCO.get("totaltco"));
				i++;
			}
		}
		catch(NullPointerException e) {
			
		}
		catch(Exception e) {
			
			log.error(e);
			e.printStackTrace();
			throw e;
		}
		finally {
		
			FileOutputStream fos = new FileOutputStream(file);
			workbook.write(fos);
			fos.close();
			
		}
	}

	public static void writeErrorBookedValueInToExcel(LinkedList<String> errorData) throws IOException {
		// TODO Auto-generated method stub
		PropertyReader reader = new PropertyReader();
		String filepath = System.getProperty("user.dir") + "/" + reader.readProperty("BookedvalueTable");
		File file = null;
		try {
			file = new File(filepath);
			workbook = new XSSFWorkbook();
			sheet = workbook.createSheet("Sheet1");
			
			XSSFCellStyle style=workbook.createCellStyle();
			style.setBorderTop(BorderStyle.MEDIUM);
			style.setBorderBottom(BorderStyle.MEDIUM);
			style.setBorderLeft(BorderStyle.MEDIUM);
			style.setBorderRight(BorderStyle.MEDIUM);
			style.setWrapText(true);
			
			//Adding headers
			Cell cell = sheet.createRow(0).createCell(0);
			cell.setCellStyle(style);
			cell.setCellValue("expected value");
			
			cell = sheet.getRow(0).createCell(1);
			cell.setCellStyle(style);
			cell.setCellValue("available value");
			
			for(int i=0; i < errorData.size(); i++) {
				
				
				String temp = errorData.get(i);
				StringTokenizer token = new StringTokenizer(temp,"|");
				cell = sheet.createRow(i+1).createCell(0);
				cell.setCellStyle(style);
				System.out.println("temp from split0" + temp);
				
				cell = sheet.getRow(i+1).createCell(1);
				cell.setCellStyle(style);
				temp = token.nextToken();
				cell.setCellValue(token.nextToken());
			}
			
			
		}
		catch(Exception e) {
			
			log.error(e);
			e.printStackTrace();
			throw e;
		}
		finally {
		
			FileOutputStream fos = new FileOutputStream(file);
			workbook.write(fos);
			fos.close();
			
		}
	}

	public static void writeTechPaduErrorvalues(HashMap<String, Integer> map1, HashMap<String, Integer> map2) throws IOException {
		// TODO Auto-generated method stub
		PropertyReader reader = new PropertyReader();
		String filepath = System.getProperty("user.dir") + "/" + reader.readProperty("TechPaduErrorData");
		File file = null;
		XSSFCellStyle style = null;
		
		try {
			
			file = new File(filepath);
			workbook = new XSSFWorkbook();
			sheet = workbook.createSheet("Sheet1");
			
			style=workbook.createCellStyle();
			style.setBorderTop(BorderStyle.MEDIUM);
			style.setBorderBottom(BorderStyle.MEDIUM);
			style.setBorderLeft(BorderStyle.MEDIUM);
			style.setBorderRight(BorderStyle.MEDIUM);
			style.setWrapText(true);
			
			org.junit.Assert.assertEquals(map1, map2);
		}
		catch(AssertionError e) {
			
			
			
			//Adding headers
			Cell cell = sheet.createRow(0).createCell(0);
			cell.setCellStyle(style);
			cell.setCellValue("expected value");
			
			cell = sheet.getRow(0).createCell(1);
			cell.setCellStyle(style);
			cell.setCellValue("available value");
			
			cell = sheet.createRow(1).createCell(0); 
			cell.setCellStyle(style);
			cell.setCellValue(map1.toString());
			
			cell = sheet.getRow(1).createCell(1);
			cell.setCellStyle(style);
			cell.setCellValue(map2.toString());
			
		}
		finally {
			
			Cell cell = sheet.createRow(0).createCell(0);
			cell.setCellStyle(style);
			cell.setCellValue("No Error Data");
			
			FileOutputStream fos = new FileOutputStream(file);
			workbook.write(fos);
			fos.close();
			
		}
		
	}
}
