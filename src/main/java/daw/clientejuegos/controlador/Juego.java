/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.clientejuegos.controlador;

import daw.clientejuegos.modelo.BibliotecaDAO;
import daw.clientejuegos.modelo.UsuarioDAO;
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
 * @author Rosa
 */
public class Juego extends HttpServlet {

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

        try {
            HttpSession sesion = request.getSession(true);
            String adquirirJuego = request.getParameter("adquirirJuego");
            RequestDispatcher despachador = contexto.getRequestDispatcher("/index.jsp");
            if ("adquirirJuego".equals(adquirirJuego)) {

                UsuarioVO usuario = (UsuarioVO) sesion.getAttribute("usuario");
                UsuarioVO usuarioLogin = UsuarioDAO.busca_usuario_nickcame(usuario.getNickname());

                if (usuario.getPass().equals(usuarioLogin.getPass())) {
                    int juego = Integer.parseInt(sesion.getAttribute("idJuego").toString());
                    BibliotecaDAO.comprar_juego(usuario.getId_usuario(), juego);

                    UsuarioVO usuarioActualizado = UsuarioDAO.busca_usuario_nickcame(usuario.getNickname());
                    sesion.setAttribute("usuario", usuarioActualizado);
                }

            } else {
                String idJuego = request.getParameter("juego");
                if (idJuego != null) {
                    sesion.setAttribute("idJuego", idJuego);
                    despachador = contexto.getRequestDispatcher("/juego.jsp");
                } else {
                    despachador = contexto.getRequestDispatcher("/index.jsp");
                }
            }
            despachador.forward(request, response);
        } finally {
            out.close();
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
