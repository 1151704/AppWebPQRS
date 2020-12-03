package util;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTPClient;

public class Pruebas {

    public void test1() {

        FTPClient ftpClient;
        try {
            ftpClient = ConexionFTP.ConexionFTP();
        } catch (Exception ex) {
            Logger.getLogger(Pruebas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {
        Pruebas p = new Pruebas();

        p.test1();
    }
}
