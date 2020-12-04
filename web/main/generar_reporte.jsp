<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
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

    List<FuncionarioDto> funcionarios = controlador.serviceFuncionario().listarTodos();
    FuncionarioDto funcionario = (FuncionarioDto) session.getAttribute("usuarioActual");

    String fechaActual = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

%>
<!doctype html>
<html lang="es">
    <head>
        <jsp:include page="../includes/head.jsp" flush="true" />
        <title>Generar Reporte - PQRS</title>
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

                                <h6 class="m-0 font-weight-bold text-primary"><a class="btn btn-sm btn-outline-dark mr-1" href="tipos_solicitudes.jsp"><i class="fas fa-arrow-circle-left"></i> Volver</a>Generar Reporte</h6>
                            </div>
                            <div class="card-body">
                                <form action="<%=request.getContextPath()%>/generar-reporte" method="get" target="_blanck">
                                    <div class="form-group">
                                        <div class="form-row">                                            

                                            <div class="col-lg-3">
                                                <div class="form-group">
                                                    <label>Estado de PQRS</label>
                                                    <select class="form-control" name="estado">
                                                        <option value="0">Todos</option>
                                                        <option value="2">Pendientes</option>
                                                        <option value="1">Respondidas</option>
                                                    </select>
                                                </div>
                                            </div>

                                            <% if (funcionario.getEsAdministrador()) { %>
                                            <div class="col-lg-3">
                                                <div class="form-group">
                                                    <label>Funcionario</label>
                                                    <select class="form-control" name="id_funcionario">
                                                        <option value="0">Todos</option>
                                                        <% for (FuncionarioDto tipoId : funcionarios) {%>
                                                        <option value="<%=tipoId.getId()%>" ><%=tipoId.getNombreCompleto()%></option>
                                                        <% }%>
                                                    </select>
                                                </div>
                                            </div>
                                            <%}%>
                                            <div class="col-lg-3">
                                                <div class="form-group">
                                                    <label>Fecha Inicial *</label>
                                                    <input type="date" class="form-control" value="<%=fechaActual%>" name="fecha_inicial" required="true">
                                                </div>
                                            </div>
                                            <div class="col-lg-3">
                                                <div class="form-group">
                                                    <label>Fecha Final *</label>
                                                    <input type="date" class="form-control" value="<%=fechaActual%>" name="fecha_final" required="true">
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-row">
                                        <div class="col-md-12 d-flex justify-content-center">
                                            <button class="btn btn-success m-1" type="submit">Generar</button>
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
        <jsp:include page="../includes/scripts.jsp" flush="true" />
    </body>
</html>
