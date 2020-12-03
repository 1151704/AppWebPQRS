<%@page import="dto.ArchivoDto"%>
<%@page import="dto.SolicitudArchivosDto"%>
<%@page import="java.util.List"%>
<%@page import="dto.FuncionarioDto"%>
<%@page import="dto.MotivoSolicitudDto"%>
<%@page import="dto.UsuarioDto"%>
<%@page import="dto.SolicitudDto"%>
<%@page import="util.Utilidades"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="controlador" scope="session" class="service.postgres.Service" />
<jsp:include page="../includes/verificarAcceso.jsp" flush="true"/>
<%
    Integer id_pqr = Utilidades.validateInputNumber(request.getParameter("id"));

    if (id_pqr == null) {
        out.print("<script>window.location.href = '" + request.getContextPath() + "/main/pqrs.jsp';</script>");
    }

    SolicitudDto solicitud = controlador.serviceSolicitud().buscarPorId(id_pqr);

    if (solicitud == null) {
        out.print("<script>window.location.href = '" + request.getContextPath() + "/main/pqrs.jsp';</script>");
    } else {
        UsuarioDto usuario = solicitud.getUsuario();
        MotivoSolicitudDto motivo = solicitud.getMotivo();
        FuncionarioDto funcionario = solicitud.getFuncionario();
        List<SolicitudArchivosDto> archivosRta = controlador.serviceSolicitudArchivos().listarPorSolicitudRespuesta(id_pqr, true);
        List<SolicitudArchivosDto> archivosEnv = controlador.serviceSolicitudArchivos().listarPorSolicitudRespuesta(id_pqr, false);
%>
<!doctype html>
<html lang="es">
    <head>
        <%@include file="../includes/head.jsp" %>
        <title>Detalle - PQRS</title>
    </head>
    <body>
        <!-- Page Wrapper -->
        <div id="wrapper">
            <%@include file="../includes/menu.jsp" %>

            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">

                <!-- Main Content -->
                <div id="content">
                    <%@include file="../includes/topbar.jsp" %>     

                    <!-- Begin Page Content -->
                    <div class="container-fluid">

                        <div class="card shadow mb-4">
                            <div class="card-header py-3">

                                <h6 class="m-0 font-weight-bold text-primary"><a class="btn btn-sm btn-outline-dark mr-1" href="pqrs.jsp"><i class="fas fa-arrow-circle-left"></i> Volver</a>Detalle de PQRS</h6>
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
                                                <textarea class="form-control" disabled="true" readonly="true"><%=solicitud.getDescripcion()%></textarea>
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
                                                    <a href="<%=archivo.getUrlArchivo()%>" ><%=archivo.getNombrePorUsuario()%> <i class="fas fa-download"></i></a>
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
                                                <a href="<%=archivo.getUrlArchivo()%>" ><%=archivo.getNombrePorUsuario()%> <i class="fas fa-download"></i></a>
                                            </li>
                                            <% } %>
                                    </div>
                                    <% }%>

                                </div>
                                <div class="form-row">   

                                    <div class="col-md-12">
                                        <div class="form-group">
                                            <label>Respuesta</label>
                                            <textarea class="form-control" disabled="true" readonly="true"><%=solicitud.getRespuesta()%></textarea>
                                        </div>
                                    </div>
                                </div>
                                <% } else {%>         
                                <form action="<%=request.getContextPath()%>/registrar-respuesta-pqrs" method="post" enctype="multipart/form-data">
                                    <input type="hidden" value="<%=solicitud.getId()%>" name="id_pqrs">
                                    <div class="form-row">   

                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>Respuesta</label>
                                                <textarea class="form-control" name="respuesta"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-row">   

                                        <div class="col-md-12">
                                            <div class="input-group mb-3">
                                                <div class="custom-file">
                                                    <input name="file" type="file" class="custom-file-input" id="inputGroupFile01">
                                                    <label class="custom-file-label" for="inputGroupFile01">Cargar</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-row">
                                        <div class="col-md-12 d-flex justify-content-center">
                                            <button class="btn btn-success m-1" type="submit">Enviar</button>
                                            <a class="btn btn-danger m-1" href="pqrs.jsp">Cancelar</a>
                                        </div>
                                    </div>
                                </form>
                                <% }%>
                            </div>
                        </div>
                    </div>
                    <!-- /.container-fluid -->

                </div>
                <!-- End of Main Content -->
                <%@include file="../includes/footer.jsp" %>
            </div>
            <!-- End of Content Wrapper -->
        </div>
        <!-- End of Page Wrapper -->
        <%@include file="../includes/scripts.jsp" %>
    </body>
</html>
<% }%>

