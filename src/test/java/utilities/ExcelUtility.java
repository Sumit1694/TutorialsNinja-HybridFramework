package utilities;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

    // ? Global public variables
    public FileInputStream fis;
    public FileOutputStream fos;
    public Workbook workbook;
    public Sheet sheet;
    public Row row;
    public Cell cell;
    public CellStyle style;
    public String path;

    // ? Constructor
    public ExcelUtility(String filePath) {
        this.path = filePath;
    }

    // ? Get total row count
    public int getRowCount(String sheetName) throws IOException {

        fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);

        int rowCount = sheet.getLastRowNum();

        workbook.close();
        fis.close();

        return rowCount;
    }

    // ? Get total cell count
    public int getCellCount(String sheetName, int rownum) throws IOException {

        fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);

        int cellCount = row.getLastCellNum();

        workbook.close();
        fis.close();

        return cellCount;
    }

    // ? Get cell data
    public String getCellData(String sheetName, int rownum, int colnum) throws IOException {

        fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        cell = row.getCell(colnum);

        DataFormatter formatter = new DataFormatter();
        String data = formatter.formatCellValue(cell);

        workbook.close();
        fis.close();

        return data;
    }

    // ? Set cell data
    public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException {

        fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(sheetName);

        row = sheet.getRow(rownum);
        if (row == null) {
            row = sheet.createRow(rownum);
        }

        cell = row.getCell(colnum);
        if (cell == null) {
            cell = row.createCell(colnum);
        }

        cell.setCellValue(data);

        fos = new FileOutputStream(path);
        workbook.write(fos);

        workbook.close();
        fis.close();
        fos.close();
    }

    // ? Green color style
    public CellStyle fillGreenColor() throws IOException {

        fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);

        style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        workbook.close();
        fis.close();

        return style;
    }

    // ? Red color style
    public CellStyle fillRedColor() throws IOException {

        fis = new FileInputStream(path);
        workbook = new XSSFWorkbook(fis);

        style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        workbook.close();
        fis.close();

        return style;
    }
}
