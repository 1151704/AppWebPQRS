package servlet;

import dto.Mensaje;
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

public class ActualizarTipoSolicitud extends HttpServlet {

    private HttpSession session;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            session = req.getSession();
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");

            Integer id = Utilidades.validateInputNumber(req.getParameter("id"));
            String descripcion = Utilidades.validateInputText(req.getParameter("descripcion"));
            Boolean habilitado = Utilidades.validateInputBoolean(req.getParameter("habilitado"));

            if (id == null || descripcion == null) {
                session.setAttribute("mensaje", new Mensaje("Datos incompletos", "Debe ingresar todos los campos con *.", TipoMensaje.ERROR));
                resp.sendRedirect(req.getContextPath() + "/main/actualizar_tipo_solicitud.jsp?id=" + id);
                return;
            }
            Service controlador = (Service) session.getAttribute("controlador");

            TipoSolicitudDto tipoSolicitud = controlador.serviceTipoSolicitud().buscarPorId(id);

            if (tipoSolicitud == null) {
                session.setAttribute("mensaje", new Mensaje("Datos errados", "El tipo de solicitud no existe.", TipoMensaje.WARNING));
                resp.sendRedirect(req.getContextPath() + "/main/tipos_solicitudes.jsp");
                return;

            }
            tipoSolicitud.setDescripcion(descripcion);
            tipoSolicitud.setHabilitado(habilitado);
            
            tipoSolicitud = controlador.serviceTipoSolicitud().guardar(tipoSolicitud);
            if (tipoSolicitud != null) {
                session.setAttribute("mensaje", new Mensaje("Formulario registrado", "Se ha registrado exitosamente el tipo de solicitud.", TipoMensaje.SUCCESS));
                resp.sendRedirect(req.getContextPath() + "/main/actualizar_tipo_solicitud.jsp?id=" + tipoSolicitud.getId());
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        session.setAttribute("mensaje", new Mensaje("Error", "Error al tratar de guardar el formulario.", TipoMensaje.ERROR));
        resp.sendRedirect(req.getContextPath() + "/main/tipos_solicitudes.jsp");

    }

}
