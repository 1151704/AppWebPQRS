package servlet;

import dto.Mensaje;
import dto.MotivoSolicitudDto;
import dto.TipoMensaje;
import dto.TipoSolicitudDto;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.postgres.Service;
import util.Utilidades;

public class MotivoSolicitudRegistrar extends HttpServlet {

    private HttpSession session;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            session = req.getSession();
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");

            Integer idTipo = Utilidades.validateInputNumber(req.getParameter("id_tipo"));
            String descripcion = Utilidades.validateInputText(req.getParameter("descripcion"));
            if (idTipo == null || descripcion == null) {
                if (idTipo == null) {
                    session.setAttribute("mensaje", new Mensaje("Datos incompletos", "Debe seleccionar un tipo de solicitud.", TipoMensaje.ERROR));
                    resp.sendRedirect(req.getContextPath() + "/main/tipos_solicitudes.jsp");
                } else {
                    session.setAttribute("mensaje", new Mensaje("Datos incompletos", "Debe ingresar todos los campos con *.", TipoMensaje.ERROR));
                    resp.sendRedirect(req.getContextPath() + "/main/registrar_motivo_solicitud.jsp?id=" + idTipo);
                }
                return;
            }
            Service controlador = (Service) session.getAttribute("controlador");

            MotivoSolicitudDto tipoSolicitud = new MotivoSolicitudDto();

            tipoSolicitud.setFkTipoSolicitud(idTipo);
            tipoSolicitud.setDescripcion(descripcion);
            tipoSolicitud.setHabilitado(true);

            tipoSolicitud = controlador.serviceMotivoSolicitud().guardar(tipoSolicitud);
            if (tipoSolicitud != null) {
                session.setAttribute("mensaje", new Mensaje("Formulario registrado", "Se ha registrado exitosamente el tipo de solicitud.", TipoMensaje.SUCCESS));
                resp.sendRedirect(req.getContextPath() + "/main/actualizar_motivo_solicitud.jsp?id=" + tipoSolicitud.getId());
                return;
            } else {
                session.setAttribute("mensaje", new Mensaje("Error", "Error al tratar de guardar el formulario.", TipoMensaje.ERROR));
                resp.sendRedirect(req.getContextPath() + "/main/registrar_motivo_solicitud.jsp?id=" + tipoSolicitud.getId());
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        session.setAttribute("mensaje", new Mensaje("Error", "Error al tratar de guardar el formulario.", TipoMensaje.ERROR));
        resp.sendRedirect(req.getContextPath() + "/main/tipos_solicitudes.jsp");

    }

}
