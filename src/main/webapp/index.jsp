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
             LocalDate fecha = LocalDate.of(1988, 04, 13);
            UsuarioDAO.insertar_usuario("rosa", "1234", fecha, 2.36, "joselito");
            
            JuegoDAO.insertar_usuario("Pokemon", "w", "rol", "Capturar pokemon", 6, 45.99);
           %>
    </body>
</html>
