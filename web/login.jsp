<%-- 
    Document   : consultar
    Created on : 17-nov-2020, 8:42:05
    Author     : OMAR
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Login - PQRS</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" type="image/x-icon" href="favicon.ico">

        <link rel="stylesheet" href="static/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="static/sb-admin-2/sb-admin-2.min.css">
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
                                <form class="user">
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
        
        
        <script src="static/jquery-3.5.1.min.js"></script>
        <script src="static/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="static/sb-admin-2/sb-admin-2.min.js"></script>
    </body>
</html>

