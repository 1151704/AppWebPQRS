<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="controlador" scope="session" class="service.postgres.Service" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PQRS - Inicio</title>
    </head>
    <body>
        <h1>PQRS - UFPS</h1>
        <a href="registrar.jsp">Registrar PQRS</a>
        <a href="consultar.jsp">Consultar PQRS</a>
        <a href="login.jsp">Iniciar sesión</a>
    </body>
</html>
