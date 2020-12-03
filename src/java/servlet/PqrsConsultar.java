package servlet;

import dto.Mensaje;
import dto.SolicitudArchivosDto;
import dto.SolicitudDto;
import dto.TipoMensaje;
import dto.UsuarioDto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.postgres.Service;
import util.Utilidades;

public class PqrsConsultar extends HttpServlet {

    private HttpSession session;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            session = req.getSession();
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");

            Integer radicado = Utilidades.validateInputNumber(req.getParameter("radicado"));
            String identificacion = Utilidades.validateInputText(req.getParameter("identificacion"));

            if (radicado == null || identificacion == null) {
                session.setAttribute("mensaje", new Mensaje("Datos incompletos", "Debe ingresar todos los campos con *.", TipoMensaje.ERROR));
                resp.sendRedirect(req.getContextPath() + "/main/registrar_tipo_usuario.jsp");
                return;
            }
            Service controlador = (Service) session.getAttribute("controlador");

            SolicitudDto solicitud = controlador.serviceSolicitud().buscarPorId(radicado);

            if (solicitud == null) {
                session.setAttribute("mensaje", new Mensaje("Datos invalidos", "No existe ninguna solicitud con el número de radicado ingresado", TipoMensaje.ERROR));
                resp.sendRedirect(req.getContextPath() + "/consultar.jsp");
                return;
            }
            UsuarioDto usuario = solicitud.getUsuario();
            if (!usuario.getIdentificacion().equals(identificacion)) {
                session.setAttribute("mensaje", new Mensaje("Datos invalidos", "Inconsistencia de datos, ingrese la identificación del usuario que realizó la solicitud.", TipoMensaje.ERROR));
                resp.sendRedirect(req.getContextPath() + "/consultar.jsp");
                return;
            }
            List<SolicitudArchivosDto> archivosEnv = controlador.serviceSolicitudArchivos().listarPorSolicitudRespuesta(radicado, false);
            List<SolicitudArchivosDto> archivosRta = controlador.serviceSolicitudArchivos().listarPorSolicitudRespuesta(radicado, true);

            session.setAttribute("solicitud", solicitud);
            session.setAttribute("archivos_env", archivosEnv);
            session.setAttribute("archivos_rta", archivosRta);
            resp.sendRedirect(req.getContextPath() + "/detalle.jsp");
            return;

        } catch (IOException e) {
            e.printStackTrace();
        }
        session.setAttribute("mensaje", new Mensaje("Error", "Error al tratar de guardar el formulario.", TipoMensaje.ERROR));
        resp.sendRedirect(req.getContextPath() + "/main/tipos_usuarios.jsp");

    }

}
