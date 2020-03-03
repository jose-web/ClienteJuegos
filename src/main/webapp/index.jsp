<%@page import="daw.clientejuegos.modelo.UsuarioVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="daw.clientejuegos.modelo.JuegoVO"%>
<%@page import="daw.clientejuegos.modelo.JuegoDAO"%>
<%@page import="daw.clientejuegos.modelo.UsuarioDAO"%>
<%@page import="java.time.LocalDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cliente Juegos</title>
        <link rel="stylesheet" href="estilos.css"/>
    </head>
    <body>

    <header>
        <%
            HttpSession sesion = request.getSession(true);
            // int usuario = Integer.parseInt(sesion.getAttribute("idUsuario").toString());

            UsuarioVO usuario = (UsuarioVO) sesion.getAttribute("usuario");

            if (usuario != null) {
                out.print("<div> Hola " + usuario.getNombre() + "(" + usuario.getNickname() + ") Tienes un saldo de " + usuario.getSaldo() + "€</div>");
        %>
        <form action="./Login" method="post">
            <button name="cerrarSesion" value="cerrarSesion"> Cerrar sesión</button>
        </form>
        <form method="post" action="./biblioteca.jsp">
            <button>Ver biblioteca</button>
        </form>
        <form method="post" action="./Admin_juego_creado">
            <button>Mis creaciones</button>
        </form>
        <%
            out.print("<form method='post' action='./PerfilEdicion'><button name='editar_perfil' value='editar_perfil'>Editar Perfil</button></form>");
        } else {
            if (sesion.getAttribute("error") != null) {
                int error = Integer.parseInt(sesion.getAttribute("error").toString());
                if (error != -1) {
                    switch (error) {
                        case 0:
                            out.print("Ha ocurrido un error con la base de datos");
                            break;
                        case 1:
                            out.print("Este usuario no está en la base de datos");
                            break;
                    }
                    sesion.setAttribute("error", -1);
                }
            }
        %>
        <form action="./Login" method="post">
            <table>
                <tr>
                    <td><label for="nicknameLogin">Nickname:</label></td>
                    <td><input type="text" name="nicknameLogin" id="nicknameLogin" required></td>
                </tr>
                <tr>
                    <td><label for="passLogin">Contraseña</label></td>
                    <td><input type="password" name="passLogin" id="passLogin" required></td>
                </tr>
                <tr>
                    <td><button name="entrar" value="entrar" type="submit"> Entrar</button></td>
                    <td><button name="registrarse" value="registrarse" onclick="registro()"> Registrarse</button></td>
                </tr>
            </table>
        </form>
        <%
            }
        %>
    </header>
    <h1>Cliente Juegos</h1>
    <div id="contenido">
        <%
            try {
                ArrayList<JuegoVO> juegos = JuegoDAO.consultarJuegos();
                for (JuegoVO juego : juegos) {
                    out.print("<form method='post' action='./Juego' class='hijo'>");
                    out.print("<button class='boton' name='juego' value='" + juego.getId_juego() + "'>");
                    out.print("<div class='imagen'><img src='img/" + juego.getImg() + "'></div>");
                    out.print("<p class='nombre'>" + juego.getNombre_juego() + "</p>");
                    out.print("<p class='precio'>" + juego.getPrecio() + "</p>");
                    out.print("</button>");
                    out.print("</form>");
                }
            } catch (java.lang.NullPointerException n) {
                out.print("<p>No se ha podido conectar con la base de datos</p>");
            }

        %>
    </div>

    <script src="js/script.js"></script>
</body>
</html>
