package servlet;

import dto.FuncionarioDto;
import dto.Mensaje;
import dto.TipoMensaje;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.postgres.Service;
import util.Utilidades;

public class FuncionarioActualizar extends HttpServlet {

    private HttpSession session;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            session = req.getSession();
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");

            Integer id = Utilidades.validateInputNumber(req.getParameter("id"));
            String nombreCompleto = Utilidades.validateInputText(req.getParameter("nombre_completo"));
            String correo = Utilidades.validateInputText(req.getParameter("correo"));
            String cargo = Utilidades.validateInputText(req.getParameter("cargo"));
            String celular = Utilidades.validateInputText(req.getParameter("celular"));
            Integer codigoInterno = Utilidades.validateInputNumber(req.getParameter("codigo_interno"));
            Boolean es_administrador = Utilidades.validateInputBoolean(req.getParameter("es_administrador"));

            if (nombreCompleto == null || correo == null || id == null) {
                resp.sendRedirect(req.getContextPath() + "/main/funcionarios.jsp");
                session.setAttribute("mensaje", new Mensaje("Datos incompletos", "Debe ingresar todos los campos con *.", TipoMensaje.ERROR));
                return;
            }
            Service controlador = (Service) session.getAttribute("controlador");

            FuncionarioDto funcionario = controlador.serviceFuncionario().buscarPorId(id);

            if (funcionario == null) {
                session.setAttribute("mensaje", new Mensaje("Datos errados", "El funcionario no existe.", TipoMensaje.WARNING));
                resp.sendRedirect(req.getContextPath() + "/main/funcionarios.jsp");
                return;
            }
            funcionario.setCodigoInterno(codigoInterno);
            funcionario.setCargo(cargo);
            funcionario.setCelular(celular);
            funcionario.setCorreo(correo);
            funcionario.setEsAdministrador(es_administrador);
            funcionario.setFechaModificacion(new Date());
            funcionario.setNombreCompleto(nombreCompleto);

            controlador.serviceFuncionario().guardar(funcionario);
            session.setAttribute("mensaje", new Mensaje("Formulario registrado", "Se ha registrado exitosamente el funcionario.", TipoMensaje.SUCCESS));
            resp.sendRedirect(req.getContextPath() + "/main/actualizar_funcionario.jsp?id=" + id);

            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
        session.setAttribute("mensaje", new Mensaje("Error", "Error al tratar de guardar el formulario.", TipoMensaje.ERROR));
        resp.sendRedirect(req.getContextPath() + "/main/funcionarios.jsp");

    }

}
