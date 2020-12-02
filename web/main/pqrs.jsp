<%@page import="util.Utilidades"%>
<%@page import="dto.MotivoSolicitudDto"%>
<%@page import="dto.UsuarioDto"%>
<%@page import="dto.SolicitudDto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="controlador" scope="session" class="service.postgres.Service" />
<%
    List<SolicitudDto> solicitudes = controlador.serviceSolicitud().listarTodas();
%>
<!doctype html>
<html lang="es">
    <head>
        <%@include file="../includes/head.jsp" %>
        <title>Listados - PQRS</title>
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
                                <h6 class="m-0 font-weight-bold text-primary">Listado de PQRS</h6>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-sm table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th>Acción</th>
                                                <th>Identificación de usuario</th>
                                                <th>Usuario</th>
                                                <th>Tipo de PQRS</th>
                                                <th>Motivo de PQRS</th>
                                                <th>Solicitud</th>
                                                <th>Respuesta</th>
                                                <th>Fecha de registro</th>
                                                <th>Fecha de respuesta</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                for (SolicitudDto solicitud : solicitudes) {
                                                    UsuarioDto usuario = solicitud.getUsuario();
                                                    MotivoSolicitudDto motivo = solicitud.getMotivo();
                                            %>
                                            <tr>
                                                <td>
                                                    <div class="d-flex justify-content-center align-content-center">
                                                        <a title="Detalle" class="btn btn-sm btn-outline-info m-1" href="detalle_pqr.jsp?id=<%=solicitud.getId()%>"  ><i class="fas fa-info-circle"></i></a>
                                                    </div>
                                                </td>
                                                <td><%=usuario != null ? usuario.getTipoIdentificacion().getAbreviatura() + " " + usuario.getIdentificacion(): ""%></td>
                                                <td><%=usuario != null ? usuario.getNombreCompleto() : ""%></td>
                                                <td><%=motivo.getTipo().getDescripcion()%></td>
                                                <td><%=motivo.getDescripcion()%></td>
                                                <td><%=solicitud.getDescripcion()%></td>
                                                <td><%=solicitud.getRespuesta() != null ? solicitud.getRespuesta() : ""%></td>
                                                <td><%=Utilidades.formatDate(solicitud.getFechaRegistro(), "yyyy-MM-dd HH:mm")%></td>
                                                <td><%=solicitud.getFechaRespuesta() != null ? solicitud.getFechaRespuesta() : ""%></td>
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
        <%@include file="../includes/scripts.jsp" %>
    </body>
</html>

