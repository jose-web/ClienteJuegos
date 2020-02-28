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
    </header>
             <h1>Cliente Juegos</h1>
            <div id="contenido">
                <%
                    ArrayList<JuegoVO> juegos=JuegoDAO.consultarJuegos();
                    for (JuegoVO juego : juegos) {
                            out.print("<form method='post' action='juego.jsp' class='hijo'>");
                            out.print("<button class='boton' name='juego' value='"+juego.getId_juego()+"'>");
                            out.print("<div class='imagen'><img src='img/"+juego.getImg()+"'></div>");
                            out.print("<p class='nombre'>"+juego.getNombre_juego()+"</p>");
                            out.print("<p class='precio'>"+juego.getPrecio()+"</p>");
                            out.print("</button>");
                            out.print("</form>");
                        }

                %>
                
            </div>
        
        <script src="js/script.js"></script>

        <%
            //LocalDate fecha = LocalDate.of(1988, 04, 13);
           // UsuarioDAO.insertar_usuario("Rosa", "1234", fecha, 2.36, "Mirusa");

            //JuegoDAO.insertar_usuario("Gris", "w", "rol", "Seguir una historia", 6, 45.99);

            //out.print(UsuarioDAO.login_Usuario("jose", "1234"));
            //out.print(JuegoDAO.delete_juego(5));

           // out.print(UsuarioDAO.login_Usuario("jose", "1234"));
            
            //ArrayList<JuegoVO> lista=JuegoDAO.consultarJuegosFiltro("precio","34.20");
           // for (JuegoVO juegoVO : lista) {
              //      out.print(juegoVO+"<br/>");
               // }
            
               // out.print(JuegoDAO.delete_juego(4));
              // out.print(JuegoDAO.buscar_juego(9));
        %>

    </body>
</html>
