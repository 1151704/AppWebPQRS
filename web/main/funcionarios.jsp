<%@page import="dto.*"%>
<%@page import="util.Utilidades"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="controlador" scope="session" class="service.postgres.Service" />
<%
    List<FuncionarioDto> funcionarios = controlador.serviceFuncionario().listarTodos();
%>
<!doctype html>
<html lang="es">
    <head>
        <%@include file="../includes/head.jsp" %>
        <title>Funcionarios - PQRS</title>
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
                                <h6 class="m-0 font-weight-bold text-primary">Listado de Funcionarios</h6>
                            </div>
                            <div class="card-body">
                                
                                <a class="btn btn-success mb-3" title="Registrar" href="registrar_funcionario.jsp">
                                    <i class="fas fa-plus-circle"></i> Registrar
                                </a>
                                
                                <div class="table-responsive">
                                    <table class="table table-sm table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th>Acción</th>
                                                <th>Identificación de funcionario</th>
                                                <th>Código</th>
                                                <th>Nombre</th>
                                                <th>Cargo</th>
                                                <th>Celular</th>
                                                <th>Correo</th>
                                                <th>Es admin</th>
                                                <th>Fecha de registro</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                for (FuncionarioDto usuario : funcionarios) {
                                            %>
                                            <tr>
                                                <td>
                                                    <div class="d-flex justify-content-center align-content-center">
                                                        <a title="Detalle" class="btn btn-sm btn-outline-info m-1" href="actualizar_funcionario.jsp?id=<%=usuario.getId() %>"  ><i class="fas fa-info-circle"></i></a>
                                                    </div>
                                                </td>
                                                <td><%=usuario.getTipoIdentificacion().getAbreviatura() + " " + usuario.getIdentificacion()%></td>
                                                <td><%=usuario.getCodigoInterno()%></td>
                                                <td><%=usuario.getNombreCompleto()%></td>
                                                <td><%=usuario.getCargo() %></td>
                                                <td><%=usuario.getCelular() %></td>
                                                <td><%=usuario.getCorreo() %></td>
                                                <td><%=usuario.getEsAdministrador()? "Si": "No" %></td>
                                                <td><%=Utilidades.formatDate(usuario.getFechaRegistro(), "yyyy-MM-dd HH:mm")%></td>
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

