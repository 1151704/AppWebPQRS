package util;

import dao.IArchivoDao;
import dao.postgres.ArchivoDao;
import dto.ArchivoDto;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.net.ftp.FTPClient;

public class ArchivoSubir implements Serializable {

    public ArchivoDto subirServidor(FileItem archivo) {

        Calendar fechaActual = Calendar.getInstance();
        fechaActual.setTime(new Date());
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyyMMdd");

        ConexionFTP clienteFTP = new ConexionFTP();

        IArchivoDao archivoDAO = new ArchivoDao();

        ArchivoDto archivoNew = null;

        FTPClient ftpClient = ConexionFTP.ConexionFTP();
        if (ftpClient != null && archivo != null) {
            InputStream inputStream = null;
            try {

                long idArchivo;
                long idArchivoNew;
                String extencionArchivo;
                int idMineTypeSeleccionado;

                boolean existe = false;
                idMineTypeSeleccionado = 0;
                idArchivo = 0;
                extencionArchivo = "";

                // Crear el nombre del archivo en el servidor
                String fecha = formatoFecha.format(fechaActual.getTime()); // fecha subida
                String numeroAleatorio = CaracteresAleatorios.getCodigoNumerico(8); // numero aleatorio de longitud 8

                String nombreServidor = fecha + idArchivo + numeroAleatorio + extencionArchivo; // contatenando el nombre

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
                    boolean subirServidor = ftpClient.storeFile(nombreServidor, inputStream); //Ruta completa de alojamiento en el FTP

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
        }
        return archivoNew;
    }
}
