<%-- 
    Document   : addSaldo
    Created on : 3 mar. 2020, 12:22:02
    Author     : rosa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/estilosGenerales.css"/>
        <link rel="stylesheet" href="css/estilosSaldo.css"/>
        <title>Añadir Saldo</title>
        <%
            HttpSession sesion = request.getSession(true);
            //  out.print(sesion.getAttribute("sentencia"));
        %>
    </head>
    <body>

        <div id="addsaldo">
            <form method="post" action="./ControlUsuario">
                <h1>Añadir Saldo</h1>
                <table>
                    <tr>
                        <td>
                            <label for="titular">  Titular Tarjeta de Crédito</label>
                        </td>
                        <td>
                            <input  id="titular" type="text"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="numero"> Número de tarjeta de crédito</label>
                        </td>
                        <td>
                            <input id="numero" type="number" maxlength="16"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="cvv"> CVV</label>
                        </td>
                        <td>
                            <input id="cvv" type="number" maxlength="3" pattern="{3}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="caducidad">  Caducidad</label>
                        </td>
                        <td>
                            <input id="caducidad" type="date"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="cantidad">  Cantidad a añadir </label>
                        </td>
                        <td>
                            <input id="cantidad"type="number" step="0.01" name="saldoNuevo"/>
                        </td>
                    </tr>
                </table>
                <button id="subirSaldo" name="subirSaldo" value="subirSaldo">Subir saldo</button>
                <a id="volver" href="index.jsp"`>Volver</a>
            </form>
        </div>
    </body>
</html>
