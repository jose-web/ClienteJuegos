<%@page import="daw.clientejuegosModelo.UsuarioVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="daw.clientejuegosModelo.JuegoVO"%>
<%@page import="daw.clientejuegosModelo.JuegoDAO"%>
<%@page import="daw.clientejuegosModelo.UsuarioDAO"%>
<%@page import="java.time.LocalDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>

        <%
            //LocalDate fecha = LocalDate.of(1988, 04, 13);
            // UsuarioDAO.insertar_usuario("jose", "1234", fecha, 2.36, "joselito");

            //JuegoDAO.insertar_usuario("Gris", "w", "rol", "Seguir una historia", 6, 45.99);
            out.print(UsuarioDAO.login_Usuario("jose", "1234"));
        %>
    </body>
</html>
