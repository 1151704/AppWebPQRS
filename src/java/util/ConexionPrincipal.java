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

    private ConexionPrincipal() {
        basicDataSource = new BasicDataSource();

        basicDataSource.setUsername(PARAMETROS.getUSER());
        basicDataSource.setPassword(PARAMETROS.getPASSWORD());
        basicDataSource.setUrl(PARAMETROS.getURL() + "://" + PARAMETROS.getHOST() + ":" + PARAMETROS.getPORT() + "/" + PARAMETROS.getDATABASE());

        basicDataSource.setDriverClassName(PARAMETROS.getDRIVER());
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

    public Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }

}
