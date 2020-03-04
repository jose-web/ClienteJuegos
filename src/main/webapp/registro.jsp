<%-- 
    Document   : registro
    Created on : 28-feb-2020, 18:31:51
    Author     : Jose
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro</title>
        <link rel="stylesheet" href="css/estilosGenerales.css"/>
        <link rel="stylesheet" href="css/estilosRegistro.css"/>
    </head>
    <body>
        <div id="registro">
            <h1>Regístrate</h1>
            <form action="./Registro" method="post">
                <label>Nickname</label>
                <input type="text" name="nickname" required>
                <label>Nombre</label>
                <input type="text" name="nombre" required>
                <label>Contraseña</label>
                <input type="password" name="pass" required>
                <label>Fecha de nacimiento</label>
                <input type="date" name="fecnac" required>
                <input id="enviar" type="submit" value="Enviar"/>
                <a id="volver" href="index.jsp"`>Volver</a>
            </form>
        </div>
    </body>
</html>
