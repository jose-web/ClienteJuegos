/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.clientejuegos.controlador;

import daw.clientejuegos.modelo.UsuarioDAO;
import daw.clientejuegos.modelo.UsuarioVO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jose
 */
public class ControlUsuario extends HttpServlet {

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

        if ("registrarse".equals(request.getParameter("registrarse"))) {
            RequestDispatcher rd = request.getRequestDispatcher("./registro.jsp");
            rd.forward(request, response);

        } else if ("entrar".equals(request.getParameter("entrar"))) {
            HttpSession sesion = request.getSession(true);
            sesion.setAttribute("error", -1);
            try {
                int login = UsuarioDAO.login_Usuario(request.getParameter("nicknameLogin"), request.getParameter("passLogin"));
                switch (login) {
                    case -1:
                        sesion.setAttribute("error", 0);
                        break;
                    case 0:
                        sesion.setAttribute("error", 1);
                        break;
                    case 1:
                        UsuarioVO usuarioLogin = UsuarioDAO.busca_usuario_nickcame(request.getParameter("nicknameLogin"));

                        if (usuarioLogin.getPass() != null && usuarioLogin.getPass().equals(UsuarioDAO.toMd5(request.getParameter("passLogin")))) {
                            sesion.setAttribute("usuario", usuarioLogin);
                        }
                        break;
                }
            } catch (Exception n) {
                sesion.setAttribute("error", 0);
            }
        } else if ("cerrarSesion".equals(request.getParameter("cerrarSesion"))) {
            HttpSession sesion = request.getSession(true);
            sesion.setAttribute("usuario", null);
        } else if ("saldo".equals(request.getParameter("saldo"))) {
            ServletContext contexto = request.getServletContext();
            RequestDispatcher despachador = contexto.getRequestDispatcher("/addSaldo.jsp");
            despachador.forward(request, response);
        } else if ("subirSaldo".equals(request.getParameter("subirSaldo"))) {
            HttpSession sesion = request.getSession(true);
            UsuarioVO usuario = (UsuarioVO) sesion.getAttribute("usuario");
            int id_usuario = usuario.getId_usuario();
            double saldo_add = Double.parseDouble(request.getParameter("saldoNuevo"));
            UsuarioDAO.addSaldo(saldo_add, id_usuario);

            UsuarioVO actualizaUsuario = UsuarioDAO.busca_usuario_nickcame(usuario.getNickname());
            sesion.setAttribute("usuario", actualizaUsuario);

        }

        ServletContext contexto = request.getServletContext();
        RequestDispatcher despachador = contexto.getRequestDispatcher("/index.jsp");
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
