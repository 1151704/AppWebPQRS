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
        <jsp:include page="../includes/head.jsp" flush="true" />
        <title>Funcionario - PQRS</title>
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

                                <h6 class="m-0 font-weight-bold text-primary"><a class="btn btn-sm btn-outline-dark mr-1" href="funcionarios.jsp"><i class="fas fa-arrow-circle-left"></i> Volver</a>Actualizar Funcionario</h6>
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
                                        <div class="form-row">                                            
                                            <div class="col-lg-2 d-flex align-items-center">
                                                <div class="form-check">
                                                    <input type="checkbox"  class="form-check-input" id="change_password" name="change_password" >
                                                    <label class="form-check-label" for="change_password">Cambiar contraseña</label>
                                                </div>
                                            </div>
                                            <div class="col-lg-5">
                                                <div class="form-group">
                                                    <label>Contraseña nueva</label>
                                                    <input type="password" class="form-control" value="" name="password">
                                                </div>
                                            </div>
                                            <div class="col-lg-5">
                                                <div class="form-group">
                                                    <label>Confirmar contraseña</label>
                                                    <input type="password" class="form-control" value="" name="confirm_password">
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
                <jsp:include page="../includes/footer.jsp" flush="true" />
            </div>
            <!-- End of Content Wrapper -->
        </div>
        <!-- End of Page Wrapper -->
        <jsp:include page="../includes/scripts.jsp" flush="true" />
    </body>
</html>
<% }%>