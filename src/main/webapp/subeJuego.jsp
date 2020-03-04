<%-- 
    Document   : subeJuego
    Created on : 03-mar-2020, 21:10:00
    Author     : Jose
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="post" action="./Admin_juego_creado"> 
            <table>
                <tr>
                    <td>
                        <label for="nombre">Nombre</label>
                    </td>
                    <td>
                        <input type="text" name="nombre" id="nombre" required>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="so">Sistema Operativo</label>
                    </td>
                    <td>
                        <select name="so" id="so">
                            <option value="w">w</option>
                            <option value="l">l</option>
                            <option value="m">m</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="tipo">Tipo</label>
                    </td>
                    <td>
                        <textarea name="tipo" id="tipo" required></textarea>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="descripcion">Descripcion</label>
                    </td>
                    <td>
                        <textarea name="descripcion" id="descripcion" required></textarea>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="precio">Precio</label>
                    </td>
                    <td>
                        <input type="text" name="precio" id="precio" required>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="pegi">Pegi</label>
                    </td>
                    <td>
                        <input type="number" name="pegi" id="pegi" required>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <button type="submit" name="addJuego">Añadir</button>
                    </td>
                </tr>
            </table>
        </form>
        <p><a href='mis_creaciones.jsp'>Volver</a></p>
    </body>
</html>
