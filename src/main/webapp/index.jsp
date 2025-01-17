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
        <link rel="stylesheet" href="css/estilosGenerales.css"/>
        <link rel="stylesheet" href="css/estilosIndex.css"/>
    </head>
    <body>

    <header>
        <h1>Cliente Juegos</h1>
        <%
            HttpSession sesion = request.getSession(true);
            // int usuario = Integer.parseInt(sesion.getAttribute("idUsuario").toString());

            UsuarioVO usuario = (UsuarioVO) sesion.getAttribute("usuario");

            if (usuario != null) {
                out.print("<div> Hola " + usuario.getNombre() + "(" + usuario.getNickname() + ") Tienes un saldo de " + usuario.getSaldo() + "€</div>");
        %>
        <form action="./ControlUsuario" method="post">
            <button class="botonHeader"  name="cerrarSesion" value="cerrarSesion"> Cerrar sesión</button>
        </form>
        <form method="post" action="./biblioteca.jsp">
            <button class="botonHeader">Ver biblioteca</button>
        </form>
        <form method="post" action="./Admin_juego_creado">
            <button class="botonHeader">Mis creaciones</button>
        </form>
        <form method="post" action="./ControlUsuario">
            <button  class="botonHeader" name="saldo" value="saldo">Añadir Saldo</button>
        </form>
        <%
            out.print("<form method='post' action='./PerfilEdicion'><button class='botonHeader' name='editar_perfil' value='editar_perfil'>Editar Perfil</button></form>");
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
        <button class="botonHeader" onclick="muestraLogin()">Login</button>
        <form method="post" action="./ControlUsuario">
            <button class="botonHeader" name="registrarse" value="registrarse"> Registrarse</button>
        </form>
        <div id="popup" class="oculto">
            <div id="fondoPopUp" onclick="salirPopUp()"></div>
        <form action="./ControlUsuario" method="post" id="login">
            <table>
                <tr>
                    <td><label for="nicknameLogin">Nickname:</label></td>
                    <td><input type="text" name="nicknameLogin" id="nicknameLogin" required></td>
                </tr>
                <tr>
                    <td><label for="passLogin">Contraseña:</label></td>
                    <td><input type="password" name="passLogin" id="passLogin" required></td>
                </tr>
                <tr>
                    <td><button name="entrar" value="entrar" type="submit"> Entrar</button></td>

                </tr>
            </table>
        </form>
        </div>
        <%
            }
        %>
    </header>
    <main>
        <%
            try {
                ArrayList<JuegoVO> juegos = JuegoDAO.consultarJuegos();
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

        %>
    </main>

    <script src="js/script.js"></script>
</body>
</html>
