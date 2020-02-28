/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.clientejuegos.controlador;

import daw.clientejuegos.modelo.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jose
 */
public class Registro extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Registro</title>");
            out.println("</head>");
            out.println("<body>");
            String nombre = request.getParameter("nombre");
            String pass = request.getParameter("pass");
            String nickname = request.getParameter("nickname");
            String fecnac = request.getParameter("fecnac");
            int edad = 0;

            try {

                Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(fecnac);

                edad = calculaEdad(date1);

            } catch (java.text.ParseException p) {

            }

            if (edad < 18) {
                out.println("usuario no insertado porque es menor de edad");

            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                //convert String to LocalDate
                LocalDate fechaNueva = LocalDate.parse(fecnac, formatter);

                UsuarioDAO.insertar_usuario(nombre, pass, fechaNueva, 0, nickname);
                out.println("usuario insertado");
            }

            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    public static int calculaEdad(Date bornDate) {
        Calendar cal = Calendar.getInstance(); // current date
        int currYear = cal.get(Calendar.YEAR);
        int currMonth = cal.get(Calendar.MONTH);
        int currDay = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(bornDate); // now born date
        int years = currYear - cal.get(Calendar.YEAR);
        int bornMonth = cal.get(Calendar.MONTH);
        if (bornMonth == currMonth) { // same month
            return cal.get(Calendar.DAY_OF_MONTH) <= currDay ? years
                    : years - 1;
        } else {
            return bornMonth < currMonth ? years - 1 : years;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
