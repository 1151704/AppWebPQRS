/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dto.DepartamentoDto;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocio.Negocio;

/**
 *
 * @author OMAR
 */
public class PruebasController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        Negocio service = new Negocio();
        
        DepartamentoDto d = new DepartamentoDto();
        d.setId(2);
        d.setCodigo("54");
        d.setNombre("Nawdwdr--");
        
        System.out.println(d);
        d = service.serviceDepartamento().guardar(d);
        System.out.println(d);

    }

}
