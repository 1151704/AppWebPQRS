package servlet;

import dto.FuncionarioDto;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.postgres.Service;
import util.Utilidades;

public class FuncionarioRegistrar extends HttpServlet {

    private HttpSession session;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");

            Integer idTipoIdentificacion = Utilidades.validateInputNumber(req.getParameter("tipo_id"));
            String nombreCompleto = Utilidades.validateInputText(req.getParameter("nombre_completo"));
            String identificacion = Utilidades.validateInputText(req.getParameter("identificacion"));
            String correo = Utilidades.validateInputText(req.getParameter("correo"));
            String cargo = Utilidades.validateInputText(req.getParameter("cargo"));
            String celular = Utilidades.validateInputText(req.getParameter("celular"));
            Integer codigoInterno = Utilidades.validateInputNumber(req.getParameter("codigo_interno"));
            Boolean es_administrador = Utilidades.validateInputBoolean(req.getParameter("es_administrador"));

            if (idTipoIdentificacion == null || nombreCompleto == null || identificacion == null
                    || correo == null) {
                resp.sendRedirect(req.getContextPath() + "/main/funcionarios.jsp");
                return;
            }
            session = req.getSession();
            Service controlador = (Service) session.getAttribute("controlador");

            FuncionarioDto funcionario = new FuncionarioDto();

            funcionario.setCargo(cargo);
            funcionario.setCelular(celular);
            funcionario.setCodigoInterno(codigoInterno);
            funcionario.setContrasena(identificacion);
            funcionario.setCorreo(correo);
            funcionario.setEsAdministrador(es_administrador);
            funcionario.setFechaRegistro(new Date());
            funcionario.setFkTipoIdentificacion(idTipoIdentificacion);
            funcionario.setIdentificacion(identificacion);
            funcionario.setNombreCompleto(nombreCompleto);

            funcionario = controlador.serviceFuncionario().guardar(funcionario);
            if (funcionario != null) {
                resp.sendRedirect(req.getContextPath() + "/main/actualizar_funcionario.jsp?id=" + funcionario.getId());
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/main/funcionarios.jsp");

    }

}
