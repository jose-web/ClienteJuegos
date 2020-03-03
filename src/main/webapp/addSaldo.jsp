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
        <title>Añadir Saldo</title>
        <%
            HttpSession sesion = request.getSession(true);
            //  out.print(sesion.getAttribute("sentencia"));
%>
    </head>
    <body>
        <h1>Añadir Saldo</h1>
        <form method="post" action="./ControlUsuario">
            <table>
                <tr>
                    <td>
                        Titular Tarjeta de Crédito
                    </td>
                    <td>
                        <input type="text"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Número de tarjeta de crédito
                    </td>
                    <td>
                        <input type="number" maxlength="16"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        CVV
                    </td>
                    <td>
                        <input type="number" maxlength="3" pattern="{3}"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Caducidad
                    </td>
                    <td>
                        <input type="date"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Cantidad a añadir
                    </td>
                    <td>
                        <input type="number" step="0.01" name="saldoNuevo"/>
                    </td>
                </tr>
            </table>
            <button  name="subirSaldo" value="subirSaldo">Subir saldo</button>
        </form>
    </body>
</html>
