package utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ExcelReader {
    public static String getCellValue(String path, int row, int col) {
        try {
            FileInputStream file = new FileInputStream(path);
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);

            return sheet.getRow(row).getCell(col).toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
