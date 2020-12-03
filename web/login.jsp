<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="controlador" scope="session" class="service.postgres.Service" />
<!doctype html>
<html lang="es">
    <head>
        <%@include file="includes/head.jsp" %>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/cover.css">
        <title>Login - PQRS</title>
    </head>
    <body class="text-center">

        <div class="container d-flex h-100 p-3 mx-auto flex-column">
            <header class="masthead mb-auto">
                <div class="inner">
                    <h3 class="masthead-brand">PQRS - UFPS</h3>
                    <nav class="nav nav-masthead justify-content-center">
                        <a class="nav-link" href="index.jsp">Inicio</a>
                        <a class="nav-link" href="consultar.jsp">Consultar</a>
                        <a class="nav-link" href="registrar.jsp">Registrar</a>
                        <a class="nav-link active" href="login.jsp">Iniciar sesión</a>
                    </nav>
                </div>
            </header>

            <main role="main" class="">
                <div class="d-block container w-50 bg-white p-5 border border-primary rounded">
                    <h3 class="title">Panel Administrativo</h3>
                    <form action="login" method="POST">
                        <div class="form-group">
                            <label>Nombre de usuario</label>
                            <input type="text" class="form-control" name="username" required autofocus>
                        </div>
                        <div class="form-group">
                            <label>Contraseña</label>
                            <input type="password" class="form-control" name="password" required>
                        </div>
                        <div class="form-group">
                            <button class="btn btn-success" type="submit" >Iniciar</button>
                        </div>
                    </form>
                </div>
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

