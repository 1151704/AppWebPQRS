<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="es">
    <head>
        <%@include file="includes/head.jsp" %>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/cover.css">
        <title>Login - PQRS</title>
    </head>
    <body class="text-center">

        <div class="cover-container d-flex h-100 p-3 mx-auto flex-column">
            <header class="masthead mb-auto">
                <div class="inner">
                    <h3 class="masthead-brand">PQRS - UFPS</h3>
                    <nav class="nav nav-masthead justify-content-center">
                        <a class="nav-link" href="index.jsp">Inicio</a>
                        <a class="nav-link active" href="login.jsp">Iniciar sesión</a>
                    </nav>
                </div>
            </header>

            <main role="main" class="inner cover">
                <div class="d-block container bg-white p-5 m-5 border border-primary rounded">
                    <form action="main/index.jsp" method="get">
                        <div class="form-group">
                            <label>Nombre de usuario</label>
                            <input type="text" class="form-control" name="username" required autofocus>
                        </div>
                        <div class="form-group">
                            <label>Contraseña</label>
                            <input type="password" class="form-control" name="password" required>
                        </div>
                        <div class="form-group">
                            <button class="btn btn-success" >Iniciar</button>
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

