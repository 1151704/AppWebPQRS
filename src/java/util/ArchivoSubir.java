package util;

import dao.IArchivoDao;
import dao.postgres.ArchivoDao;
import dto.ArchivoDto;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.net.ftp.FTPClient;

public class ArchivoSubir extends ConexionFtp implements Serializable {

    public ArchivoDto subirServidor(FileItem archivo) {

        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyyMMddHHmmss");

        IArchivoDao archivoDAO = new ArchivoDao();

        ArchivoDto archivoNew = null;

        FTPClient ftpClient = this.conFtp.getClienteFTP();
        if (ftpClient != null && archivo != null) {
            InputStream inputStream = null;
            try {

                String extencionArchivo;
                int indice_extecion = archivo.getName().lastIndexOf('.');
                if (indice_extecion != -1) {
                    extencionArchivo = archivo.getName().substring(indice_extecion).toLowerCase();
                } else {
                    return null;
                }
                // Crear el nombre del archivo en el servidor
                String fecha = formatoFecha.format(new Date()); // fecha subida
                String numeroAleatorio = CaracteresAleatorios.getCodigoNumerico(4); // numero aleatorio de longitud 8

                String nombreServidor = fecha + "_" + numeroAleatorio + extencionArchivo; // contatenando el nombre

                archivoNew = new ArchivoDto();

                // nombre del archivo para el usuario
                String nombreArchivo = archivo.getName();
                archivoNew.setFechaRegistro(new Date());
                archivoNew.setNombreEnServidor(nombreServidor);
                archivoNew.setNombrePorUsuario(nombreArchivo);

                // registrar en la base de datos
                archivoNew = archivoDAO.guardar(archivoNew);
                if (archivoNew != null) {

                    inputStream = archivo.getInputStream();

                    // subir el archivo al servidor 
                    boolean subirServidor = ftpClient.storeFile(nombreServidor, inputStream);

                    if (!subirServidor) {
                        if (archivoDAO.eliminarPorId(archivoNew.getId())) {
                            archivoNew = null;
                        }
                    }
                }

            } catch (IOException ex) {
                Logger.getLogger(ArchivoSubir.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException ex) {
                        Logger.getLogger(ArchivoSubir.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                try {
                    ftpClient.logout();
                    ftpClient.disconnect();
                } catch (IOException ex) {
                    Logger.getLogger(ArchivoSubir.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            System.out.println("----> 0");
        }
        return archivoNew;
    }
}
