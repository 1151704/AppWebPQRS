<%@page import="dto.TipoUsuarioDto"%>
<%@page import="dto.TipoSolicitudDto"%>
<%@page import="dto.TipoIdentificacionDto"%>
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
    Integer id_tipo = Utilidades.validateInputNumber(request.getParameter("id"));

    if (id_tipo == null) {
        out.print("<script>window.location.href = '" + request.getContextPath() + "/main/tipos_usuarios.jsp';</script>");
    }

    TipoUsuarioDto tipoSolicitud = controlador.serviceTipoUsuario().buscarPorId(id_tipo);

    if (tipoSolicitud == null) {
        out.print("<script>window.location.href = '" + request.getContextPath() + "/main/tipos_usuarios.jsp';</script>");
    } else {

%>
<!doctype html>
<html lang="es">
    <head>
        <%@include file="../includes/head.jsp" %>
        <title>Tipo Usuario - PQRS</title>
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

                                <h6 class="m-0 font-weight-bold text-primary"><a class="btn btn-sm btn-outline-dark mr-1" href="tipos_usuarios.jsp"><i class="fas fa-arrow-circle-left"></i> Volver</a>Actualizar Tipo de Usuario</h6>
                            </div>
                            <div class="card-body">
                                <form action="<%=request.getContextPath()%>/actualizar-tipo-usuario" method="post">
                                    <input name="id" value="<%=tipoSolicitud.getId()%>" type="hidden" >
                                    <div class="form-group">
                                        <div class="form-row">
                                            <div class="col-lg-2 d-flex align-items-center">
                                                <div class="form-check">
                                                    <input type="checkbox" <%=tipoSolicitud.getHabilitado() ? "checked" : ""%> class="form-check-input" id="habilitado" name="habilitado" >
                                                    <label class="form-check-label" for="habilitado">Habilitado</label>
                                                </div>
                                            </div>
                                            <div class="col-lg-7">
                                                <div class="form-group">
                                                    <label>Descripción *</label>
                                                    <input type="text" value="<%=tipoSolicitud.getDescripcion()%>" class="form-control" name="descripcion" required="true">
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-row">
                                        <div class="col-md-12 d-flex justify-content-center">
                                            <button class="btn btn-success m-1" type="submit">Guardar</button>
                                            <a class="btn btn-danger m-1" href="tipos_usuarios.jsp">Cancelar</a>
                                        </div>
                                    </div>
                                </form>
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
