package servlet;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dto.FuncionarioDto;
import dto.Mensaje;
import dto.MotivoSolicitudDto;
import dto.SolicitudDto;
import dto.TipoMensaje;
import dto.UsuarioDto;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.postgres.Service;
import util.EventsPDF;
import util.Utilidades;

public class ReportePqrs extends HttpServlet {

    private HttpSession session;
    private OutputStream outStream;

    private final static BaseColor bg_TH = new BaseColor(253, 88, 88);
    private final static BaseColor bg_TD = new BaseColor(240, 240, 240);

    private static final BaseColor color_TEXT = new BaseColor(0, 0, 0);

    private static final Font font_normal = new Font(Font.FontFamily.HELVETICA, 8, Font.NORMAL, color_TEXT);
    private static final Font font_TH = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL, color_TEXT);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        Date fechaInicial = Utilidades.validateInputDate(request.getParameter("fecha_inicial"));
        Date fechaFinal = Utilidades.validateInputDate(request.getParameter("fecha_final"));
        Integer estado = Utilidades.validateInputNumber(request.getParameter("estado"));
        Integer idFuncionario = Utilidades.validateInputNumber(request.getParameter("id_funcionario"));

        if (fechaInicial == null || fechaFinal == null) {
            session.setAttribute("mensaje", new Mensaje("Datos incompletos", "Debe ingresar todos los campos con *.", TipoMensaje.ERROR));
            response.sendRedirect(request.getContextPath() + "/main/generar_reporte.jsp");
            return;
        }

        String titulo = "REPORTE SOLICITUDES PQRS";

        final String CONTEXTO = request.getServletContext().getRealPath("/");
        session = request.getSession();
        Service controlador = (Service) session.getAttribute("controlador");
        String imagen = CONTEXTO + "static/images/fondo_universidad.jpeg";

        if (session.getAttribute("usuarioActual") != null) {

            FuncionarioDto funcionario = (FuncionarioDto) session.getAttribute("usuarioActual");

            List<SolicitudDto> solicitudes;

            if (idFuncionario != null && idFuncionario == 0) {
                idFuncionario = null; // todos los funcionarios
            }

            if (!funcionario.getEsAdministrador()) {
                idFuncionario = funcionario.getId();
            }

            // Filtrado de solicitudes
            if (estado == null || estado != 0) {
                if (idFuncionario == null) {
                    if (estado == 1) {
                        solicitudes = controlador.serviceSolicitud().buscarPorRangoFechaEstado(fechaInicial, fechaFinal, true);
                    } else {
                        solicitudes = controlador.serviceSolicitud().buscarPorRangoFechaEstado(fechaInicial, fechaFinal, false);
                    }
                } else {
                    if (estado == 1) {
                        solicitudes = controlador.serviceSolicitud().buscarFuncionarioPorRangoFechaEstado(idFuncionario, fechaInicial, fechaFinal, true);
                    } else {
                        solicitudes = controlador.serviceSolicitud().buscarFuncionarioPorRangoFechaEstado(idFuncionario, fechaInicial, fechaFinal, false);
                    }
                }
            } else {
                if (idFuncionario != null) {
                    solicitudes = controlador.serviceSolicitud().buscarFuncionarioPorRangoFecha(idFuncionario, fechaInicial, fechaFinal);
                } else {
                    solicitudes = controlador.serviceSolicitud().buscarPorRangoFecha(fechaInicial, fechaFinal);
                }
            }

            try {
                response.setContentType("application/pdf");
                Rectangle r = new Rectangle(PageSize.LEGAL.rotate());
                outStream = response.getOutputStream();

                Document documento = new Document(r, 10, 10, 170, 57);

                PdfWriter writer = PdfWriter.getInstance(documento, outStream);

                Date fechaActual = new Date();
                String fecha;
                SimpleDateFormat formtFechaBd = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");

                fecha = formtFechaBd.format(fechaActual);
//                response.addHeader("Content-disposition", "attachment; filename=" + titulo + "_" + fecha + ".pdf");

                writer.setPageEvent(new EventsPDF(titulo, "PQRS UFPS", imagen, 75));
                documento.open();
                documento.addTitle(titulo);

                PdfPTable tabla = new PdfPTable(9);
                PdfPCell cell;

                tabla.setWidths(new int[]{3, 15, 15, 10, 10, 10, 8, 8, 8});
                tabla.setWidthPercentage(100);
                tabla.setHeaderRows(1);

                cell = new PdfPCell(new Phrase("#", font_TH));
                cell.setBackgroundColor(bg_TH);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tabla.addCell(cell);

                cell = new PdfPCell(new Phrase("Identificación", font_TH));
                cell.setBackgroundColor(bg_TH);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tabla.addCell(cell);

                cell = new PdfPCell(new Phrase("Usuario", font_TH));
                cell.setBackgroundColor(bg_TH);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tabla.addCell(cell);

                cell = new PdfPCell(new Phrase("Tipo", font_TH));
                cell.setBackgroundColor(bg_TH);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tabla.addCell(cell);

                cell = new PdfPCell(new Phrase("Motivo", font_TH));
                cell.setBackgroundColor(bg_TH);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tabla.addCell(cell);

                cell = new PdfPCell(new Phrase("Funcionario", font_TH));
                cell.setBackgroundColor(bg_TH);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tabla.addCell(cell);

                cell = new PdfPCell(new Phrase("Fecha registro", font_TH));
                cell.setBackgroundColor(bg_TH);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tabla.addCell(cell);

                cell = new PdfPCell(new Phrase("Fecha respuesta", font_TH));
                cell.setBackgroundColor(bg_TH);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tabla.addCell(cell);

                cell = new PdfPCell(new Phrase("Respuesta", font_TH));
                cell.setBackgroundColor(bg_TH);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                tabla.addCell(cell);

                int cont = 1;
                for (SolicitudDto solicitud : solicitudes) {
                    UsuarioDto usuario = solicitud.getUsuario();
                    MotivoSolicitudDto motivo = solicitud.getMotivo();

                    cell = new PdfPCell(new Phrase(String.valueOf(cont++), font_normal));
                    cell.setBackgroundColor(bg_TD);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    tabla.addCell(cell);

                    cell = new PdfPCell(new Phrase(usuario != null ? usuario.getNombreCompleto() : "", font_normal));
                    cell.setBackgroundColor(bg_TD);
                    tabla.addCell(cell);

                    cell = new PdfPCell(new Phrase(usuario != null ? usuario.getTipoIdentificacion().getAbreviatura() + " " + usuario.getIdentificacion() : "", font_normal));
                    cell.setBackgroundColor(bg_TD);
                    tabla.addCell(cell);

                    cell = new PdfPCell(new Phrase(motivo.getTipo().getDescripcion(), font_normal));
                    cell.setBackgroundColor(bg_TD);
                    tabla.addCell(cell);

                    cell = new PdfPCell(new Phrase(motivo.getDescripcion(), font_normal));
                    cell.setBackgroundColor(bg_TD);
                    tabla.addCell(cell);

                    cell = new PdfPCell(new Phrase(solicitud.getFuncionario().getNombreCompleto(), font_normal));
                    cell.setBackgroundColor(bg_TD);
                    tabla.addCell(cell);

                    cell = new PdfPCell(new Phrase(Utilidades.formatDate(solicitud.getFechaRegistro(), "yyyy-MM-dd HH:mm"), font_normal));
                    cell.setBackgroundColor(bg_TD);
                    tabla.addCell(cell);

                    cell = new PdfPCell(new Phrase(solicitud.getFechaRespuesta() != null ? Utilidades.formatDate(solicitud.getFechaRespuesta(), "yyyy-MM-dd HH:mm") : "", font_normal));
                    cell.setBackgroundColor(bg_TD);
                    tabla.addCell(cell);

                    cell = new PdfPCell(new Phrase(solicitud.getRespondida() ? "SI" : "NO", font_normal));
                    cell.setBackgroundColor(bg_TD);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    tabla.addCell(cell);
                }
                if (solicitudes.isEmpty()) {

                    cell = new PdfPCell(new Phrase("Sin resultados", font_TH));
                    cell.setBackgroundColor(bg_TD);
                    cell.setMinimumHeight(20);
                    cell.setColspan(9);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    tabla.addCell(cell);

                }

                documento.add(tabla);

                documento.close();
            } catch (DocumentException ex) {
                ex.printStackTrace();
            }

        } else {
            session.setAttribute("mensaje", new Mensaje("Error", "Error al tratar de generar el reporte.", TipoMensaje.ERROR));
            response.sendRedirect(request.getContextPath() + "/main/generar_reportes.jsp");
        }
    }

}
