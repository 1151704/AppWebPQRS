package util;

public abstract class ConexionFtp {

    protected ConexionFtpAdmin conFtp = null;

    public ConexionFtp() {
        conFtp = ConexionFtpAdmin.getInstance();
    }

}
