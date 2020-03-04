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
        <link rel="stylesheet" href="css/estilosGenerales.css"/>
        <link rel="stylesheet" href="css/estilosJuego.css"/>
        <script src="https://kit.fontawesome.com/0d44a327d6.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <main>
            <div>
                <%
                    String sesione = session.getAttribute("idJuego").toString();
                    int idJuego = Integer.parseInt(sesione);
                    JuegoVO juego = JuegoDAO.buscar_juego(idJuego);

                    out.print("<h1 id='nombre' class='apartado'>" + juego.getNombre_juego() + "</h1>");
                    out.print("<div id='descripcion' class='apartado'><div class='titulo'>Descripción</div><div class='contenido'> " + juego.getDescripcion() + "</div></div>");
                    char sis = juego.getSistema_operativo().charAt(0);

                    //out.print(sis);
                    out.print("<div id='so' class='apartado'> <div class='titulo'>Sistema operativo</div><div class='contenido'><i class='fab ");
                    switch (sis) {
                        case 'w':
                            out.print("fa-windows '></i>");
                            break;
                        case 'l':
                            out.print("fa-linux'></i>");
                            break;
                        case 'm':
                            out.print("fa-apple'></i>");
                            break;
                    }
                    out.print("</div></div>");

                    out.print("<div id='precio' class='apartado'><div class='titulo'>Precio</div><div class='contenido'> " + juego.getPrecio() + "</div></div>");
                    out.print("<div id='pegi' class='apartado'><div class='titulo'>Pegi</div><div class='contenido'> " + juego.getPegi() + "</div></div>");

                    HttpSession sesion = request.getSession(true);
                    if (sesion.getAttribute("usuario") != null) {
                        UsuarioVO usuario = (UsuarioVO) sesion.getAttribute("usuario");
                        if (BibliotecaDAO.usuario_tiene_juego(usuario.getId_usuario(), idJuego)) {
                            out.print("<div id='comprar' class='apartado'>Ya tienes este juego</div>");
                        } else {
                %>   
                <form method="post" action="./Juego" id='comprar' class='apartado adquirir'>
                    <button name="adquirirJuego" value="adquirirJuego">Adquirir Juego</button>
                </form>
                <%
                        }
                    } else {
                        out.print("<div id='comprar' class='apartado'>Debes estar registrado para comprar este producto</div>");
                    }
                %>
                <a id='volver' href='index.jsp'>Volver</a>
            </div>
        </main>
    </body>
</html>
