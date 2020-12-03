package servlet;

import dto.ArchivoDto;
import dto.FuncionarioDto;
import dto.Mensaje;
import dto.SolicitudArchivosDto;
import dto.SolicitudDto;
import dto.TipoMensaje;
import dto.UsuarioDto;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import service.postgres.Service;
import util.ArchivoSubir;
import util.Utilidades;

public class PqrsRegistar extends HttpServlet {

    private HttpSession session;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean isMultipartContent = ServletFileUpload.isMultipartContent(req);
        if (!isMultipartContent) {
            return;
        }

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            List<FileItem> fields = upload.parseRequest(req);
            Iterator<FileItem> it = fields.iterator();

            Integer idTipoIdentificacion = null;
            Integer idTipoUsuario = null;
            Integer idTipoSolicitud = null;
            Integer idMotivo = null;
            String identificacion = null;
            Integer codigoInterno = null;
            String nombre1 = null;
            String nombre2 = null;
            String apellido1 = null;
            String apellido2 = null;

            String celular = null;
            String telefono = null;
            String direccion = null;
            String correo = null;

            String descripcion = null;
            FileItem archivo = null;

            while (it.hasNext()) {
                FileItem fileItem = it.next();
                boolean isFormField = fileItem.isFormField();
                if (isFormField) {
                    switch (fileItem.getFieldName()) {
                        case "tipo_usuario":
                            idTipoUsuario = Utilidades.validateInputNumber(fileItem.getString());
                            break;
                        case "tipo_identificacion":
                            idTipoIdentificacion = Utilidades.validateInputNumber(fileItem.getString());
                            break;
                        case "codigo_interno":
                            codigoInterno = Utilidades.validateInputNumber(fileItem.getString());
                            break;
                        case "tipo_solicitud":
                            idTipoSolicitud = Utilidades.validateInputNumber(fileItem.getString());
                            break;
                        case "motivo_solicitud":
                            idMotivo = Utilidades.validateInputNumber(fileItem.getString());
                            break;
                        case "celular":
                            celular = Utilidades.validateInputText(fileItem.getString());
                            break;
                        case "correo":
                            correo = Utilidades.validateInputText(fileItem.getString());
                            break;
                        case "direccion":
                            direccion = Utilidades.validateInputText(fileItem.getString());
                            break;
                        case "telefono":
                            telefono = Utilidades.validateInputText(fileItem.getString());
                            break;
                        case "descripcion":
                            descripcion = Utilidades.validateInputText(fileItem.getString());
                            break;
                        case "primer_nombre":
                            nombre1 = Utilidades.validateInputText(fileItem.getString());
                            break;
                        case "segundo_nombre":
                            nombre2 = Utilidades.validateInputText(fileItem.getString());
                            break;
                        case "primer_apellido":
                            apellido1 = Utilidades.validateInputText(fileItem.getString());
                            break;
                        case "segundo_apellido":
                            apellido2 = Utilidades.validateInputText(fileItem.getString());
                            break;
                        case "identificacion":
                            identificacion = Utilidades.validateInputText(fileItem.getString());
                            break;
                    }
                } else {
                    archivo = fileItem;
                }
            }

            if (apellido1 == null || nombre1 == null || correo == null || idTipoUsuario == null || idTipoIdentificacion == null || idTipoSolicitud == null) {
                session.setAttribute("mensaje", new Mensaje("Datos incompletos", "Debe ingresar todos los campos con *.", TipoMensaje.ERROR));
                resp.sendRedirect(req.getContextPath() + "/registrar.jsp");
                return;
            }

            session = req.getSession();
            Service controlador = (Service) session.getAttribute("controlador");

            SolicitudDto solicitud = new SolicitudDto();

            FuncionarioDto funcionario = controlador.serviceFuncionario().buscarDisponible();

            if (funcionario == null) {
                session.setAttribute("mensaje", new Mensaje("Sin funcionarios", "En este momento no se encuentra ningún funcionario laborando, porfavor intentelo despues.", TipoMensaje.WARNING));
                resp.sendRedirect(req.getContextPath() + "/registrar.jsp");
                return;
            }

            Integer idUsuario;

            UsuarioDto usuario = controlador.serviceUsuario().buscarPorIdentificacion(identificacion);
            if (usuario != null) {
                idUsuario = usuario.getId();
            } else {
                usuario = new UsuarioDto();
                usuario.setCelular(celular);
                usuario.setCodigoInterno(codigoInterno);
                usuario.setCorreo(correo);
                usuario.setDireccion(direccion);
                usuario.setFechaRegistro(new Date());
                usuario.setFkMunicipio(1081); // cucuta
                usuario.setFkTipoIdentificacion(idTipoIdentificacion);
                usuario.setFkTipoUsuario(idTipoUsuario);
                usuario.setIdentificacion(identificacion);
                usuario.setPrimerApellido(apellido1);
                usuario.setPrimerNombre(nombre1);
                usuario.setSegundoApellido(apellido2 != null ? apellido2 : "");
                usuario.setSegundoNombre(nombre2 != null ? nombre2 : "");
                usuario.setTelefonoFijo(telefono);
                usuario = controlador.serviceUsuario().guardar(usuario);
                if (usuario != null) {
                    idUsuario = usuario.getId();
                } else {
                    resp.sendRedirect(req.getContextPath() + "/index.jsp");
                    return;
                }
            }

            solicitud.setDescripcion(descripcion);
            solicitud.setFechaRegistro(new Date());
            solicitud.setFkFuncionario(funcionario.getId());
            solicitud.setFkMotivoSolicitud(idMotivo);
            solicitud.setFkUsuario(idUsuario);
            solicitud.setOtroMotivo("");
            solicitud.setRespondida(false);

            solicitud = controlador.serviceSolicitud().guardar(solicitud);

            if (solicitud != null) {
                if (archivo != null) {
                    ArchivoDto archivoNew = new ArchivoSubir().subirServidor(archivo);
                    if (archivoNew != null) {
                        SolicitudArchivosDto solicitudArchivo = new SolicitudArchivosDto();
                        solicitudArchivo.setFkArchivo(archivoNew.getId());
                        solicitudArchivo.setFkSolicitud(solicitud.getId());
                        solicitudArchivo.setEsRespuesta(false);
                        controlador.serviceSolicitudArchivos().guardar(solicitudArchivo);
                    }
                }
                session.setAttribute("mensaje", new Mensaje("Realizado", "Se ha registrado la solicitud correctamente, el número de radicado es: " + solicitud.getId(), TipoMensaje.SUCCESS));
            } else {
                session.setAttribute("mensaje", new Mensaje("Formulario invalido", "No se pudo registrar la PQRS, verifique la información ingresada e intente de nuevo", TipoMensaje.WARNING));
            }

            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

    }

}
