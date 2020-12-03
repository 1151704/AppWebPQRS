package servlet;

import dto.Mensaje;
import dto.TipoMensaje;
import dto.TipoSolicitudDto;
import dto.TipoUsuarioDto;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.postgres.Service;
import util.Utilidades;

public class TipoUsuarioRegistrar extends HttpServlet {

    private HttpSession session;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            session = req.getSession();
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");

            String descripcion = Utilidades.validateInputText(req.getParameter("descripcion"));
            if (descripcion == null) {
                session.setAttribute("mensaje", new Mensaje("Datos incompletos", "Debe ingresar todos los campos con *.", TipoMensaje.ERROR));
                resp.sendRedirect(req.getContextPath() + "/main/registrar_tipo_usuario.jsp");
                return;
            }
            Service controlador = (Service) session.getAttribute("controlador");

            TipoUsuarioDto tipoSolicitud = new TipoUsuarioDto();

            tipoSolicitud.setDescripcion(descripcion);
            tipoSolicitud.setHabilitado(true);

            tipoSolicitud = controlador.serviceTipoUsuario().guardar(tipoSolicitud);
            if (tipoSolicitud != null) {
                session.setAttribute("mensaje", new Mensaje("Formulario registrado", "Se ha registrado exitosamente el tipo de usuario.", TipoMensaje.SUCCESS));
                resp.sendRedirect(req.getContextPath() + "/main/actualizar_tipo_usuario.jsp?id=" + tipoSolicitud.getId());
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        session.setAttribute("mensaje", new Mensaje("Error", "Error al tratar de guardar el formulario.", TipoMensaje.ERROR));
        resp.sendRedirect(req.getContextPath() + "/main/tipos_usuarios.jsp");

    }

}
