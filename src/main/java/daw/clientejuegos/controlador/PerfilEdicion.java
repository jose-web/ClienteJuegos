/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw.clientejuegos.controlador;

import daw.clientejuegos.modelo.UsuarioDAO;
import daw.clientejuegos.modelo.UsuarioVO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
public class PerfilEdicion extends HttpServlet {

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

        RequestDispatcher despachador = contexto.getRequestDispatcher("/index.jsp");

        try {
            HttpSession sesion = request.getSession(true);
            String editar_perfil = request.getParameter("editar_perfil");
            if ("editar_perfil".equals(editar_perfil)) {
                despachador = contexto.getRequestDispatcher("/editarPerfil.jsp");
            }
            String subir_cambios = request.getParameter("subir_cambios");
            if (subir_cambios != null) {
                String nombre_edit = request.getParameter("nombre");
                String pass_edit = request.getParameter("clave");
                String fecha_edit = request.getParameter("fecha");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                LocalDate fechaNueva = LocalDate.parse(fecha_edit, formatter);

                String nick_edit = request.getParameter("nick");
                UsuarioVO usu = (UsuarioVO) sesion.getAttribute("usuario");
                int id_usu = usu.getId_usuario();
                /*
            sesion.setAttribute("id_usuario", id_usu);
            sesion.setAttribute("nom_usuario", nombre_edit);
            sesion.setAttribute("pass_usuario", pass_edit);
            sesion.setAttribute("fecha_usuario", fechaNueva);
            sesion.setAttribute("nick_usuario", nick_edit);*/
                boolean error = "".equals(nombre_edit) || "".equals(fecha_edit) || "".equals(nick_edit);
                despachador = contexto.getRequestDispatcher("/editarPerfil.jsp");
                if (!error) {
                    int resp = UsuarioDAO.updateUsuario(nombre_edit, pass_edit, fechaNueva, nick_edit, id_usu);

                    sesion.setAttribute("error_edicion", resp);
                    despachador = contexto.getRequestDispatcher("/editarPerfil.jsp");
                    UsuarioVO usuarioLogin = UsuarioDAO.busca_usuario_nickcame(nick_edit);
                    sesion.setAttribute("usuario", usuarioLogin);

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
