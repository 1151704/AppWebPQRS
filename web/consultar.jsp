<%-- 
    Document   : consultar
    Created on : 17-nov-2020, 8:42:05
    Author     : OMAR
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Consultar PQRS</title>
    </head>
    <body>
        <h1>Consultar PQRS</h1>
        <form>
            <input placeholder="Número de radicado" name="radicado" type="text" required="true" autofocus="true">
            <select name="tipo_identificacion" required="true">
                <option value="1" selected="true">CC</option>
                <option value="2">TI</option>
                <option value="3">NIT</option>
            </select>
            <input placeholder="Número de identificación" name="identificacion" type="text" required="true">
            <button type="submit">Consultar</button>
        </form>
    </body>
</html>
