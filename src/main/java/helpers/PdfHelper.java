package helpers;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.font.FontEncoding;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import models.InterestRate;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class PdfHelper {
    public static ByteArrayOutputStream generate(ArrayList<InterestRate> interestRateList) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            PdfWriter writer = new PdfWriter(byteArrayOutputStream);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf, PageSize.A4);

            document.setMargins(20, 100, 20, 100);
            PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN, PdfEncodings.CP1250);
            PdfFont bold = PdfFontFactory.createFont(FontConstants.TIMES_BOLD, PdfEncodings.CP1250);

            float[] pointColumnWidths = {6, 10, 10, 10, 12};
            Table table = new Table(pointColumnWidths);
            table.addHeaderCell(newCell("Nr raty", bold));
            table.addHeaderCell(newCell("Kwota kapitału", bold));
            table.addHeaderCell(newCell("Kwota odsetek", bold));
            table.addHeaderCell(newCell("Opłaty stałe", bold));
            table.addHeaderCell(newCell("Całkowita kwota raty", bold));

            int id = 1;
            for (InterestRate interestRate : interestRateList) {
                table.addCell(newCell(Integer.toString(id), font));
                table.addCell(newCell(String.format("%.2f", interestRate.getCapitalValue()), font));
                table.addCell(newCell(String.format("%.2f", interestRate.getTaxValue()), font));
                table.addCell(newCell(String.format("%.2f", interestRate.getConstPayValue()), font));
                table.addCell(newCell(String.format("%.2f", interestRate.getTotalRateValue()), font));
                id++;
            }

            document.add(table);
            document.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return byteArrayOutputStream;
    }

    private static Cell newCell(String value, PdfFont font) {
        Cell cell = new Cell();
        cell.setMinHeight(20);

        cell.setVerticalAlignment(VerticalAlignment.MIDDLE);
        cell.setHorizontalAlignment(HorizontalAlignment.CENTER);
        cell.setFont(font);

        cell.add(value);

        return cell;
    }
}
