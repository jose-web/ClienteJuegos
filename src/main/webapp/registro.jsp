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
        <title>JSP Page</title>
        <style>
            *{
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }
            body{
                background: rgb(0,0,0);
                background: -moz-linear-gradient(146deg, rgba(0,0,0,1) 0%, rgba(14,0,80,1) 50%, rgba(57,9,121,1) 100%);
                background: -webkit-linear-gradient(146deg, rgba(0,0,0,1) 0%, rgba(14,0,80,1) 50%, rgba(57,9,121,1) 100%);
                background: linear-gradient(146deg, rgba(0,0,0,1) 0%, rgba(14,0,80,1) 50%, rgba(57,9,121,1) 100%);
                filter: progid:DXImageTransform.Microsoft.gradient(startColorstr="#000000",endColorstr="#390979",GradientType=1);
                background-attachment: fixed;
                min-height:100vh;
                display:flex;
                justify-content: center;
                align-items: center;

            }

            #registro{
                flex:0 50%;
                background-color: white;
                border-radius: 0.5em;
                padding:0.5em;
                display: flex;
                flex-direction: column;
            }
            h1{
                text-align: center;
            }

            form{
                display:flex;
                flex-direction: column;
            }
        </style>
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
                <input type="submit">
            </form>
        </div>
    </body>
</html>
