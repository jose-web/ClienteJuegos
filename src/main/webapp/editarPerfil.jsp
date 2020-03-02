<%-- 
    Document   : editarPerfil
    Created on : 2 mar. 2020, 12:44:08
    Author     : rosa
--%>

<%@page import="daw.clientejuegos.modelo.UsuarioVO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar perfil</title>
    </head>
    <body>
        <%
            HttpSession sesion = request.getSession(true);
            // int usuario = Integer.parseInt(sesion.getAttribute("idUsuario").toString());

            UsuarioVO usuario = (UsuarioVO) sesion.getAttribute("usuario");
            
            out.print(usuario.toString());

            
            %>
            <form method="post" action="editarPerfil">
                <table>
                    <tr>
                        <td>
                            <label for="nombre">Nombre</label>
                        </td>
                         <td>
                            <input type="text" name="nombre"/>
                        </td>
                    </tr>
                </table>
            </form>
    </body>
</html>
