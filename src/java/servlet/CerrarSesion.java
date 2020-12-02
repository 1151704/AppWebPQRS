package servlet;

import dto.Mensaje;
import dto.TipoMensaje;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CerrarSesion extends HttpServlet {

    private HttpSession session;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        session = req.getSession();
        session.invalidate();        
        session = req.getSession();        
        session.setAttribute("mensaje", new Mensaje("Error", "Error al tratar de guardar el formulario.", TipoMensaje.ERROR));
        resp.sendRedirect(req.getContextPath() + "/main/funcionarios.jsp");

    }

}
