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
            
            //out.print(usuario.toString());

            
            %>
            <form method="post" action="./PerfilEdicion">
                <h1>Edición del perfil</h1>
                <table>
                    <tr>
                        <td>
                            <label for="nombre">Nombre</label>
                        </td>
                         <td>
                            <input type="text" name="nombre" id="nombre" value="<% out.print(usuario.getNombre()); %>" required/>
                        </td>
                    </tr>
                     <tr>
                        <td>
                            <label for="clave">Password</label>
                        </td>
                         <td>
                            <input type="password" name="clave" id="clave"/>
                        </td>
                    </tr>
                     <tr>
                        <td>
                            <label for="fecha">Fecha de nacimiento</label>
                        </td>
                         <td>
                            <input type="date" name="fecha" id="fecha" required value="<% out.print(usuario.getFecha_nacimiento()); %>"/>
                        </td>
                    </tr>
                     <tr>
                        <td>
                            <label for="nick">Nickname</label>
                        </td>
                         <td>
                            <input type="text" name="nick" id="nick" required value="<% out.print(usuario.getNickname()); %>"/>
                        </td>
                    </tr>
                </table>
                 <input type="submit" name="subir_cambios" value="Subir Cambios"/>
            </form>
    </body>
</html>
