<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="controlador" scope="session" class="service.postgres.Service" /><!doctype html>
<html lang="es">
    <head>
        <%@include file="includes/head.jsp" %>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/cover.css">
        <title>Inicio - PQRS</title>
    </head>

    <body class="text-center">

        <div class="container d-flex h-100 p-3 mx-auto flex-column">
            <header class="masthead mb-auto">
                <div class="inner">
                    <h3 class="masthead-brand">PQRS - UFPS</h3>
                    <nav class="nav nav-masthead justify-content-center">
                        <a class="nav-link active" href="index.jsp">Inicio</a>
                        <a class="nav-link" href="consultar.jsp">Consultar</a>
                        <a class="nav-link" href="registrar.jsp">Registrar</a>
                        <a class="nav-link" href="login.jsp">Iniciar sesión</a>
                    </nav>
                </div>
            </header>

            <main role="main" class="inner cover">
                <h1 class="cover-heading">Peticiones, Quejas, Reclamos y Solicitudes.</h1>
                <p class="lead">APLICATIVO WEB DE PETICIONES QUEJAS, RECLAMOS Y SUGERENCIAS (PQRS) PARA EL PROGRAMA DE ING. DE SISTEMAS DE LA UNIVERSIDAD FRANCISCO DE PAULA SANTANDER.</p>
                <p class="lead">
                    <a href="registrar.jsp" class="btn btn-lg mr-4 btn-primary">Registrar</a>
                    <a href="consultar.jsp" class="btn btn-lg ml-4 btn-secondary">Consultar</a>
                </p>
            </main>

            <footer class="mastfoot mt-auto">
                <div class="inner">
                    <p>UFPS. Ingenieria de Sistemas. 2020.</p>
                </div>
            </footer>
        </div>
        <%@include file="includes/scripts.jsp" %>
    </body>
</html>
