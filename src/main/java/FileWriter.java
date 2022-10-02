import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileWriter {
    static String filename = System.getProperty("user.dir") + "\\src\\main\\resources\\nomenclatures.xlsx";

    public static void priceWriter(String priceValue, int cellNumber, int rowNumber) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filename);
        var workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet1 = workbook.getSheetAt(0);
        Row row = sheet1.getRow(rowNumber);
        row.createCell(cellNumber).setCellValue(priceValue);
        FileOutputStream outputStream = new FileOutputStream(new File(filename));
        workbook.write(outputStream);
        outputStream.close();
        fileInputStream.close();
    }
}
