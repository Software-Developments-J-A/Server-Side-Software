package pe.edu.upc.ferreshop.export;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import pe.edu.upc.ferreshop.entities.Category;
import pe.edu.upc.ferreshop.entities.Product;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProductExcelExporter {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Product> product;


    public ProductExcelExporter (List<Product> products) {
        this.product = products;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Productos");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();

        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "ID", style);
        createCell(row, 1, "Nombre", style);
        createCell(row, 2, "Summary", style);
        createCell(row, 3, "Brand", style);
        createCell(row, 4, "Quantity", style);
        createCell(row, 5, "Price", style);
        createCell(row, 6, "Status", style);
        createCell(row, 7, "Business", style);
        createCell(row, 8, "Categoria", style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {

        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);

        if(value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if(value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue((String) value);
        }

        cell.setCellStyle(style);

    }


    private void writeDataLines() {

        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for( Product result: product) {

            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, String.valueOf(result.getId()), style);
            createCell(row, columnCount++, result.getName(), style);
            createCell(row, columnCount++, result.getSummary(), style);
            createCell(row, columnCount++, result.getBrand(), style);
            createCell(row, columnCount++, result.getQuantity(), style);
            createCell(row, columnCount++, result.getPrice(), style);
            createCell(row, columnCount++, result.isStatus(), style);
            createCell(row, columnCount++, result.getBusiness(), style);
            createCell(row, columnCount++, result.getCategory(), style);
        }
    }


    public void export(HttpServletResponse response) throws IOException {

        writeHeaderLine();
        writeDataLines();

        ServletOutputStream servletOutput = response.getOutputStream();
        workbook.write(servletOutput);
        workbook.close();

        servletOutput.close();

    }
}
