package dao;

import java.io.Serializable;
import java.util.List;

public interface IRepositoryDao<T extends Serializable, ID> {

    public List<T> listAll();

    public T save(T entity);

    public boolean deleteById(ID id);

    public T getById(ID id);

    public List<T> listDtoByQuery(String query, Object... args);

    public List<Object[]> listByQuery(String query, Object... args);

    public int execute(String query, Object... args);

}
