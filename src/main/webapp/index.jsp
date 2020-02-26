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
    </head>
    <body>

        <form action="./Login" method="POST">
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

        <script src="js/script.js"></script>
    </body>
</html>
