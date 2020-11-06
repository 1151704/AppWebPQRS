package util;

public abstract class ConexionBD {

    protected ConexionPrincipal conPrincipal = null;

    public ConexionBD() {
        conPrincipal = ConexionPrincipal.getInstance();
    }
}
