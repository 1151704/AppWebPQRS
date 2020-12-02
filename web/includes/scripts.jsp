<%@page import="dto.Mensaje"%>
<script src="<%=request.getContextPath()%>/static/js/jquery-3.5.1.min.js"></script>
<script src="<%=request.getContextPath()%>/static/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="<%=request.getContextPath()%>/static/sb-admin-2/sb-admin-2.min.js"></script>
<script src="<%=request.getContextPath()%>/static/js/scripts.js"></script>

<% if (session.getAttribute("mensaje") != null && session.getAttribute("mensaje") instanceof Mensaje) {%>
<%
    Mensaje mensaje = (Mensaje) session.getAttribute("mensaje");
%>
<div class="notifications">
    <div class="notification <%=mensaje.getTipo().className%>" >
        <div class="title">
            <div><%=mensaje.getTitulo()%></div>
            <button type="button" class="notificacion-close" onclick="$('.notifications').fadeOut('show');">
                &times;
            </button>
        </div>
        <div class="message"><%=mensaje.getMensaje()%></div>
    </div>
</div>
<% session.setAttribute("mensaje", null); %>
<%}%>