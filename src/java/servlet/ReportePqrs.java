package servlet;

import Util.PositionPdfPCell.ImagenAbsolute;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dto.Mensaje;
import dto.TipoMensaje;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.postgres.Service;

public class ReportePqrs extends HttpServlet {

    private HttpSession session;
    private OutputStream outStream;

    private final static BaseColor bg_TH = new BaseColor(148, 240, 217);
    private final static BaseColor bg_TD = new BaseColor(240, 240, 240);

    private static final BaseColor color_TEXT = new BaseColor(30, 30, 30);

    private static final Font font_normal = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL, color_TEXT);
    private static final Font font_TH = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL, color_TEXT);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String titulo = "ESTUDIANTES EN LIMPIO";

        final String CONTEXTO = request.getServletContext().getRealPath("/");
        session = request.getSession();
        Service controlador = (Service) session.getAttribute("controlador");

        if (session.getAttribute("usuarioActual") != null) {
            try {
                response.setContentType("application/pdf");
                Rectangle r = new Rectangle(PageSize.LETTER);
                outStream = response.getOutputStream();

                Document documento = new Document(r, 10, 10, 10, 10);

                PdfWriter.getInstance(documento, outStream);

                Date fechaActual = new Date();
                String fecha;
                SimpleDateFormat formtFechaBd = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");

                fecha = formtFechaBd.format(fechaActual);
                response.addHeader("Content-disposition", "attachment; filename=" + titulo + "_" + fecha + ".pdf");

                documento.open();
                documento.addTitle(titulo);

                PdfPTable table_main = new PdfPTable(1);
                table_main.setWidthPercentage(100);
                PdfPTable table;
                PdfPCell cell;
                String text_vacio = "\u0020";
                PdfPCell cell_imagen = new PdfPCell(new Phrase(text_vacio));

                Image img;

                boolean imagenNone = false;
                try {

                    cell_imagen = new PdfPCell(new Phrase(text_vacio));
                    img = Image.getInstance("");
                    cell_imagen.setCellEvent(new ImagenAbsolute(img));
                } catch (BadElementException | IOException e) {
                    imagenNone = true;
                }
                if (imagenNone) {
                    try {
                        img = Image.getInstance(CONTEXTO + "recursos/img/noDisponible.png");
                        cell_imagen.setCellEvent(new ImagenAbsolute(img));
                    } catch (BadElementException e) {
                    } catch (IOException e) {
                    }
                }
//                table = laminaEstudiante(estudiante, cell_imagen, gradoDTO, descripcion);
//
//                if (table != null) {
//                    cell = new PdfPCell(table);
//                    cell.setBorder(Rectangle.NO_BORDER);
//                    table_main.addCell(cell);
//
//                    cell = new PdfPCell(new Phrase("\u0020"));
//                    cell.setBorder(Rectangle.NO_BORDER);
//                    table_main.addCell(cell);
//                }

                documento.add(table_main);

                documento.close();
            } catch (DocumentException ex) {

            }

        } else {
            session.setAttribute("mensaje", new Mensaje("Error", "Error al tratar de generar el reporte.", TipoMensaje.ERROR));
            response.sendRedirect(request.getContextPath() + "/main/generar_reportes.jsp");
        }
    }

//    private PdfPTable laminaEstudiante(Soli, PdfPCell imagen, GradosColegioDTO gradoDTO, String descrpcion) throws DocumentException {
//
//        PdfPTable tabla = new PdfPTable(10);
//        int height_cell = 20;
//
//        tabla.setWidthPercentage(100);
//
//        float[] columnsSizes = new float[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
//
//        tabla.setWidths(columnsSizes);
//
//        PdfPCell cell = imagen;
//        cell.setColspan(5);
//        cell.setRowspan(6);
//        tabla.addCell(cell);
//
//        cell = new PdfPCell(new Phrase("Apellidos y Nombres", font_TH));
//        cell.setColspan(5);
//        cell.setFixedHeight(height_cell);
//        cell.setBackgroundColor(bg_TH);
//        tabla.addCell(cell);
//
//        cell = new PdfPCell(new Phrase(estudiante.getNombreCompleto(), font_normal));
//        cell.setColspan(5);
//        cell.setFixedHeight(height_cell);
//        cell.setBackgroundColor(bg_TD);
//        tabla.addCell(cell);
//
//        cell = new PdfPCell(new Phrase("Curso y Grado", font_TH));
//        cell.setColspan(3);
//        cell.setFixedHeight(height_cell);
//        cell.setBackgroundColor(bg_TH);
//        tabla.addCell(cell);
//
//        cell = new PdfPCell(new Phrase("Género", font_TH));
//        cell.setColspan(2);
//        cell.setFixedHeight(height_cell);
//        cell.setBackgroundColor(bg_TH);
//        tabla.addCell(cell);
//
//        cell = new PdfPCell(new Phrase(gradoDTO.getCursoColegio().getCurso().getNombre() + " " + gradoDTO.getNombre(), font_normal));
//        cell.setColspan(3);
//        cell.setFixedHeight(height_cell);
//        cell.setBackgroundColor(bg_TD);
//        tabla.addCell(cell);
//
//        cell = new PdfPCell(new Phrase(estudiante.getGeneroActual(), font_normal));
//        cell.setColspan(2);
//        cell.setFixedHeight(height_cell);
//        cell.setBackgroundColor(bg_TD);
//        tabla.addCell(cell);
//
//        cell = new PdfPCell(new Phrase("Núm. de Documento", font_TH));
//        cell.setColspan(2);
//        cell.setFixedHeight(height_cell);
//        cell.setBackgroundColor(bg_TH);
//        tabla.addCell(cell);
//
//        cell = new PdfPCell(new Phrase("Descripción", font_TH));
//        cell.setColspan(3);
//        cell.setFixedHeight(height_cell);
//        cell.setBackgroundColor(bg_TH);
//        tabla.addCell(cell);
//
//        cell = new PdfPCell(new Phrase(estudiante.getNumDoc(), font_normal));
//        cell.setColspan(2);
//        cell.setFixedHeight(height_cell);
//        cell.setBackgroundColor(bg_TD);
//        tabla.addCell(cell);
//
//        cell = new PdfPCell(new Phrase(descrpcion, font_normal));
//        cell.setColspan(3);
//        cell.setFixedHeight(height_cell);
//        cell.setBackgroundColor(bg_TD);
//        tabla.addCell(cell);
//
//        return tabla;
//    }
}
