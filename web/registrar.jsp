<%@page import="java.util.List"%>
<%@page import="dto.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="controlador" scope="session" class="service.postgres.Service" />
<%

    List<TipoIdentificacionDto> tiposIds = controlador.serviceTipoIdentificacion().listarTodos();
    List<TipoUsuarioDto> tiposUsuarios = controlador.serviceTipoUsuario().listarActivos();
    List<TipoSolicitudDto> tiposSolicitud = controlador.serviceTipoSolicitud().listarActivos();

%>
<!doctype html>
<html lang="es">
    <head>
        <%@include file="includes/head.jsp" %>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/cover.css">
        <title>Registrar - PQRS</title>
    </head>
    <body class="">

        <div class="container d-block p-3">
            <header class="masthead mb-5">
                <div class="inner">
                    <h3 class="masthead-brand">PQRS - UFPS</h3>
                    <nav class="nav nav-masthead justify-content-center">
                        <a class="nav-link" href="index.jsp">Inicio</a>
                        <a class="nav-link" href="consultar.jsp">Consultar</a>
                        <a class="nav-link active" href="registrar.jsp">Registrar</a>
                        <a class="nav-link" href="login.jsp">Iniciar sesión</a>
                    </nav>
                </div>
            </header>

            <div class="container">

                <div class="card shadow mb-4">
                    <div class="card-header py-3">

                        <h6 class="m-0 font-weight-bold text-primary">Registrar PQRS</h6>
                    </div>
                    <div class="card-body">
                        <form action="registrar-pqrs" method="post" enctype="multipart/form-data">
                            <div class="form-group">
                                <h5 class="card-title">Datos del usuario</h5>
                                <div class="form-row">
                                    <div class="col-lg-2">
                                        <div class="form-group">
                                            <label>Tipo de Usuario *</label>
                                            <select class="form-control" name="tipo_usuario">
                                                <option value="" disabled>Seleccione ..</option>
                                                <% for (TipoUsuarioDto tipoId : tiposUsuarios) {%>
                                                <option value="<%=tipoId.getId()%>"><%=tipoId.getDescripcion()%></option>
                                                <% }%>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-lg-2">
                                        <div class="form-group">
                                            <label>Tipo de Documento *</label>
                                            <select class="form-control" name="tipo_identificacion">
                                                <option value="" disabled>Seleccione ..</option>
                                                <% for (TipoIdentificacionDto tipoId : tiposIds) {%>
                                                <option value="<%=tipoId.getId()%>"><%=tipoId.getDescripcion()%></option>
                                                <% }%>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-lg-6">
                                        <div class="form-group">
                                            <label>Identificación *</label>
                                            <input type="number" class="form-control" name="identificacion" required="true">
                                        </div>
                                    </div>
                                    <div class="col-lg-2">
                                        <div class="form-group">
                                            <label>Código</label>
                                            <input type="number" class="form-control" name="codigo_interno">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="col-lg-3">
                                        <div class="form-group">
                                            <label>Primer Nombre *</label>
                                            <input type="text" class="form-control" name="primer_nombre" required="true">
                                        </div>
                                    </div>
                                    <div class="col-lg-3">
                                        <div class="form-group">
                                            <label>Segundo Nombre</label>
                                            <input type="text" class="form-control" name="segundo_nombre">
                                        </div>
                                    </div>
                                    <div class="col-lg-3">
                                        <div class="form-group">
                                            <label>Primer Apellido *</label>
                                            <input type="text" class="form-control" name="primer_apellido" required="true">
                                        </div>
                                    </div>
                                    <div class="col-lg-3">
                                        <div class="form-group">
                                            <label>Segundo apellido</label>
                                            <input type="text" class="form-control" name="segundo_apellido">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="col-lg-3">
                                        <div class="form-group">
                                            <label>Dirección *</label>
                                            <input type="text" class="form-control" name="direccion" required="true">
                                        </div>
                                    </div>
                                    <div class="col-lg-3">
                                        <div class="form-group">
                                            <label>Correo *</label>
                                            <input type="text" class="form-control" name="correo" required="true">
                                        </div>
                                    </div>
                                    <div class="col-lg-3">
                                        <div class="form-group">
                                            <label>Celular</label>
                                            <input type="text" class="form-control" name="celular">
                                        </div>
                                    </div>
                                    <div class="col-lg-3">
                                        <div class="form-group">
                                            <label>Teléfono</label>
                                            <input type="text" class="form-control" name="telefono">
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <h5 class="card-title">Datos de la PQRS</h5>
                                <div class="form-row">
                                    <div class="col-lg-3">
                                        <div class="form-group">
                                            <label>Tipo de Solicitud *</label>
                                            <select class="form-control" required name="tipo_solicitud" id="form_tipo_solicitud">
                                                <option value="" disabled>Seleccione ..</option>
                                                <% for (TipoSolicitudDto tipoId : tiposSolicitud) {%>
                                                <option value="<%=tipoId.getId()%>"><%=tipoId.getDescripcion()%></option>
                                                <% }%>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-lg-3">
                                        <div class="form-group">
                                            <label>Motivo de Solicitud *</label>
                                            <select class="form-control" required name="motivo_solicitud" id="select_motivos_solicitud"></select>
                                        </div>
                                    </div>      
                                    <div class="col-md-6 d-flex align-items-end">
                                        <div class="input-group mb-3">
                                            <div class="custom-file">
                                                <input name="file" type="file" class="custom-file-input" id="inputGroupFile01">
                                                <label class="custom-file-label" for="inputGroupFile01">Cargar</label>
                                            </div>
                                        </div>
                                    </div>                                  
                                </div>
                                <div class="form-row">  
                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label>Descripción *</label>
                                            <textarea class="form-control" name="descripcion" maxlength="2000" required minlength="10"></textarea>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-row">
                                    <div class="col-md-12 d-flex justify-content-center">
                                        <button class="btn btn-success m-1" type="submit">Enviar</button>
                                        <a class="btn btn-danger m-1" href="index.jsp">Cancelar</a>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="includes/scripts.jsp" %>
        <script>
            cargarImplements("<%=request.getContextPath()%>/implements/motivos_solicitud.jsp", {tipo: $("#form_tipo_solicitud").val()}, "#select_motivos_solicitud")

            $("#form_tipo_solicitud").on('change', function (e) {
                cargarImplements("<%=request.getContextPath()%>/implements/motivos_solicitud.jsp", {tipo: this.value}, "#select_motivos_solicitud")
            });
            CKEDITOR.replace('descripcion');
            multipleFiles('#inputGroupFile01', 1)
        </script>
    </body>
</html>

