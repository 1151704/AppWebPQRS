<%@page import="dto.FuncionarioDto"%>
<%@page import="util.Utilidades"%>
<%@page import="dto.MotivoSolicitudDto"%>
<%@page import="dto.UsuarioDto"%>
<%@page import="dto.SolicitudDto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="controlador" scope="session" class="service.postgres.Service" />
<jsp:include page="../includes/verificarAcceso.jsp" flush="true"/>
<%
    FuncionarioDto funcionario = (FuncionarioDto) session.getAttribute("usuarioActual");
    List<SolicitudDto> solicitudes;
    if (funcionario.getEsAdministrador()) {
        solicitudes = controlador.serviceSolicitud().listarTodas();
    } else {
        solicitudes = controlador.serviceSolicitud().buscarPorFuncionario(funcionario.getId());
    }
%>
<!doctype html>
<html lang="es">
    <head>
        <jsp:include page="../includes/head.jsp" flush="true" />
        <title>Listados - PQRS</title>
    </head>
    <body>
        <!-- Page Wrapper -->
        <div id="wrapper">
            <jsp:include page="../includes/menu.jsp" flush="true" />

            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">

                <!-- Main Content -->
                <div id="content">
                    <jsp:include page="../includes/topbar.jsp" flush="true" />    

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
                                                <th>Radicado</th>
                                                <th>Identificación de usuario</th>
                                                <th>Usuario</th>
                                                <th>Tipo de PQRS</th>
                                                <th>Motivo de PQRS</th>
                                                <th>Solicitud</th>
                                                <th>Respuesta</th>
                                                <th>Fecha de registro</th>
                                                <th>Fecha de respuesta</th>
                                                    <% if (funcionario.getEsAdministrador()) { %>
                                                <th>Funcionario</th>
                                                    <% } %>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                for (SolicitudDto solicitud : solicitudes) {
                                                    UsuarioDto usuario = solicitud.getUsuario();
                                                    MotivoSolicitudDto motivo = solicitud.getMotivo();
                                                    FuncionarioDto asignada = null;
                                                    if (funcionario.getEsAdministrador()) {
                                                        asignada = solicitud.getFuncionario();
                                                    }
                                            %>
                                            <tr class="<%=solicitud.getRespondida() ? "tr-select" : ""%>">
                                                <td>
                                                    <div class="d-flex justify-content-center align-content-center">
                                                        <a title="Detalle" class="btn btn-sm btn-outline-info m-1" href="detalle_pqrs.jsp?id=<%=solicitud.getId()%>"  ><i class="fas fa-info-circle"></i></a>
                                                    </div>
                                                </td>
                                                <td><%=solicitud.getId()%></td>
                                                <td><%=usuario != null ? usuario.getTipoIdentificacion().getAbreviatura() + " " + usuario.getIdentificacion() : ""%></td>
                                                <td><%=usuario != null ? usuario.getNombreCompleto() : ""%></td>
                                                <td><%=motivo.getTipo().getDescripcion()%></td>
                                                <td><%=motivo.getDescripcion()%></td>
                                                <td><div class="container-html container-html-td"><%=solicitud.getDescripcion()%></div></td>
                                                <td>
                                                    <% if (solicitud.getRespondida()) {%>
                                                    <div class="container-html container-html-td"><%=solicitud.getRespuesta()%></div>
                                                    <%}%>
                                                </td>
                                                <td><%=Utilidades.formatDate(solicitud.getFechaRegistro(), "yyyy-MM-dd HH:mm")%></td>
                                                <td><%=solicitud.getFechaRespuesta() != null ? Utilidades.formatDate(solicitud.getFechaRespuesta(), "yyyy-MM-dd HH:mm") : ""%></td>
                                                <% if (funcionario.getEsAdministrador()) {%>
                                                <td><%=asignada.getNombreCompleto()%></td>
                                                <% } %>
                                            </tr>
                                            <% }%>
                                            <% if (solicitudes.isEmpty()) { %>
                                            <tr>
                                                <td colspan="12" align="center">Sin resultados</td>
                                            </tr>
                                            <% } %>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- /.container-fluid -->

                </div>
                <!-- End of Main Content -->
                <jsp:include page="../includes/footer.jsp" flush="true" />
            </div>
            <!-- End of Content Wrapper -->
        </div>
        <!-- End of Page Wrapper -->
        <jsp:include page="../includes/scripts.jsp" flush="true" />
    </body>
</html>

