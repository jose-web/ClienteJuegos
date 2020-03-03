<%-- 
    Document   : biblioteca
    Created on : 2 mar. 2020, 13:25:43
    Author     : jose
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="daw.clientejuegos.modelo.JuegoVO"%>
<%@page import="daw.clientejuegos.modelo.UsuarioVO"%>
<%@page import="daw.clientejuegos.modelo.BibliotecaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            HttpSession sesion = request.getSession(true);
            // int usuario = Integer.parseInt(sesion.getAttribute("idUsuario").toString());

            UsuarioVO usuario = (UsuarioVO) sesion.getAttribute("usuario");

            if (usuario != null) {
                try {
                    ArrayList<JuegoVO> juegos = BibliotecaDAO.verBiblioteca(usuario.getId_usuario());
                    for (JuegoVO juego : juegos) {
                        out.print("<form method='post' action='./Juego' class='hijo'>");
                        out.print("<button class='boton' name='juego' value='" + juego.getId_juego() + "'>");
                        out.print("<p class='nombre'>" + juego.getNombre_juego() + "</p>");
                        out.print("<p class='precio'>" + juego.getPrecio() + "</p>");
                        out.print("</button>");
                        out.print("</form>");
                    }
                } catch (java.lang.NullPointerException n) {
                    out.print("<p>No se ha podido conectar con la base de datos</p>");
                }
            } else {
                out.print("Debes iniciar sesión para ver tu biblioteca");
            }
        %>
        <p><a href='index.jsp'>Volver</a></p>
    </body>
</html>
