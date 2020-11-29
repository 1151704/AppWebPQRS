package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.postgres.Service;

public class loginController extends HttpServlet {
    private HttpSession session;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        String identificacion = request.getParameter("identificacion");
        String password = request.getParameter("password");
        
        session = request.getSession();
        Service controlador = (Service) session.getAttribute("controlador");
        
        

    }

}
