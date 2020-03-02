<%-- 
    Document   : juego
    Created on : 28 feb. 2020, 20:08:48
    Author     : Rosa
--%>

<%@page import="daw.clientejuegos.modelo.UsuarioVO"%>
<%@page import="daw.clientejuegos.modelo.BibliotecaDAO"%>
<%@page import="daw.clientejuegos.modelo.JuegoVO"%>
<%@page import="daw.clientejuegos.modelo.JuegoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Juego</title>
        <script src="https://kit.fontawesome.com/0d44a327d6.js" crossorigin="anonymous"></script>
        <style>
            body{
                background: rgb(0,0,0);
                background: -moz-linear-gradient(146deg, rgba(0,0,0,1) 0%, rgba(14,0,80,1) 50%, rgba(57,9,121,1) 100%);
                background: -webkit-linear-gradient(146deg, rgba(0,0,0,1) 0%, rgba(14,0,80,1) 50%, rgba(57,9,121,1) 100%);
                background: linear-gradient(146deg, rgba(0,0,0,1) 0%, rgba(14,0,80,1) 50%, rgba(57,9,121,1) 100%);
                filter: progid:DXImageTransform.Microsoft.gradient(startColorstr="#000000",endColorstr="#390979",GradientType=1);
                color:white;
            }
            img{
                width:25%;
            }
            a{
                cursor:pointer;
                text-decoration: none;
                color:purple;

            }

        </style>
    </head>
    <body>
        <%
            String sesione = session.getAttribute("idJuego").toString();
            int idJuego = Integer.parseInt(sesione);
            JuegoVO juego = JuegoDAO.buscar_juego(idJuego);

            // out.print("<img src='img/"+juego.getImg()+"'/>");
            out.print("<h1>" + juego.getNombre_juego() + "</h1>");
            out.print("<img src='img/" + juego.getImg() + "'>");
            out.print("<p>Descripción: " + juego.getDescripcion() + "</p>");
            char sis = juego.getSistema_operativo().charAt(0);

            //out.print(sis);
            switch (sis) {
                case 'w':
                    out.print("<i class='fab fa-windows'></i>");
                    break;
                case 'l':
                    out.print("<i class='fab fa-linux'></i>");
                    break;
                case 'm':
                    out.print("<i class='fab fa-apple'></i>");
                    break;
            }
            out.print("<p>Precio: " + juego.getPrecio() + "</p>");
            out.print("<p>Pegi: " + juego.getPegi() + "</p>");

            HttpSession sesion = request.getSession(true);
            UsuarioVO usuario = (UsuarioVO) sesion.getAttribute("usuario");
            if (BibliotecaDAO.usuario_tiene_juego(usuario.getId_usuario(), idJuego)) {
                out.print("Ya tienes este juego");
            } else {
        %>   
        <form method="post" action="./Juego">
            <button name="adquirirJuego" value="adquirirJuego">Adquirir Juego</button>
        </form>
        <%
            }
        %>
        <p><a href='index.jsp'>Volver</a><p>
    </body>
</html>
