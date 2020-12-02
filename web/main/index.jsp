<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../includes/verificarAcceso.jsp" flush="true"/>
<!doctype html>
<html lang="es">
    <head>
        <%@include file="../includes/head.jsp" %>
        <title>Inicio - PQRS</title>
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
                        <h1>Inicio</h1>
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

