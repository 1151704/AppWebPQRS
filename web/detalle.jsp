<%@page import="util.Utilidades"%>
<%@page import="java.util.List"%>
<%@page import="dto.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="controlador" scope="session" class="service.postgres.Service" />
<%
    if (session.getAttribute("solicitud") == null) {
        out.print("<script>window.location.href = '" + request.getContextPath() + "/consultar.jsp';</script>");
    } else {
        SolicitudDto solicitud = (SolicitudDto) session.getAttribute("solicitud");
        UsuarioDto usuario = solicitud.getUsuario();
        MotivoSolicitudDto motivo = solicitud.getMotivo();
        FuncionarioDto funcionario = solicitud.getFuncionario();

        List<SolicitudArchivosDto> archivosEnv = (List<SolicitudArchivosDto>) session.getAttribute("archivos_env");
        List<SolicitudArchivosDto> archivosRta = (List<SolicitudArchivosDto>) session.getAttribute("archivos_rta");

        List<TipoIdentificacionDto> tiposIds = controlador.serviceTipoIdentificacion().listarTodos();
        List<TipoUsuarioDto> tiposUsuarios = controlador.serviceTipoUsuario().listarTodos();
        List<TipoSolicitudDto> tiposSolicitud = controlador.serviceTipoSolicitud().listarTodos();

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
                        <a class="nav-link active" href="consultar.jsp">Consultar</a>
                        <a class="nav-link" href="registrar.jsp">Registrar</a>
                        <a class="nav-link" href="login.jsp">Iniciar sesión</a>
                    </nav>
                </div>
            </header>

            <div class="container">

                <div class="card shadow mb-4">
                    <div class="card-header py-3">

                        <h6 class="m-0 font-weight-bold text-primary">Detalle de PQRS</h6>
                    </div>
                    <div class="card-body">
                        <div class="form-group">
                            <div class="form-row">
                                <div class="col-lg-3">
                                    <div class="form-group">
                                        <label>Tipo de Usuario</label>
                                        <input type="text" value="<%=usuario.getTipoUsuario().getDescripcion()%>" class="form-control" disabled="true" readonly="true">
                                    </div>
                                </div>
                                <div class="col-lg-3">
                                    <div class="form-group">
                                        <label>Tipo de Identificación</label>
                                        <input type="text" value="<%=usuario.getTipoIdentificacion().getDescripcion()%>" class="form-control" disabled="true" readonly="true">
                                    </div>
                                </div>
                                <div class="col-lg-6">
                                    <div class="form-group">
                                        <label>Identificación</label>
                                        <input type="text" value="<%=usuario.getIdentificacion()%>" class="form-control" disabled="true" readonly="true">
                                    </div>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="col-lg-3">
                                    <div class="form-group">
                                        <label>Primer Nombre</label>
                                        <input type="text" value="<%=usuario.getPrimerNombre()%>" class="form-control" disabled="true" readonly="true">
                                    </div>
                                </div>
                                <div class="col-lg-3">
                                    <div class="form-group">
                                        <label>Segundo Nombre</label>
                                        <input type="text" value="<%=usuario.getSegundoNombre()%>" class="form-control" disabled="true" readonly="true">
                                    </div>
                                </div>
                                <div class="col-lg-3">
                                    <div class="form-group">
                                        <label>Primer Apellido</label>
                                        <input type="text" value="<%=usuario.getPrimerApellido()%>" class="form-control" disabled="true" readonly="true">
                                    </div>
                                </div>
                                <div class="col-lg-3">
                                    <div class="form-group">
                                        <label>Segundo Apellido</label>
                                        <input type="text" value="<%=usuario.getSegundoApellido()%>" class="form-control" disabled="true" readonly="true">
                                    </div>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="col-lg-4">
                                    <div class="form-group">
                                        <label>Celular</label>
                                        <input type="text" value="<%=usuario.getCelular()%>" class="form-control" disabled="true" readonly="true">
                                    </div>
                                </div>
                                <div class="col-lg-4">
                                    <div class="form-group">
                                        <label>Correo</label>
                                        <input type="text" value="<%=usuario.getCorreo()%>" class="form-control" disabled="true" readonly="true">
                                    </div>
                                </div>
                                <div class="col-lg-4">
                                    <div class="form-group">
                                        <label>Dirección</label>
                                        <input type="text" value="<%=usuario.getDireccion()%>" class="form-control" disabled="true" readonly="true">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="form-row">
                                <div class="col-lg-3">
                                    <div class="form-group">
                                        <label>Asignada a</label>
                                        <input type="text" value="<%=funcionario.getNombreCompleto()%>" class="form-control" disabled="true" readonly="true">
                                    </div>
                                </div>
                                <div class="col-lg-3">
                                    <div class="form-group">
                                        <label>Tipo de Solicitud</label>
                                        <input type="text" value="<%=motivo.getTipo().getDescripcion()%>" class="form-control" disabled="true" readonly="true">
                                    </div>
                                </div>
                                <div class="col-lg-6">
                                    <div class="form-group">
                                        <label>Motivo</label>
                                        <input type="text" value="<%=motivo.getDescripcion()%>" class="form-control" disabled="true" readonly="true">
                                    </div>
                                </div>
                            </div>
                            <% if (motivo.getHabilitarEntrada()) {%>
                            <div class="form-row">
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <label>Otro Motivo</label>
                                        <input type="text" value="<%=solicitud.getOtroMotivo()%>" class="form-control" disabled="true" readonly="true">
                                    </div>
                                </div>
                            </div>                                  
                            <% }%>

                            <div class="form-row">
                                <div class="col-lg-12">
                                    <div class="form-group">
                                        <label>Solicitud</label>
                                        <div class="container-html"><%=solicitud.getDescripcion()%></div>
                                    </div>
                                </div>
                            </div>                

                            <div class="form-row">                                        
                                <div class="col-lg-3">
                                    <div class="form-group">
                                        <label>Fecha de solicitud</label>
                                        <input type="text" value="<%=Utilidades.formatDate(solicitud.getFechaRegistro(), "yyyy-MM-dd")%>" class="form-control" disabled="true" readonly="true">
                                    </div>
                                </div>
                                <% if (!archivosEnv.isEmpty()) { %>
                                <div class="col-md-9">
                                    <ul class="list-group">
                                        <% for (SolicitudArchivosDto a : archivosEnv) { %>
                                        <% ArchivoDto archivo = a.getArchivo();%>
                                        <li class="list-group-item">
                                            <a href="<%=archivo.getUrlArchivo()%>" target="_blanck" ><%=archivo.getNombrePorUsuario()%> <i class="fas fa-download"></i></a>
                                        </li>
                                        <% } %>
                                </div>
                                <% }%>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">Respuesta de PQRS</h6>
                    </div>
                    <div class="card-body">    
                        <% if (solicitud.getRespondida()) {%>
                        <div class="form-row">

                            <div class="col-lg-3">
                                <div class="form-group">
                                    <label>Fecha de Respuesta</label>
                                    <input type="text" value="<%=Utilidades.formatDate(solicitud.getFechaRespuesta(), "yyyy-MM-dd HH:mm")%>" class="form-control" disabled="true" readonly="true">
                                </div>
                            </div>
                            <% if (!archivosRta.isEmpty()) { %>
                            <div class="col-md-9">
                                <ul class="list-group">
                                    <% for (SolicitudArchivosDto a : archivosRta) { %>
                                    <% ArchivoDto archivo = a.getArchivo();%>
                                    <li class="list-group-item">
                                        <a href="<%=archivo.getUrlArchivo()%>" target="_blanck" ><%=archivo.getNombrePorUsuario()%> <i class="fas fa-download"></i></a>
                                    </li>
                                    <% } %>
                            </div>
                            <% }%>

                        </div>
                        <div class="form-row">   

                            <div class="col-md-12">
                                <div class="form-group">
                                    <label>Respuesta</label>
                                    <div class="container-html"><%=solicitud.getRespuesta()%></div>
                                </div>
                            </div>
                        </div>
                        <% }%>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="includes/scripts.jsp" %>
    </body>
</html>
<% }%>

