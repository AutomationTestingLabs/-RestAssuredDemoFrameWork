package api.utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class ExcelUtility {
    public FileInputStream fileInputStream;
    public FileOutputStream fileOutputStream;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    String path;

    public ExcelUtility(String path ){
        this.path = path;
    }

    public int getTotalRows(String sheetname) throws IOException {
        fileInputStream = new FileInputStream(path);
        workbook = new XSSFWorkbook(fileInputStream);
        sheet = workbook.getSheet(sheetname);
        int lastrownum = sheet.getLastRowNum();
        workbook.close();
        fileInputStream.close();
        return lastrownum;
    }

    public int getRowCount(String sheetName) throws IOException {
        fileInputStream = new FileInputStream(path);
        workbook = new XSSFWorkbook(fileInputStream);
        sheet = workbook.getSheet(sheetName);
        int rowcount = sheet.getLastRowNum();
        workbook.close();
        fileInputStream.close();
        return rowcount ;
    }

    public int getLastCellNum(String sheetname, int lastRowNum) throws IOException {
        fileInputStream = new FileInputStream(path);
        workbook = new XSSFWorkbook(fileInputStream);
        sheet = workbook.getSheet(sheetname);
        row = sheet.getRow(lastRowNum);
        int lastcellnum = row.getLastCellNum();
        workbook.close();
        fileInputStream.close();
        return lastcellnum;
    }

    public String getCellData(String sheetname, int rownum, int column) throws IOException {
        fileInputStream = new FileInputStream(path);
        workbook = new XSSFWorkbook(fileInputStream);
        sheet = workbook.getSheet(sheetname);
        row = sheet.getRow(rownum);
        cell = row.getCell(column);

        String data;

        DataFormatter formatter = new DataFormatter();
        try{
            data = formatter.formatCellValue(cell);
        }catch (Exception e){
            data = "";
        }
        workbook.close();
        fileInputStream.close();
        return data;
    }

    public void setCellData(String sheetname, int rownum, int column, String data) throws IOException {
        File file = new File(path);
        if(!file.exists())
        {
            workbook = new XSSFWorkbook();
            fileOutputStream = new FileOutputStream(path);
            workbook.write(fileOutputStream);
        }
        fileInputStream = new FileInputStream(path);
        workbook = new XSSFWorkbook(fileInputStream);
        if(workbook.getSheetIndex(sheetname)==-1)
        {
            workbook.createSheet(sheetname);
            sheet = workbook.getSheet(sheetname);
        }if(sheet.getRow(rownum)==null){
            sheet.createRow(rownum);
            row = sheet.getRow(rownum);
        }
        cell = row.getCell(column);
        cell.setCellValue(data);
        fileOutputStream = new FileOutputStream(path);
        workbook.write(fileOutputStream);
        workbook.close();
        fileInputStream.close();
        fileOutputStream.close();
    }
}
