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
        <link rel="stylesheet" href="css/estilosGenerales.css"/>
        <link rel="stylesheet" href="css/estilosSubirJuego.css"/>
        <title>Subir juegos</title>
    </head>
    <body>
        <div id="subir_juego">
            <form method="post" action="./Admin_juego_creado"> 
                <h1>Subir Juego</h1>
                <table>
                    <tr>
                        <td>
                            <label for="nombre">Nombre</label>
                        </td>
                        <td>
                            <input .class="dcha" type="text" name="nombre" id="nombre" required>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="so">Sistema Operativo</label>
                        </td>
                        <td>
                            <select .class="dcha" name="so" id="so">
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
                            <textarea .class="dcha" name="tipo" id="tipo" required></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="descripcion">Descripcion</label>
                        </td>
                        <td>
                            <textarea  .class="dcha" name="descripcion" id="descripcion" required></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="precio">Precio</label>
                        </td>
                        <td>
                            <input .class="dcha" type="text" name="precio" id="precio" required>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="pegi">Pegi</label>
                        </td>
                        <td>
                            <input .class="dcha"  type="number" name="pegi" id="pegi" required>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <button id="addJuego" type="submit" name="addJuego">Añadir</button>
                        </td>
                    </tr>
                </table>
                <a id="volver" href='mis_creaciones.jsp'>Volver</a>
            </form>
        </div>
    </body>
</html>
