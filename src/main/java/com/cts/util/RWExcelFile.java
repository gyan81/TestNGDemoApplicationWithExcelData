package com.cts.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.client.RestTemplate;

import com.cts.model.User;

public class RWExcelFile {

	
	private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private int rownum;
	private int cellnum;
	File file;

	RWExcelFile() {
		rownum = 0;
		cellnum = 0;
		file = new File("./src/main/resources/excel/login.xlsx");
	}

	public void readExcel(String inputFilePath) {
		RestTemplate restTemplate = new RestTemplate();
		// TechDTO techDto = new TechDTO();
		try {

			/*
			 * FileInputStream excelFile = new FileInputStream(new
			 * File(inputFilePath)); Workbook workbook = new
			 * XSSFWorkbook(excelFile);
			 */
			Workbook workbook = new XSSFWorkbook(
					Files.newInputStream(Paths.get("./src/main/resources/excel/students.xlsx")));
			Sheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();

			while (iterator.hasNext()) {

				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();

				while (cellIterator.hasNext()) {

					Cell currentCell = cellIterator.next();
					if (currentCell.getCellTypeEnum() == CellType.STRING) {
						LOGGER.info(currentCell.getStringCellValue() + "--");
						int columnIndex = currentCell.getColumnIndex();
						int rowIndex = currentCell.getRowIndex();
						if (rowIndex > 0) {
							if (columnIndex == 1) {
							}
							if (columnIndex == 2) {
								String input = currentCell.getStringCellValue();
								if (input.endsWith(".json")) {
								}
							}
							if (columnIndex == 3) {
							}
							if (columnIndex == 4) {
							
							}
						}
					} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
						LOGGER.log(Level.INFO, currentCell.getNumericCellValue() + "--");
					}
				}
			}
		} catch (FileNotFoundException e) {
			LOGGER.log(Level.WARNING, "FileNotFoundException:" + e.getLocalizedMessage());
		} catch (IOException e) {
			LOGGER.log(Level.WARNING, "FileNotFoundException:" + e.getLocalizedMessage());
		}

	}

	public String writeExcel(User userDto) {
		XSSFWorkbook workbook = new XSSFWorkbook();

		XSSFSheet sheet = workbook.createSheet("User Details");

		int rownum = 0;

		Row row = sheet.createRow(rownum++);

		Cell cell = row.createCell(cellnum++);

		cell.setCellValue(userDto.getUserId());

		Cell cell2 = row.createCell(cellnum++);
		cell2.setCellValue(userDto.getPassword());

		Cell cell3 = row.createCell(cellnum++);
		cell3.setCellValue(userDto.getFirstName());

		Cell cell4 = row.createCell(cellnum++);
		cell4.setCellValue(userDto.getLastName());
		try {
			/*
			 * FileOutputStream out = new FileOutputStream(file);
			 * workbook.write(out); out.close();
			 */
			workbook = new XSSFWorkbook(Files.newInputStream(Paths.get("./src/main/resources/excel/students.xlsx")));
			workbook.close();
			return "User Registered Successfully";
		} catch (Exception e) {
			e.printStackTrace();
			return "Internal Server Error";
		}
	}

}
