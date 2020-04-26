package com.cts.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.cts.model.Student;

@Component
public class RWExcelStudent {

	private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public List<Student> readExcel(String inputFilePath) {
		ArrayList<Student> studentList = null;
		Workbook workbook = null;
		try {
			workbook = new XSSFWorkbook(Files.newInputStream(Paths.get(inputFilePath)));
			Sheet sheet = workbook.getSheetAt(0);
			studentList = new ArrayList<>();
			for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
				Student student = new Student();
				Row ro = sheet.getRow(i);
				for (int j = ro.getFirstCellNum(); j <= ro.getLastCellNum(); j++) {
					Cell ce = ro.getCell(j);
					if (j == 0) {
						int id = (int) ce.getNumericCellValue();
						student.setId(id);
					} else if (j == 1) {
						student.setName(ce.getStringCellValue());
					} else if (j == 2) {
						student.setGrade(ce.getStringCellValue());
					} else if (j == 3) {
						int age = (int) ce.getNumericCellValue();
						student.setAge(age);
					}
				}
				studentList.add(student);
			}
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return studentList;
	}

	public Student writeExcel(Student student, String filePath) {
		System.out.println("Under RWExcelStudent file ... writeExcel method ..... ");
		// Set data = new HashSet();

		try {
			Workbook workbook = new XSSFWorkbook(Files.newInputStream(Paths.get(filePath)));
			Sheet sheet = workbook.getSheetAt(0);
			int rownum = sheet.getLastRowNum();
			int cellnum = 0;
			Row row = sheet.createRow(++rownum);
			Cell cell = row.createCell(cellnum++);
			cell.setCellValue(student.getId());

			Cell cell2 = row.createCell(cellnum++);
			cell2.setCellValue(student.getName());

			Cell cell3 = row.createCell(cellnum++);
			cell3.setCellValue(student.getGrade());

			Cell cell4 = row.createCell(cellnum++);
			cell4.setCellValue(student.getAge());

			workbook.write(Files.newOutputStream(Paths.get(filePath)));
			workbook.close();
			return student;
		} catch (Exception e) {
			LOGGER.log(Level.INFO, e.getMessage());
			return null;
		}
	}

	public Student updateExcel(Student student, String filePath) {
		System.out.println("Under RWExcelStudent file ... updateExcel method ..... ");

		try {
			Workbook workbook = new XSSFWorkbook(Files.newInputStream(Paths.get(filePath)));
			Sheet sheet = workbook.getSheetAt(0);
			int rownum = sheet.getLastRowNum();
			Cell cell = null;
			int passedId = student.getId();

			for (int i = sheet.getFirstRowNum(); i <= rownum; i++) {
				Row ro = sheet.getRow(i);
				cell = ro.getCell(0);

				int existingcellId = (int) cell.getNumericCellValue();
				if (passedId == existingcellId) {

					for (int j = ro.getFirstCellNum(); j <= ro.getLastCellNum(); j++) {

						Cell ce = ro.getCell(j);

						if (j == 0) {
							ce.setCellValue(passedId);
						} else if (j == 1) {
							ce.setCellValue(student.getName());
						} else if (j == 2) {
							ce.setCellValue(student.getGrade());
						} else if (j == 3) {
							ce.setCellValue(student.getAge());
						}
					}

				}
			}

			workbook.write(Files.newOutputStream(Paths.get(filePath)));
			workbook.close();
			return student;
		} catch (Exception e) {
			LOGGER.log(Level.INFO, e.getMessage());
			return null;
		}
	}

	public Student getStudentById(int id, String filePath) {
		List<Student> students = readExcel(filePath);
		Student student = null;
		for (Student st : students) {
			if (st.getId() == id) {
				student = st;
			}
		}
		return student;
	}

	public List<Student> getStudentByGrade(String grade, String filePath) {
		List<Student> students = readExcel(filePath);
		List<Student> studentsByGradeList = new LinkedList<>();
		for (Student st : students) {
			if (st.getGrade().equalsIgnoreCase(grade)) {
				studentsByGradeList.add(st);
			}
		}
		return studentsByGradeList;
	}

	public String writeStudentExcel(Student createStudent, String filePath) {
		try {
			Workbook workbook = new XSSFWorkbook(Files.newInputStream(Paths.get(filePath)));
			Sheet sheet = workbook.getSheetAt(0);
			int rownum = sheet.getLastRowNum();
			int cellnum = 0;
			Row row = sheet.createRow(rownum++);
			Cell cell = row.createCell(cellnum++);
			cell.setCellValue(createStudent.getId());
			Cell cell2 = row.createCell(cellnum++);
			cell2.setCellValue(createStudent.getName());
			Cell cell3 = row.createCell(cellnum++);
			cell3.setCellValue(createStudent.getGrade());
			Cell cell4 = row.createCell(cellnum++);
			cell4.setCellValue(createStudent.getAge());
			workbook.write(Files.newOutputStream((Paths.get(filePath))));
			workbook.close();
			return "Student has been creadted Successfully.";

		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "Internal Server Error");
			return "NoSuchFileException";
		}
	}

	@SuppressWarnings("resource")
	public int removeStudentFromExcel(String inputFilePath, int id) {
		System.out.println("id is : " + id);
		int removedStudentId = 0;
		int removedRowIndex = 0;
		try {
			Workbook workbook = new XSSFWorkbook(Files.newInputStream(Paths.get(inputFilePath)));
			Sheet dataSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = dataSheet.iterator();

			while (iterator.hasNext()) {

				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();

				while (cellIterator.hasNext()) {

					Cell currentCell = cellIterator.next();

					int columnIndex = currentCell.getColumnIndex();
					int rowIndex = currentCell.getRowIndex();
					if (rowIndex >= 0) {
						System.out.println("rowIndex :" + rowIndex + "columnIndex :" + columnIndex);
						// System.out.println("current cell value
						// :"+(int)currentCell.getNumericCellValue());
						if (columnIndex == 0 && (int) currentCell.getNumericCellValue() == id) {
							System.out.println("removedStudentId : " + removedStudentId + "Id : " + id);
							removedRowIndex = rowIndex;
							removedStudentId = id;
						}
					}
				}
			}
			removeRow(dataSheet, removedRowIndex);
			File file = new File(inputFilePath);
			workbook.write(Files.newOutputStream((Paths.get(inputFilePath))));
			workbook.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return removedStudentId;
	}

	public String removeRow(Sheet sheet, int rowIndex) {

		int lastRowNum = sheet.getLastRowNum();
		if (rowIndex >= 0 && rowIndex < lastRowNum) {
			sheet.shiftRows(rowIndex + 1, lastRowNum, -1);
		}
		if (rowIndex == lastRowNum) {
			Row removingRow = sheet.getRow(rowIndex);
			if (removingRow != null) {
				sheet.removeRow(removingRow);
			}
		}
		return "Deleted successfully";
	}

}
