/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.clientejuegos.controlador;

import daw.clientejuegos.modelo.JuegoDAO;
import daw.clientejuegos.modelo.UsuarioVO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jose
 */
public class Admin_juego_creado extends HttpServlet {

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

        ServletContext contexto = request.getServletContext();
        RequestDispatcher despachador = contexto.getRequestDispatcher("/mis_creaciones.jsp");

        if (request.getParameter("irAEditar") != null) {

            HttpSession sesion = request.getSession(true);

            sesion.setAttribute("irAEditar", request.getParameter("irAEditar"));

            RequestDispatcher rd = request.getRequestDispatcher("./editaJuego.jsp");
            rd.forward(request, response);
        } else if (request.getParameter("editar") != null) {
            HttpSession sesion = request.getSession(true);
            UsuarioVO usuario = (UsuarioVO) sesion.getAttribute("usuario");

            int idJuego = Integer.parseInt(request.getParameter("editar"));

            int pertenece = JuegoDAO.consultarPertenenciaJuegoCreado(usuario.getId_usuario(), idJuego);

            despachador = request.getRequestDispatcher("./editaJuego.jsp");

            switch (pertenece) {
                case 1:
                    int pegi = Integer.parseInt(request.getParameter("pegi"));
                    double precio = Double.parseDouble(request.getParameter("precio"));

                    JuegoDAO.editar_juego(
                            idJuego,
                            request.getParameter("nombre"),
                            request.getParameter("so"),
                            request.getParameter("tipo"),
                            request.getParameter("descripcion"),
                            pegi,
                            precio,
                            "imagen.jpg"
                    );
                    break;

                case 0:
                case -1:
                    despachador = request.getRequestDispatcher("./index.jsp");
                    break;

            }
        }

        despachador.forward(request, response);
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
