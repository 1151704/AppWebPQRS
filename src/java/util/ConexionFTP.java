package util;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class ConexionFTP implements java.io.Serializable {

    public static FTPClient ConexionFTP() {
        FTPClient clienteFTP = null;

        try {
            ResourceBundle rb;
            String ARCHIVO_CONFIGURACION = "resources.parametros";
            rb = ResourceBundle.getBundle(ARCHIVO_CONFIGURACION);

            clienteFTP = new FTPClient();

            int puerto = Integer.parseInt(rb.getString("FTP_PORT"));
            String serve = rb.getString("FTP_HOST");
            String user = rb.getString("FTP_USER");
            String password = rb.getString("FTP_PASSWORD");
            String raiz = rb.getString("FTP_RAIZ");

            if (puerto <= 0) {
                clienteFTP.connect(serve);
            } else {
                clienteFTP.connect(serve, puerto);
            }
            clienteFTP.login(user, password);

            if (FTPReply.isPositiveCompletion(clienteFTP.getReplyCode())) {
                if (raiz != null && raiz.length() > 0) {
                    clienteFTP.changeWorkingDirectory(raiz);
                }
                clienteFTP.setFileType(FTP.BINARY_FILE_TYPE);
                clienteFTP.enterLocalPassiveMode();

            } else {
                clienteFTP = null;
            }

        } catch (IOException ex) {
            Logger.getLogger(ConexionFTP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clienteFTP;
    }
}
