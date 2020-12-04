<%@page import="dto.*"%>
<%@page import="util.Utilidades"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="controlador" scope="session" class="service.postgres.Service" />
<jsp:include page="../includes/verificarAcceso.jsp" flush="true"/>
<%
    Integer id_tipo = Utilidades.validateInputNumber(request.getParameter("id"));

    if (id_tipo == null) {
        out.print("<script>window.location.href = '" + request.getContextPath() + "/main/tipos_solicitudes.jsp';</script>");
    }

    TipoSolicitudDto tipoSolicitud = controlador.serviceTipoSolicitud().buscarPorId(id_tipo);

    if (tipoSolicitud == null) {
        out.print("<script>window.location.href = '" + request.getContextPath() + "/main/tipos_solicitudes.jsp';</script>");
    } else {
        List<MotivoSolicitudDto> motivos = controlador.serviceMotivoSolicitud().listarPorTipoSolicitud(id_tipo);
%>
<!doctype html>
<html lang="es">
    <head>
        <jsp:include page="../includes/head.jsp" flush="true" />
        <title>Motivos de Solicitudes - PQRS</title>
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
                                <h6 class="m-0 font-weight-bold text-primary"><a class="btn btn-sm btn-outline-dark mr-1" href="tipos_solicitudes.jsp"><i class="fas fa-arrow-circle-left"></i> Volver</a>Motivos de Solicitudes</h6>
                            </div>
                            <div class="card-body">

                                <div class="form-group">
                                    <div class="form-row">
                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label>Tipo de Solicitud </label>
                                                <input type="text" class="form-control" readonly disabled value="<%=tipoSolicitud.getDescripcion()%>">
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <a class="btn btn-success mb-3" title="Registrar" href="registrar_motivo_solicitud.jsp?id=<%=id_tipo%>">
                                    <i class="fas fa-plus-circle"></i> Registrar
                                </a>

                                <div class="table-responsive">
                                    <table class="table table-sm table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th>Acción</th>
                                                <th>Tipo</th>
                                                <th>Habilitado</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                for (MotivoSolicitudDto tipo : motivos) {
                                            %>
                                            <tr>
                                                <td>
                                                    <div class="d-flex justify-content-center align-content-center">
                                                        <a title="Detalle" class="btn btn-sm btn-outline-info m-1" href="actualizar_motivo_solicitud.jsp?id=<%=tipo.getId()%>"  ><i class="fas fa-info-circle"></i></a>
                                                    </div>
                                                </td>
                                                <td><%=tipo.getDescripcion()%></td>
                                                <td><%=tipo.getHabilitado() ? "Si" : "No"%></td>
                                            </tr>
                                            <% }%>
                                        </tbody>
                                    </table>
                                </div>
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
        <jsp:include page="../includes/scripts.jsp" flush="true" />
    </body>
</html>
<% }%>

