package com.example.springbootdownloadexcelfile.util;

import com.example.springbootdownloadexcelfile.entity.Product;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelUtil {

    public static String HEADER[] = {"id", "name", "price", "quantity"};

    public static String SHEET_NAME = "sheetForProductData";
    public static ByteArrayInputStream dataToExcel(List<Product> productList) throws IOException {
        Workbook workbook  = new XSSFWorkbook();

        ByteArrayOutputStream byteArrayOutputStream  = new ByteArrayOutputStream();
        try {
        Sheet sheet = workbook.createSheet(SHEET_NAME);
        Row row = sheet.createRow(0);

          for (int i  =0; i< HEADER.length;i++){

           Cell cell = row.createCell(i);
           cell.setCellValue(HEADER[i]);
        }

           int rowIndex = 1;
          for (Product p :productList){
              Row row1 = sheet.createRow(rowIndex);
              rowIndex++;
              row1.createCell(0).setCellValue(p.getId());
              row1.createCell(1).setCellValue(p.getName());
              row1.createCell(2).setCellValue(p.getPrice());
              row1.createCell(3).setCellValue(p.getQuantity());
          }

            workbook.write(byteArrayOutputStream);
            return  new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
       finally {
            workbook.close();
            byteArrayOutputStream.close();
        }
    }
}
