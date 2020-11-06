package util;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class ConexionPrincipal {

    private static ConexionPrincipal INSTANCE;
    private final BasicDataSource basicDataSource;
    private final DataSource dataSource;
    private static final Parametros PARAMETROS = new Parametros();

    public static final String PORT = PARAMETROS.getPORT();
    public static final String HOST = PARAMETROS.getHOST();
    public static final String DATABASE = PARAMETROS.getDATABASE();
    public static final String USER = PARAMETROS.getUSER();
    public static final String PASSWORD = PARAMETROS.getPASSWORD();
    public static final String DRIVER = PARAMETROS.getDRIVER();
    public static final String URL = PARAMETROS.getURL();

    private ConexionPrincipal() {
        basicDataSource = new BasicDataSource();

        basicDataSource.setUsername(USER);
        basicDataSource.setPassword(PASSWORD);
        basicDataSource.setUrl(URL + "://" + HOST + ":" + PORT + "/" + DATABASE);

        basicDataSource.setDriverClassName(DRIVER);
        basicDataSource.setMaxTotal(-1);
        basicDataSource.setMaxIdle(-1);
        basicDataSource.setTimeBetweenEvictionRunsMillis(10000);
        basicDataSource.setRemoveAbandonedOnBorrow(true);
        basicDataSource.setRemoveAbandonedOnMaintenance(true);
        basicDataSource.setRemoveAbandonedTimeout(5000);
        basicDataSource.setMaxWaitMillis(7000);

        dataSource = basicDataSource;
    }

    public static ConexionPrincipal getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ConexionPrincipal();
        }
        return INSTANCE;
    }

    public String getDataBase() {
        return DATABASE;
    }

    public Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }

}
