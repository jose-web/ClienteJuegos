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
            <%                    if (sesion.getAttribute("error_edicion") != null) {
                    switch (Integer.parseInt(sesion.getAttribute("error_edicion").toString())) {
                        case 1:
                            out.print("Se ha actualizado correctamente");
                            break;
                        case -1:
                            out.print("Ha ocurrido un error con la base de datos");
                            break;

                    }
                    sesion.setAttribute("error_edicion", null);
                }

        //   out.print(sesion.getAttribute("repetido"));
           
                if (sesion.getAttribute("repetido") != null) {
                    switch (Integer.parseInt(sesion.getAttribute("repetido").toString())) {
                        case 1:
                            out.print("El nick ya existe, por favor introduzca uno distinto");
                            break;
                        case -1:
                            out.print("Ha ocurrido un error con la base de datos");
                            break;
                    }
                    sesion.setAttribute("repetido", null);
                }

            %>
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
                        <input type="text" name="nick" id="nick" required value="<% out.print(usuario.getNickname());%>"/>
                    </td>
                </tr>
            </table>
            <input type="submit" name="subir_cambios" value="Subir Cambios"/>
            <br/>
            <a href="index.jsp">Volver</a>
        </form>
    </body>
</html>
