<!-- Sidebar -->
<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

    <!-- Sidebar - Brand -->
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="<%=request.getContextPath()%>/main/index.jsp">
        <div class="sidebar-brand-icon rotate-n-15">
            <i class="fas fa-laugh-wink"></i>
        </div>
        <div class="sidebar-brand-text mx-3">PQRS-UFPS</div>
    </a>

    <!-- Divider -->
    <hr class="sidebar-divider my-0">

    <!-- Nav Item - Dashboard -->
    <li class="nav-item active">
        <a class="nav-link" href="<%=request.getContextPath()%>/main/index.jsp">
            <i class="fas fa-fw fa-tachometer-alt"></i>
            <span>Inicio</span></a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider">
    
    <li class="nav-item active">
        <a class="nav-link" href="<%=request.getContextPath()%>/main/pqrs.jsp">
            <i class="fas fa-fw fa-tachometer-alt"></i>
            <span>PQRS</span></a>
    </li>
    
    <li class="nav-item active">
        <a class="nav-link" href="<%=request.getContextPath()%>/main/funcionarios.jsp">
            <i class="fas fa-fw fa-user"></i>
            <span>Funcionarios</span></a>
    </li>
    
    <li class="nav-item active">
        <a class="nav-link" href="<%=request.getContextPath()%>/main/tipos_solicitudes.jsp">
            <i class="fas fa-fw fa-user"></i>
            <span>Tipos de Solicitudes</span></a>
    </li>
    
    <li class="nav-item active">
        <a class="nav-link" href="<%=request.getContextPath()%>/main/tipos_usuarios.jsp">
            <i class="fas fa-fw fa-user"></i>
            <span>Tipos de Usuarios</span></a>
    </li>

    <!-- Divider -->
    <hr class="sidebar-divider d-none d-md-block">

    <!-- Sidebar Toggler (Sidebar) -->
    <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
    </div>

</ul>
<!-- End of Sidebar -->