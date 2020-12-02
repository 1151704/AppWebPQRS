package servlet;

import dto.Mensaje;
import dto.SolicitudDto;
import dto.TipoMensaje;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import service.postgres.Service;
import util.Utilidades;

public class RegistrarRespuestaPqrs extends HttpServlet {

    private HttpSession session;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean isMultipartContent = ServletFileUpload.isMultipartContent(req);
        if (!isMultipartContent) {
            return;
        }

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {

            session = req.getSession();
            List<FileItem> fields = upload.parseRequest(req);
            Iterator<FileItem> it = fields.iterator();

            Integer idSolicitud = null;
            String respuesta = null;
            InputStream archivo = null;

            while (it.hasNext()) {
                FileItem fileItem = it.next();
                boolean isFormField = fileItem.isFormField();
                if (isFormField) {
                    if (fileItem.getFieldName().equals("id_pqrs")) {
                        idSolicitud = Utilidades.validateInputNumber(fileItem.getString());
                    } else if (fileItem.getFieldName().equals("respuesta")) {
                        respuesta = Utilidades.validateInputText(fileItem.getString());
                    }
                } else {
                    archivo = fileItem.getInputStream();
                }
            }

            if (idSolicitud == null || respuesta == null) {
                session.setAttribute("mensaje", new Mensaje("Datos incompletos", "Debe ingresar todos los campos con *.", TipoMensaje.ERROR));
                resp.sendRedirect(req.getContextPath() + "/main/detalle_pqrs.jsp?id=" + idSolicitud);
                return;
            }
            Service controlador = (Service) session.getAttribute("controlador");

            SolicitudDto solicitud = controlador.serviceSolicitud().buscarPorId(idSolicitud);

            if (solicitud == null) {
                session.setAttribute("mensaje", new Mensaje("Datos errados", "La solicitud no existe.", TipoMensaje.WARNING));
                resp.sendRedirect(req.getContextPath() + "/main/pqrs.jsp");
                return;
            }

            solicitud.setRespuesta(respuesta);
            solicitud.setRespondida(true);
            solicitud.setFechaRespuesta(new Date());

            controlador.serviceSolicitud().guardar(solicitud);
            session.setAttribute("mensaje", new Mensaje("Formulario registrado", "Se ha registrado exitosamente la respuesta.", TipoMensaje.SUCCESS));
            resp.sendRedirect(req.getContextPath() + "/main/detalle_pqrs.jsp?id=" + idSolicitud);

        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        session.setAttribute("mensaje", new Mensaje("Error", "Error al tratar de guardar el formulario.", TipoMensaje.ERROR));
        resp.sendRedirect(req.getContextPath() + "/main/pqrs.jsp");

    }

}
