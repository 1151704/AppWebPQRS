package util;

public abstract class ConexionDb {

    protected ConexionDbAdmin conDb = null;

    public ConexionDb() {
        conDb = ConexionDbAdmin.getInstance();
    }
}
