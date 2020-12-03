package util;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class ConexionDbAdmin {

    private static ConexionDbAdmin INSTANCE;
    private final BasicDataSource basicDataSource;
    private final DataSource dataSource;
    private static final Parametros PARAMETROS = new Parametros();

    private ConexionDbAdmin() {
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

    public static ConexionDbAdmin getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ConexionDbAdmin();
        }
        return INSTANCE;
    }

    public Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }

}
