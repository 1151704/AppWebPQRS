package servlet;

import dto.FuncionarioDto;
import dto.Mensaje;
import dto.TipoMensaje;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.postgres.Service;
import util.Encriptador;
import util.Utilidades;

public class LoginController extends HttpServlet {
    
    private HttpSession session;
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            session = req.getSession();
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");
            String username = Utilidades.validateInputText(req.getParameter("username"));
            String password = Utilidades.validateInputText(req.getParameter("password"));
            
            if (username == null || password == null) {
                session.setAttribute("mensaje", new Mensaje("Datos incompletos", "Debe ingresar todos los campos con *.", TipoMensaje.ERROR));
                resp.sendRedirect(req.getContextPath() + "/login.jsp");
                return;
            }
            Service controlador = (Service) session.getAttribute("controlador");
            
            FuncionarioDto funcionario = controlador.serviceFuncionario().verificarAccesoCuenta(username, Encriptador.encriptar(password));

            if (funcionario != null) {
                session.setAttribute("usuarioActual", funcionario);
                session.setAttribute("mensaje", new Mensaje("Acceso concedido", "Bienvenido " + funcionario.getNombreCompleto(), TipoMensaje.SUCCESS));
                resp.sendRedirect(req.getContextPath() + "/main/index.jsp");
                return;
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        session.setAttribute("mensaje", new Mensaje("Error", "Credenciales erradas, verifique la información ingresada.", TipoMensaje.ERROR));
        resp.sendRedirect(req.getContextPath() + "/login.jsp");
        
    }
    
}
