<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="../includes/verificarAcceso.jsp" flush="true"/>
<!doctype html>
<html lang="es">
    <head>
        <jsp:include page="../includes/head.jsp" flush="true" />
        <title>Inicio - PQRS</title>
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
                        <h1>Inicio</h1>
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

