package util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.IOException;

public class EventsPDF extends PdfPageEventHelper {

    PdfTemplate t;
    Image total;
    Image image;

    BaseColor color = BaseColor.BLACK;
    BaseColor color_title = BaseColor.BLACK;

    Font font = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL, color);
    Font font_title = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, color_title);

    String titulo;
    String footer;
    String src;
    float totalMax = 0;

    public EventsPDF(String titulo, String footer, String src) {
        this.titulo = titulo;
        this.footer = footer;
        this.src = src;
        this.totalMax = 20;
    }

    public EventsPDF(String titulo, String footer, String src, float totalMax) {
        this.titulo = titulo;
        this.footer = footer;
        this.src = src;
        this.totalMax = totalMax;
    }

    @Override
    public void onOpenDocument(PdfWriter writer, Document document) {
        t = writer.getDirectContent().createTemplate(20, 10);
        try {
            total = Image.getInstance(t);
            total.setRole(PdfName.ARTIFACT);

        } catch (BadElementException de) {
            System.out.println(de.getMessage());
        }
        try {
            if (src != null) {
                image = Image.getInstance(src);
                if (image != null) {
                    image.scaleAbsolute(document.getPageSize());
                    image.setAbsolutePosition(0, 0);
                }
            }
        } catch (BadElementException de) {
            System.out.println(de.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        PdfPTable table = new PdfPTable(2);
        PdfPTable table1 = new PdfPTable(1);
        table1.setTotalWidth(document.getPageSize().getWidth() - document.leftMargin() * 2);
        float heigth_table = 20;
        try {

            PdfContentByte canvas = writer.getDirectContent();
            // BACKGROUND 

            if (image != null) {
                writer.getDirectContentUnder().addImage(image);
            }
            canvas.beginMarkedContentSequence(PdfName.ARTIFACT);

            float x = document.leftMargin();
            float y = (document.top() + totalMax + 10);

            // HEADER
            table1.setWidths(new float[]{470f});

            PdfPCell cell = new PdfPCell(new Phrase(titulo, font_title));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(Rectangle.NO_BORDER);
            table1.addCell(cell);

            table1.writeSelectedRows(0, -1, x, y, canvas);

            // FOOTER
            table.setWidths(new int[]{25, 25});

            table.setTotalWidth(document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin());
            table.getDefaultCell().setFixedHeight(heigth_table);

            // TITULO
            PdfPCell c_footer = new PdfPCell(new Phrase(footer, font));
            c_footer.setVerticalAlignment(Element.ALIGN_MIDDLE);
            c_footer.setBorder(Rectangle.BOTTOM);
            c_footer.setBorderColor(color);

            table.addCell(c_footer);

            // FOOTER
            PdfPCell c_pagina = new PdfPCell(new Phrase("Develop Inc", font));
            c_pagina.setVerticalAlignment(Element.ALIGN_MIDDLE);
            c_pagina.setHorizontalAlignment(Element.ALIGN_RIGHT);
            c_pagina.setBorder(Rectangle.BOTTOM);
            c_pagina.setBorderColor(color);

            table.addCell(c_pagina);

            x = document.leftMargin();
            y = document.bottomMargin();

            table.writeSelectedRows(0, -1, x, y, canvas);

            canvas.endMarkedContentSequence();

        } catch (DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }

    @Override
    public void onCloseDocument(PdfWriter writer, Document document) {
//        ColumnText.showTextAligned(t, Element.ALIGN_LEFT, new Phrase(String.valueOf(writer.getPageNumber()), font), 0, 0, 0);
    }
}
