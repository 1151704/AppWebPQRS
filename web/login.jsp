<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="es">
    <head>
        <%@include file="includes/head.jsp" %>
        <title>Login - PQRS</title>
    </head>
    <body>
        <div class="container">
            <!-- Outer Row -->
            <div class="row justify-content-center">
                <div class="col-xl-10 col-lg-12 col-md-9">
                    <div class="card o-hidden border-0 shadow-lg my-5">
                        <div class="card-body p-0">
                            <!-- Nested Row within Card Body -->
                            <div class="row">
                                <div class="col-lg-6 d-none d-lg-flex">
                                    <img class="img-fluid" src="static/images/pqrs.jpg">
                                </div>
                                <div class="col-lg-6 d-flex justify-center align-items-center">
                                    <div class="d-block p-5 col-12">
                                        <div class="text-center">
                                            <h1 class="h4 text-gray-900 mb-4 title-login">PQRS UFPS</h1>
                                        </div>
                                        <form class="user" action="main/index.jsp" method="get">
                                            <div class="form-group">
                                                <input type="text" class="form-control form-control-user" name="username" required autofocus placeholder="Identificación">
                                            </div>
                                            <div class="form-group">
                                                <input type="password" name="password" required class="form-control form-control-user" placeholder="Contraseña">
                                            </div>
                                            <button type="submit" class="btn btn-primary btn-user btn-block">Ingresar</button>
                                        </form>
                                        <div class="alert alert-danger mt-3">
                                            Error: 
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="includes/scripts.jsp" %>
    </body>
</html>

