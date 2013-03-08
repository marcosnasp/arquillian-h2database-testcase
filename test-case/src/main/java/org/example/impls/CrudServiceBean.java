package org.example.impls;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.example.interfaces.CrudService;
import org.example.qualifiers.DataRepository;

/**
 * <p>
 * Abordagem adaptada dos links (@see): Utilizado com a abordagem DAP (Data Access Procedure).
 * </p>
 * 
 * @see <a>http://www.adam-bien.com/roller/abien/entry/generic_crud_service_aka_dao</a>
 * 
 * @see <a>https://www.evernote.com/shard/s126/sh/f9cccf4d-8324-4c82-9953-7122333a2864/7f7708ea456a44120f310693e577a0a5</a>
 * 
 *      <pre>
 * Exemplo de uso:
 * {@code
 *  private @Ejb CrudServiceBean<Viatura> viaturaDAO;
 *  // ou
 *  private @Inject CrudServiceBean<Viatura> viaturaDao;
 * }
 * </pre>
 * 
 * @param <T> - Qualquer entidade para operações com o banco de dados.
 * 
 * @author <a href="mailto:marcosportela@ioct.com.br">marcosportela@ioct.com.br</a>
 * 
 */
@Stateless
@LocalBean
@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
public class CrudServiceBean<T> implements CrudService<T> {

    @Inject
    @DataRepository
    EntityManager em;

    public T create(T t) {
        this.em.persist(t);
        this.em.flush();
        this.em.refresh(t);
        return t;
    }

    public T find(Class<T> type, Object id) {
        return (T) this.em.find(type, id);
    }

    public T update(T t) {
        return (T) this.em.merge(t);
    }

    public List<T> findWithNamedQuery(Class<T> type, String namedQueryName) {
        TypedQuery<T> q = em.createNamedQuery(namedQueryName, type);
        return q.getResultList();
    }

    public List<T> findWithNamedQuery(Class<T> type, String namedQueryName, Map<String, Object> parameters) {
        return findWithNamedQuery(type, namedQueryName, parameters, 0);
    }

    public List<T> findWithNamedQuery(Class<T> type, String queryName, int resultLimit) {

        TypedQuery<T> q = this.em.createNamedQuery(queryName, type);
        if (resultLimit > 0) {
            q.setMaxResults(resultLimit);
        }
        return q.getResultList();

    }

    @SuppressWarnings("unchecked")
    public List<T> findByNativeQuery(String sql, Class<T> type) {
        return this.em.createNativeQuery(sql, type).getResultList();
    }

    public List<T> findWithNamedQuery(Class<T> type, String namedQueryName, Map<String, Object> parameters, int resultLimit) {

        TypedQuery<T> query;
        Set<Entry<String, Object>> rawParameters = parameters.entrySet();

        try {
            query = this.em.createNamedQuery(namedQueryName, type);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getCause());
        }

        if (resultLimit > 0) {
            query.setMaxResults(resultLimit);
        }
        for (Entry<String, Object> entry : rawParameters) {
            query.setParameter((String) entry.getKey(), entry.getValue());
        }

        return (List<T>) query.getResultList();
    }

    public EntityManager getEntityManager() {
        return em;
    }

}