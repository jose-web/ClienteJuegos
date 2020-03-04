<%-- 
    Document   : mis_creaciones
    Created on : 3 mar. 2020, 8:48:05
    Author     : jose
--%>

<%@page import="daw.clientejuegos.modelo.UsuarioVO"%>
<%@page import="daw.clientejuegos.modelo.JuegoDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="daw.clientejuegos.modelo.JuegoVO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mis creaciones</title>
        <link rel="stylesheet" href="css/estilosGenerales.css"/>
        <link rel="stylesheet" href="css/estilosMisCreaciones.css"/>
    </head>
    <body>
        <main>
            <h1>Mis creaciones</h1>
            <form method="post" action="./Admin_juego_creado" id="subirJuego">
                <button name="subir">Subir un juego</button>
            </form>
            <%
                HttpSession sesion = request.getSession(true);

                UsuarioVO usuario = (UsuarioVO) sesion.getAttribute("usuario");
                try {
                    ArrayList<JuegoVO> juegos = JuegoDAO.consultarJuegosCreados(usuario.getId_usuario());
                    for (JuegoVO juego : juegos) {

            %>

            <div class="juego">
                <p class='nombre'> <%=juego.getNombre_juego()%></p>
                <p class='precio'><%=juego.getPrecio()%></p>
                <form method='post' action='./Juego' >
                    <button class='ver' name='juego' value='<%= juego.getId_juego()%>'>
                        Ver
                    </button>
                </form>
                <form method='post' action='./Admin_juego_creado' class='editarYBorrar'>
                    <button name='irAEditar' value='<%= juego.getId_juego()%>'>
                        Editar
                    </button>
                    <button  name='borrarJuego' value='<%= juego.getId_juego()%>'>
                        Borrar Juego
                    </button>

                </form>
            </div>

            <%
                    }
                } catch (java.lang.NullPointerException n) {
                    out.print("<p>No se ha podido conectar con la base de datos</p>");
                }
            %>
            <a id="volver" href='index.jsp'>Volver</a>
        </main>
    </body>
</html>
