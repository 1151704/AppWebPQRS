<%@page import="dto.TipoIdentificacionDto"%>
<%@page import="java.util.List"%>
<%@page import="dto.FuncionarioDto"%>
<%@page import="dto.MotivoSolicitudDto"%>
<%@page import="dto.UsuarioDto"%>
<%@page import="dto.SolicitudDto"%>
<%@page import="util.Utilidades"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="controlador" scope="session" class="service.postgres.Service" />
<%
    Integer id_func = Utilidades.validateInputNumber(request.getParameter("id"));

    if (id_func == null) {
        out.print("<script>window.location.href = '" + request.getContextPath() + "/main/funcionarios.jsp';</script>");
    }

    FuncionarioDto funcionario = controlador.serviceFuncionario().buscarPorId(id_func);

    if (funcionario == null) {
        out.print("<script>window.location.href = '" + request.getContextPath() + "/main/funcionarios.jsp';</script>");
    } else {
        List<TipoIdentificacionDto> tiposIds = controlador.serviceTipoIdentificacion().listarTodos();
%>
<!doctype html>
<html lang="es">
    <head>
        <%@include file="../includes/head.jsp" %>
        <title>Funcionario nuevo - PQRS</title>
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

                                <h6 class="m-0 font-weight-bold text-primary"><a class="btn btn-sm btn-outline-dark mr-1" href="funcionarios.jsp"><i class="fas fa-arrow-circle-left"></i> Volver</a>Registrar Funcionario</h6>
                            </div>
                            <div class="card-body">
                                <form action="<%=request.getContextPath()%>/actualizar-funcionario" method="post">
                                    <input type="hidden" name="id" value="<%=funcionario.getId()%>" >
                                    <div class="form-group">
                                        <div class="form-row">
                                            <div class="col-lg-2 d-flex align-items-center">
                                                <div class="form-check">
                                                    <input type="checkbox" <%=funcionario.getEsAdministrador() ? "checked" : ""%>  class="form-check-input" id="es_administrador" name="es_administrador" >
                                                    <label class="form-check-label" for="es_administrador">Es Administrador</label>
                                                </div>
                                            </div>
                                            <div class="col-lg-3">
                                                <div class="form-group">
                                                    <label>Tipo de Identificación</label>
                                                    <select class="form-control" name="tipo_id" disabled="true">
                                                        <option value="">Seleccione ..</option>
                                                        <% for (TipoIdentificacionDto tipoId : tiposIds) {%>
                                                        <option value="<%=tipoId.getId()%>" selected="<%=tipoId.getId().equals(funcionario.getFkTipoIdentificacion())%>"><%=tipoId.getDescripcion()%></option>
                                                        <% }%>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-lg-7">
                                                <div class="form-group">
                                                    <label>Identificación</label>
                                                    <input type="text" class="form-control" disabled="true" value="<%=funcionario.getIdentificacion()%>" name="identificacion" required="true">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-row">
                                            <div class="col-lg-4">
                                                <div class="form-group">
                                                    <label>Código</label>
                                                    <input type="number" class="form-control" value="<%=funcionario.getCodigoInterno()%>" name="codigo_interno" required="true">
                                                </div>
                                            </div>
                                            <div class="col-lg-8">
                                                <div class="form-group">
                                                    <label>Nombre Completo</label>
                                                    <input type="text" class="form-control" value="<%=funcionario.getNombreCompleto()%>" name="nombre_completo" required="true">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-row">
                                            <div class="col-lg-4">
                                                <div class="form-group">
                                                    <label>Celular</label>
                                                    <input type="text" class="form-control" value="<%=funcionario.getCelular()%>" name="celular">
                                                </div>
                                            </div>
                                            <div class="col-lg-4">
                                                <div class="form-group">
                                                    <label>Correo</label>
                                                    <input type="text" class="form-control" value="<%=funcionario.getCorreo()%>" name="correo">
                                                </div>
                                            </div>
                                            <div class="col-lg-4">
                                                <div class="form-group">
                                                    <label>Cargo</label>
                                                    <input type="text" class="form-control" value="<%=funcionario.getCargo()%>" name="cargo">
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-row">
                                        <div class="col-md-12 d-flex justify-content-center">
                                            <button class="btn btn-success m-1" type="submit">Guardar</button>
                                            <a class="btn btn-danger m-1" href="funcionarios.jsp">Cancelar</a>
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