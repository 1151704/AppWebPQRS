package util;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class ConexionFtpAdmin implements java.io.Serializable {

    private static ConexionFtpAdmin INSTANCE;
    private FTPClient clienteFTP;

    private ConexionFtpAdmin() {
        try {
            ResourceBundle rb = ResourceBundle.getBundle("resources.parametros");

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
            }

        } catch (IOException ex) {
            Logger.getLogger(ConexionFtpAdmin.class.getName()).log(Level.SEVERE, null, ex);
            clienteFTP = null;
        }
    }

    public static ConexionFtpAdmin getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ConexionFtpAdmin();
        }
        return INSTANCE;
    }

    public FTPClient getClienteFTP() {
        return clienteFTP;
    }

}
