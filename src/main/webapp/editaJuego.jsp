<%-- 
    Document   : editaJuego
    Created on : 3 mar. 2020, 9:54:50
    Author     : jose
--%>

<%@page import="daw.clientejuegos.modelo.BibliotecaDAO"%>
<%@page import="daw.clientejuegos.modelo.UsuarioVO"%>
<%@page import="daw.clientejuegos.modelo.JuegoDAO"%>
<%@page import="daw.clientejuegos.modelo.JuegoVO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edita tu juego</title>
    </head>
    <body>
        <%
            UsuarioVO usuario = (UsuarioVO) session.getAttribute("usuario");

            String sesione = session.getAttribute("irAEditar").toString();
            int idJuego = Integer.parseInt(sesione);

            int pertenece = JuegoDAO.consultarPertenenciaJuegoCreado(usuario.getId_usuario(), idJuego);
            switch (pertenece) {
                case 0:
                    out.print("Este juego no ha sido creado por ti y no puedes editarlo");
                    break;
                case -1:
                    out.print("Ha ocurrido un error con la base de datos");
                    break;
                case 1:

                    JuegoVO juego = JuegoDAO.buscar_juego(idJuego);
        %>  
        <form method="post" action="./Admin_juego_creado"> 
            <table>
                <tr>
                    <td>
                        <label for="nombre">Nombre</label>
                    </td>
                    <td>
                        <input type="text" name="nombre" id="nombre" value="<%=juego.getNombre_juego()%>">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="imagen">Imagen</label>
                    </td>
                    <td>
                        <img src="img/<%=juego.getImg()%>">
                        <br>
                        <input type="file" name="imagen" id="imagen">   
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="so">Sistema Operativo</label>
                    </td>
                    <td>
                        <select name="so" id="so">
                            <option value="w" <%= "w".equals(juego.getSistema_operativo()) ? "selected = 'selected'" : ""%>>w</option>
                            <option value="l" <%= "l".equals(juego.getSistema_operativo()) ? "selected = 'selected'" : ""%>>l</option>
                            <option value="m" <%= "m".equals(juego.getSistema_operativo()) ? "selected = 'selected'" : ""%>>m</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="tipo">Tipo</label>
                    </td>
                    <td>
                        <textarea name="tipo" id="tipo"><%=juego.getTipo()%></textarea>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="descripcion">Descripcion</label>
                    </td>
                    <td>
                        <textarea name="descripcion" id="descripcion"><%=juego.getDescripcion()%></textarea>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="precio">Precio</label>
                    </td>
                    <td>
                        <input type="text" name="precio" id="precio" value="<%=juego.getPrecio()%>">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="pegi">Pegi</label>
                    </td>
                    <td>
                        <input type="number" name="pegi" id="pegi" value="<%=juego.getPegi()%>">
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <button type="submit" name="editar" value="<%=juego.getId_juego()%>">Editar</button>
                    </td>
                </tr>
            </table>
        </form>
        <%
            }
        %>
        <p><a href='mis_creaciones.jsp'>Volver</a></p>
    </body>
</html>
