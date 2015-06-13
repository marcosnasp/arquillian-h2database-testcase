package org.example.interfaces;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

/**
 * TODO: Documentar essa interface
 * 
 * @author Marcos
 *
 * @param <T>
 */
@Local
public interface CrudService<T> {

    public T create(T t);

    public T find(Class<T> type, Object id);

    public T update(T t);

    public List<T> findWithNamedQuery(Class<T> type, String queryName);

    public List<T> findWithNamedQuery(Class<T> type, String queryName, int resultLimit);

    public List<T> findWithNamedQuery(Class<T> type, String namedQueryName, Map<String, Object> parameters);

    public List<T> findWithNamedQuery(Class<T> type, String namedQueryName, Map<String, Object> parameters, int resultLimit);

}