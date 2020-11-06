/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author OMAR
 * @param <T>
 * @param <ID>
 */
public interface IGenericDao<T extends Serializable, ID> {

    public List<T> listAll();

    public T save(T entity);

    public boolean deleteById(ID id);

    public T getById(ID id);

    public List<T> listDtoByQuery(String query, Object... args);

    public List<Object[]> listByQuery(String query, Object... args);

    public int execute(String query, Object... args);

}
