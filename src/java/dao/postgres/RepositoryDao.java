package dao.postgres;

import java.beans.Expression;
import java.beans.Statement;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;
import util.ConexionBD;
import dao.IRepositoryDao;
import java.lang.reflect.ParameterizedType;
import util.Column;
import util.Table;

public class RepositoryDao<T extends Serializable, ID> extends ConexionBD implements IRepositoryDao<T, ID> {

    private final String tableName;
    private final String idColumnName;
    private final Class<T> type;

    public RepositoryDao() {
        this.type = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        if (!type.isAnnotationPresent(Table.class)) {
            throw new IllegalArgumentException(type.getName() + ". debe contener la anotación: " + Table.class.getName());
        }
        this.tableName = type.getAnnotation(Table.class).name();
        this.idColumnName = type.getAnnotation(Table.class).nameFieldId();
    }

    public Class<T> getType() {
        return type;
    }

    public T newInstance() throws IllegalAccessException, InstantiationException {
        return type.newInstance();
    }

    @Override
    public List<T> listAll() {
        String query = String.format("select * from %s order by %s desc", tableName, idColumnName);
        return listDtoByQuery(query);
    }

    @Override
    public T save(T entity) {
        Field[] fieldsInit = type.getDeclaredFields();

        String columnName = "";
        String values = "";
        List parameters = new ArrayList();
        String name;
        Expression stmt;

        int index = 0;
        ID identificador = null;
        Field[] fields = new Field[fieldsInit.length - 1];
        for (Field field : fieldsInit) {
            name = getNameColumn(field);
            if (!name.equals(idColumnName)) {
                fields[index++] = field;
            } else {
                try {
                    stmt = new Expression(entity, "get" + this.getFieldName(name), new Object[]{});
                    identificador = (ID) stmt.getValue();
                } catch (Exception ex) {
                    Logger.getLogger(RepositoryDao.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        for (int i = 0; i < fields.length; i++) {
            name = fields[i].getName();

            if (!name.equals(idColumnName)) {
                columnName += getNameColumn(fields[i]);
                values += '?';

                try {
                    stmt = new Expression(entity, "get" + this.getFieldName(name), new Object[]{});
                    parameters.add(stmt.getValue());
                } catch (Exception ex) {
                    Logger.getLogger(RepositoryDao.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (i + 1 < fields.length) {
                    columnName += ',';
                    values += ',';
                }
            }

        }
        String query;
        List<Object[]> results;
        if (identificador == null) {
            query = String.format("insert into %s (%s) values (%s) RETURNING %s", tableName, columnName, values, idColumnName);
            results = listByQuery(query, parameters.toArray());
        } else {
            parameters.add(identificador);
            query = String.format("update %s set (%s) = (%s) where %s = ? RETURNING %s", tableName, columnName, values, idColumnName, idColumnName);
            results = listByQuery(query, parameters.toArray());
        }

        if (results.isEmpty()) {
            return null;
        }

        for (Object object : results.get(0)) {
            return getById((ID) object);
        }
        return null;

    }

    @Override
    public boolean deleteById(ID id) {
        String query = String.format("delete from %s where %s = ?", tableName, idColumnName);
        return execute(query, id) == 1;
    }

    @Override
    public T getById(ID id) {
        String query = String.format("select * from %s where %s = ?", tableName, idColumnName);
        List<T> list = listDtoByQuery(query, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<T> listDtoByQuery(String query, Object... args) {

        List<T> list = new ArrayList();
        T item;

        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = conPrincipal.getConnection();
            pst = con.prepareStatement(query);

            for (int i = 0; i < args.length; i++) {
                pst.setObject(i + 1, args[i]);
            }
            rs = pst.executeQuery();

            Statement stmt;
            String name;
            while (rs.next()) {
                item = newInstance();

                for (Field field : item.getClass().getDeclaredFields()) {
                    name = field.getName();

                    stmt = new Statement(item, "set" + this.getFieldName(name), new Object[]{rs.getObject(this.getNameColumn(field))});
                    stmt.execute();

                }

                list.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(RepositoryDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RepositoryDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(RepositoryDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;

    }

    @Override
    public List<Object[]> listByQuery(String query, Object... args) {
        List<Object[]> list = new ArrayList();
        Object[] item;

        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection con = null;

        try {
            con = conPrincipal.getConnection();
            pst = con.prepareStatement(query);

            for (int i = 0; i < args.length; i++) {
                pst.setObject(i + 1, args[i]);
            }
            rs = pst.executeQuery();

            String name;

            ResultSetMetaData rsMetaData = rs.getMetaData();
            int count = rsMetaData.getColumnCount();

            while (rs.next()) {
                item = new Object[count];

                for (int i = 1; i <= count; i++) {
                    name = rsMetaData.getColumnName(i);

                    item[i - 1] = rs.getObject(name);

                }

                list.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RepositoryDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(RepositoryDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;

    }

    @Override
    public int execute(String query, Object... args) {

        int rows = -1;

        PreparedStatement pst = null;
        Connection con = null;

        try {
            con = conPrincipal.getConnection();
            pst = con.prepareStatement(query);

            for (int i = 0; i < args.length; i++) {
                pst.setObject(i + 1, args[i]);
            }

            rows = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RepositoryDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RepositoryDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(RepositoryDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rows;

    }

    private String getFieldName(String name) {
        return StringUtils.capitalize(name);
    }

    private String getNameColumn(Field field) {

        if (field.isAnnotationPresent(Column.class)) {
            Column column = field.getAnnotation(Column.class);
            return column.name();
        }

        return field.getName();
    }

    public String getIdColumnName() {
        return idColumnName;
    }

    public String getTableName() {
        return tableName;
    }

}
