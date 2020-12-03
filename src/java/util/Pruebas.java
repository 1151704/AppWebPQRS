package util;

import java.io.IOException;
import java.util.Arrays;
import org.apache.commons.net.ftp.FTPClient;

public class Pruebas extends ConexionFtp {

    public void test1() throws IOException {
        FTPClient client = conFtp.getClienteFTP();
        
        if (client != null) {
            
            System.out.println(Arrays.toString(client.listNames()));
            
        }
        
    }

    public static void main(String[] args) throws IOException {
        Pruebas p = new Pruebas();

        p.test1();
    }
}
